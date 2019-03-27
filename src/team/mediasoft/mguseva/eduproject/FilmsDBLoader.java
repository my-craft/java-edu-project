package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.comments.*;
import team.mediasoft.mguseva.eduproject.film.*;

import java.sql.*;
import java.util.*;

public class FilmsDBLoader {

    private DBConnector dbConnector;

    private List<Film> films;
    private Map<Integer, String> actorsList;
    private Map<Integer, String> directorsList;
    private Map<Integer, String> genresList;
    private Map<Integer, String> criticsList;

    public FilmsDBLoader() {
        this.dbConnector = new DBConnector();

        this.films = new ArrayList<Film>();
        this.actorsList = new HashMap<Integer, String>();
        this.directorsList = new HashMap<Integer, String>();
        this.genresList = new HashMap<Integer, String>();
        this.criticsList = new HashMap<Integer, String>();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    /**
     * Загрузить список фильмов
     */
    public void loadFilms() {
        try {
            this.dbConnector.connect();
            Connection connection = this.dbConnector.getConnection();
            if(connection != null) {
                Statement statement = connection.createStatement();
                String sql = "select * from " + Film.tableName;

                ResultSet resultSet = statement.executeQuery(sql);

                while(resultSet.next()) {
                    this.addFilmInfoByRow(resultSet, connection);
                }

            }
        } catch (Exception e) {

        } finally {
            dbConnector.disconnect();
        }

        for (Film film : this.films) {
            System.out.println(film.getFullInfo() + "\n\n\n");
            System.out.println(film.getCriticRates() + "\n\n\n");
        }

    }

    /**
     * Добавить информацио о фильме из строки запроса к БД
     *
     * @param resultSet
     */
    private void addFilmInfoByRow(ResultSet resultSet, Connection connection) {
        try {
            Film film = new Film(resultSet.getInt(Film.idColumn), resultSet.getString(Film.nameColumn), resultSet.getInt(Film.yearColumn), resultSet.getString(Film.descriptionColumn));

            if (film.getId() > 0) {
                // персонажи
                String charactersSql = "select fc." + ActorCharacter.characterColumn + ", a." + Actor.nameColumn +
                                        " from " + ActorCharacter.tableName + " as fc" +
                                        " left join " + Actor.tableName + " as a on a." + Actor.idColumn + " = fc." + ActorCharacter.actorIdColumn +
                                        " where fc." + ActorCharacter.filmIdColumn + " = ?";

                this.addParameterToFilm(
                        film,
                        charactersSql,
                        1,
                        connection,
                        (Film tempFilm, ResultSet paramResultSet) -> {
                            try {
                                tempFilm.addActorCharacter(new ActorCharacter(new Actor(paramResultSet.getString(Actor.nameColumn)), paramResultSet.getString(ActorCharacter.characterColumn)));
                            } catch (Exception e) {

                            }
                        }
                    );

                // режиссеры
                String directorsSql = "select d." + Director.nameColumn +
                        " from " + FilmDirector.tableName + " as fd" +
                        " left join " + Director.tableName + " as d on d." + Director.idColumn + " = fd." + FilmDirector.directorIdColumn +
                        " where fd." + FilmDirector.filmIdColumn + " = ?";

                this.addParameterToFilm(
                        film,
                        directorsSql,
                        1,
                        connection,
                        (Film tempFilm, ResultSet paramResultSet) -> {
                            try {
                                tempFilm.addDirector(new FilmDirector(new Director(paramResultSet.getString(Director.nameColumn))));
                            } catch (Exception e) {

                            }
                        }
                );

                // жанры
                String genresSql = "select g." + Genre.nameColumn +
                        " from " + FilmGenre.tableName + " as fg" +
                        " left join " + Genre.tableName + " as g on g." + Genre.idColumn + " = fg." + FilmGenre.genreIdColumn +
                        " where fg." + FilmGenre.filmIdColumn + " = ?";

                this.addParameterToFilm(
                        film,
                        genresSql,
                        1,
                        connection,
                        (Film tempFilm, ResultSet paramResultSet) -> {
                            try {
                                tempFilm.addGenre(new FilmGenre(new Genre(paramResultSet.getString(Genre.nameColumn))));
                            } catch (Exception e) {

                            }
                        }
                );

                // оценки
                String ratesSql = "select cr." + CriticRate.rateColumn + ", cr." + CriticRate.commentColumn + ", c." + Critic.nameColumn +
                        " from " + CriticRate.tableName + " as cr" +
                        " left join " + Critic.tableName + " as c on c." + Critic.idColumn + " = cr." + CriticRate.criticIdColumn +
                        " where cr." + CriticRate.filmIdColumn + " = ?";

                this.addParameterToFilm(
                        film,
                        ratesSql,
                        1,
                        connection,
                        (Film tempFilm, ResultSet paramResultSet) -> {
                            try {
                                tempFilm.addCriticRate(new CriticRate(new Critic(paramResultSet.getString(Critic.nameColumn)), paramResultSet.getInt(CriticRate.rateColumn), paramResultSet.getString(CriticRate.commentColumn)));
                            } catch (Exception e) {

                            }
                        }
                );
            }

            this.films.add(film);
        } catch (Exception e) {

        }
    }

    private void addParameterToFilm(Film film, String sql, int filmIdSqlPosition, Connection connection, FilmParameterAdderFromDB adder) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(filmIdSqlPosition, film.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                adder.add(film, resultSet);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Ввод комментариев к фильму
     */
    public void inputCommentToFilm() {
        Film filmToComment = (Film) (new FilmChooser(this.films)).getInfoFromUser();
        if (filmToComment == null) {
            return;
        }

        String criticName = (String) (new NameReader()).getInfoFromUser();

        if (criticName == null) {
            return;
        }

        Critic newCritic = new Critic(criticName);

        Integer criticRate = (Integer) (new RateReader()).getInfoFromUser();
        String criticComment = (String) (new CommentReader()).getInfoFromUser();

        if (criticRate == null || criticComment == null) {
            return;
        }

        CriticRate rate = new CriticRate(newCritic, criticRate, criticComment);

        filmToComment.addCriticRate(rate);

        this.saveRate(filmToComment, rate);
    }

    private void saveRate(Film film, CriticRate rate) {
        try {
            this.dbConnector.connect();
            Connection connection = this.dbConnector.getConnection();
            if(connection != null) {
                String sql = "insert into " + Critic.tableName +
                                " (" + Critic.nameColumn + ")" +
                                " values (?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, rate.getParameter().getName());

                if (preparedStatement.executeUpdate() > 0) {
                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        String rateSql = "insert into " + CriticRate.tableName +
                                " (" + CriticRate.filmIdColumn + ", " + CriticRate.criticIdColumn + ", " + CriticRate.rateColumn + ", " + CriticRate.commentColumn + ")" +
                                " values (?, ?, ?, ?)";
                        PreparedStatement ratePreparedStatement = connection.prepareStatement(rateSql);
                        ratePreparedStatement.setInt(1, film.getId());
                        ratePreparedStatement.setInt(2, generatedKeys.getInt(1));
                        ratePreparedStatement.setInt(3, rate.getRate());
                        ratePreparedStatement.setString(4, rate.getComment());

                        ratePreparedStatement.executeUpdate();
                    }

                }

            }
        } catch (Exception e) {

        } finally {
            dbConnector.disconnect();
        }
    }
}
