package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.Film;

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
                String sql = "select * from films";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    Film film = new Film(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("year"), resultSet.getString("description"));
                    this.films.add(film);
                }

            }
        } catch (Exception e) {

        }

        System.out.println(this.films);

    }
}
