package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.comments.CommentReader;
import team.mediasoft.mguseva.eduproject.comments.FilmChooser;
import team.mediasoft.mguseva.eduproject.comments.NameReader;
import team.mediasoft.mguseva.eduproject.comments.RateReader;
import team.mediasoft.mguseva.eduproject.film.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FilmsLoader extends InputChecker {

    private static String basePath = "files/";

    private List<Film> films;
    private Map<Integer, String> actorsList;
    private Map<Integer, String> directorsList;
    private Map<Integer, String> genresList;

    public FilmsLoader() {
        this.films = new ArrayList<Film>();
        this.actorsList = new HashMap<Integer, String>();
        this.directorsList = new HashMap<Integer, String>();
        this.genresList = new HashMap<Integer, String>();
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
        this.loadFilmInfo();
        if (this.films == null || this.films.isEmpty()) {
            return;
        }

        Map<Integer, String> loadedActorsList = this.getParametersList(FilmsLoader.basePath + "actors.csv");
        if (loadedActorsList != null) {
            this.actorsList = loadedActorsList;
        }

        Map<Integer, String> loadedDirectorsList = this.getParametersList(FilmsLoader.basePath + "directors.csv");
        if (loadedDirectorsList != null) {
            this.directorsList = loadedDirectorsList;
        }

        Map<Integer, String> loadedGenresList = this.getParametersList(FilmsLoader.basePath + "genres.csv");
        if (loadedGenresList != null) {
            this.genresList = loadedGenresList;
        }

        this.enrichFilmsInfo();
    }

    /**
     * Загрузить информацию о фильмах
     */
    private void loadFilmInfo() {
        List<String> filmsTextList = this.getContentFromFile(FilmsLoader.basePath + "films.csv");
        if (filmsTextList == null) {
            return;
        }

        this.films = new ArrayList<Film>();
        for (String filmText : filmsTextList) {
            String[] filmInfo = this.getArrayFromString(filmText, 4);

            Integer id = this.getIntegerFromString(filmInfo[0]);
            Integer year = this.getIntegerFromString(filmInfo[2]);

            if (id != 0) {
                this.films.add(new Film((int)id, filmInfo[1], (int)year, filmInfo[3]));
            }
        }
    }

    /**
     * Добавить в фильтмы список актеров, режиссеров и жанров
     */
    private void enrichFilmsInfo() {
        Map<Integer, List<Map<Integer, String>>> filmCharactersTextList = this.getFilmParametersList(FilmsLoader.basePath + "actor_characters.csv");
        Map<Integer, List<Map<Integer, String>>> filmDirectorsTextList = this.getFilmParametersList(FilmsLoader.basePath + "film_directors.csv");
        Map<Integer, List<Map<Integer, String>>> filmGenresTextList = this.getFilmParametersList(FilmsLoader.basePath + "film_genres.csv");

        for (Film film : this.films) {
            this.setFilmParameters(film, filmCharactersTextList, this.actorsList, new ActorCharacter());
            this.setFilmParameters(film, filmDirectorsTextList, this.directorsList, new FilmDirector());
            this.setFilmParameters(film, filmGenresTextList, this.genresList, new FilmGenre());

            //System.out.println(film.getFullInfo() + "\n\n\n");
        }
    }

    /**
     * Добавить в текущий фильм параметр
     *
     * @param film фильм
     * @param parametersInFilms таблица соответствий фильмов и параметров
     * @param parametersNames значения параметров
     * @param filmParameter экземпляр класса параметра для вызова привязки к фильму
     */
    private void setFilmParameters(Film film, Map<Integer, List<Map<Integer, String>>> parametersInFilms, Map<Integer, String> parametersNames, FilmParameter filmParameter) {
        if (film == null || parametersInFilms == null || parametersNames == null) {
            return;
        }

        int filmId = film.getId();

        // получить список параметров у фильма
        List<Map<Integer, String>> currentFilmParams = parametersInFilms.get(filmId);
        if (currentFilmParams != null) {
            for (Map<Integer, String> currentFilmParam : currentFilmParams) {
                // получить id параметра и дополнительное поле, если нужно
                Map.Entry<Integer,String> entry = currentFilmParam.entrySet().iterator().next();
                if (entry != null) {
                    try {
                        // получить новый экземпляр класса параметра
                        FilmParameter tempFilmParameter = filmParameter.getClass().newInstance();

                        // установить в него значение
                        tempFilmParameter.setParameterByName(parametersNames.get(entry.getKey()));
                        // установить в него дополнительное свойство
                        tempFilmParameter.setAddParameter(new ArrayList<String>() {{ add(entry.getValue()); }});

                        // добавить его в фильм
                        tempFilmParameter.addParameterToFilm(film);
                    } catch (Exception e) {}
                }
            }
        }
    }

    /**
     * Получить справочник из файла
     *
     * @param csvFileName
     * @return
     */
    private Map<Integer, String> getParametersList(String csvFileName) {
        List<String> parametersTextList = this.getContentFromFile(csvFileName);

        if (parametersTextList == null) {
            return null;
        }

        Map<Integer, String> parametersList = new HashMap<Integer, String>();
        for (String parameterText : parametersTextList) {
            this.addParameterFromString(parameterText, parametersList);
        }

        return parametersList;
    }

    /**
     * Получить список соответствий для фильмов из файла
     *
     * @param csvFileName
     * @return
     */
    private Map<Integer, List<Map<Integer, String>>> getFilmParametersList(String csvFileName) {
        List<String> parametersTextList = this.getContentFromFile(csvFileName);

        if (parametersTextList == null) {
            return null;
        }

        Map<Integer, List<Map<Integer, String>>> parametersList = new HashMap<Integer, List<Map<Integer, String>>>();
        for (String parameterText : parametersTextList) {
            if (parameterText == null) {
                continue;
            }

            String[] strArray = parameterText.split(";");
            if (strArray.length < 2 || strArray.length > 3) {
                continue;
            }

            Integer filmId = this.getIntegerFromString(strArray[0]);
            Integer paramId = this.getIntegerFromString(strArray[1]);

            if (filmId != 0 && paramId != 0) {
                String paramText = (strArray.length == 3) ? strArray[2] : "";

                List<Map<Integer, String>> paramsInFilm = parametersList.get(filmId);
                if (paramsInFilm == null) {
                    paramsInFilm = new ArrayList<Map<Integer, String>>();
                }

                paramsInFilm.add(new HashMap<Integer, String>() {{ put(paramId, paramText); }});

                parametersList.put(filmId, paramsInFilm);
            }
        }

        return parametersList;
    }

    /**
     * Преобразовать строку и записать её в справочник
     *
     * @param parameterText
     * @param parametersList
     */
    private void addParameterFromString(String parameterText, Map<Integer, String> parametersList) {
        String[] parameterInfo = this.getArrayFromString(parameterText, 2);
        if (parameterInfo == null) {
            return;
        }

        Integer id = this.getIntegerFromString(parameterInfo[0]);

        if (id != 0) {
            parametersList.put(id, parameterInfo[1]);
        }
    }

    /**
     * Получить список строк файла
     *
     * @param fileName
     * @return
     */
    private List<String> getContentFromFile(String fileName) {
        FileWorker actorsWorker = new FileWorker(fileName);
        actorsWorker.loadContentFromFile();
        return actorsWorker.getContent();
    }

    /**
     * Получить массив строк из строки по разделителю ";"
     *
     * @param str
     * @param count
     * @return
     */
    private String[] getArrayFromString(String str, int count) {
        if (str == null) {
            return null;
        }

        String[] strArray = str.split(";");
        if (strArray.length != count) {
            return null;
        }

        return strArray;
    }

    /**
     * Ввод комментариев к фильму
     */
    public void inputCommentToFilm() {
        Film filmToComment = (Film) (new FilmChooser(this.films)).getInfoFromUser();
        if (filmToComment == null) {
            return;
        }

        CriticRate rate = new CriticRate(
                new Critic((String) (new NameReader()).getInfoFromUser()),
                (Integer) (new RateReader()).getInfoFromUser(),
                (String) (new CommentReader()).getInfoFromUser());

        filmToComment.addCriticRate(rate);

        System.out.println(filmToComment.getCriticRates());

    }
}
