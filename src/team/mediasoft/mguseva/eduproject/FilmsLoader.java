package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.comments.*;
import team.mediasoft.mguseva.eduproject.film.*;

import java.util.*;

public class FilmsLoader extends InputChecker {

    private static String basePath = "files/";

    private List<Film> films;
    private Map<Integer, String> actorsList;
    private Map<Integer, String> directorsList;
    private Map<Integer, String> genresList;
    private Map<Integer, String> criticsList;

    public FilmsLoader() {
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

            if (filmInfo == null) {
                continue;
            }

            Integer id = this.getIntegerFromString(filmInfo[0]);

            if (id != 0) {
                this.films.add(new Film((int)id, filmInfo[1], this.getIntegerFromString(filmInfo[2]), filmInfo[3]));
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

        Map<Integer, List<Map<Integer, String>>> filmRatesTextList = this.getRatesList(FilmsLoader.basePath + "rates.csv");

        for (Film film : this.films) {
            this.setFilmParameters(film, filmCharactersTextList, this.actorsList,
                    (Film tempFilm, String actorName, String[] addParameters) -> {
                        String characterName = "";
                        if (addParameters != null && addParameters.length > 0) {
                            characterName = addParameters[0];
                        }
                        tempFilm.addActorCharacter(new ActorCharacter(new Actor(actorName), characterName));
                    }
                );

            this.setFilmParameters(film, filmDirectorsTextList, this.directorsList,
                    (Film tempFilm, String directorName, String[] addParameters) -> {
                        tempFilm.addDirector(new FilmDirector(new Director(directorName)));
                    }
                );

            this.setFilmParameters(film, filmGenresTextList, this.genresList,
                    (Film tempFilm, String genreName, String[] addParameters) -> {
                        tempFilm.addGenre(new FilmGenre(new Genre(genreName)));
                    }
                );

            this.setFilmParameters(film, filmRatesTextList, this.criticsList,
                    (Film tempFilm, String criticName, String[] addParameters) -> {
                        int rate = 0;
                        String comment = "";
                        if (addParameters != null && addParameters.length > 1) {
                            // оценка
                            rate = this.getIntegerFromString(addParameters[0]);

                            // комментарий
                            comment = addParameters[1];
                        }

                        tempFilm.addCriticRate(new CriticRate(new Critic(criticName), rate, comment));
                    }
                );

            System.out.println(film.getFullInfo() + "\n\n\n");
            System.out.println(film.getCriticRates() + "\n\n\n");
        }
    }

    /**
     * Добавить в текущий фильм параметр
     *
     * @param film фильм
     * @param parametersInFilms таблица соответствий фильмов и параметров
     * @param parametersNames значения параметров
     * @param filmParameterAdder интерфейс добавления параметров в фильм
     */
    private void setFilmParameters(Film film, Map<Integer, List<Map<Integer, String>>> parametersInFilms, Map<Integer, String> parametersNames, FilmParameterAdder filmParameterAdder) {
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
                    filmParameterAdder.add(film, parametersNames.get(entry.getKey()), this.getArrayFromString(entry.getValue(), 0));
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

            String[] strArray = this.getArrayFromString(parameterText, 0);
            if (strArray == null || strArray.length < 2 || strArray.length > 3) {
                continue;
            }

            Integer filmId = this.getIntegerFromString(strArray[0]);
            Integer paramId = this.getIntegerFromString(strArray[1]);

            if (filmId != 0 && paramId != 0) {
                String paramText = (strArray.length == 3) ? strArray[2] : "";

                this.addFilmParamToList(parametersList, filmId, paramId, paramText);
            }
        }

        return parametersList;
    }

    /**
     * Получить список оценок критиков к фильму
     *
     * @param csvFileName
     * @return
     */
    private Map<Integer, List<Map<Integer, String>>> getRatesList(String csvFileName) {
        List<String> ratesTextList = this.getContentFromFile(csvFileName);

        if (ratesTextList == null) {
            return null;
        }

        Map<Integer, List<Map<Integer, String>>> ratesList = new HashMap<Integer, List<Map<Integer, String>>>();

        int tempRateId = 1;

        for (String rateText : ratesTextList) {
            if (rateText == null) {
                continue;
            }

            String[] strArray = this.getArrayFromString(rateText, 4);
            if (strArray == null) {
                continue;
            }

            Integer filmId = this.getIntegerFromString(strArray[0]);
            Integer rate = this.getIntegerFromString(strArray[2]);

            if (filmId != 0 && rate != 0) {
                int newRateId = tempRateId++;
                this.criticsList.put(newRateId, strArray[1]);

                String paramText = rate + ";" + strArray[3];

                this.addFilmParamToList(ratesList, filmId, newRateId, paramText);
            }
        }

        return ratesList;
    }

    /**
     * Добавить новый параметр в список параметров фильма
     *
     * @param paramsList
     * @param filmId
     * @param paramId
     * @param text
     */
    private void addFilmParamToList(Map<Integer, List<Map<Integer, String>>> paramsList, int filmId, int paramId, String text) {
        List<Map<Integer, String>> paramsInFilm = paramsList.get(filmId);
        if (paramsInFilm == null) {
            paramsInFilm = new ArrayList<Map<Integer, String>>();
        }

        paramsInFilm.add(new HashMap<Integer, String>() {{ put(paramId, text); }});

        paramsList.put(filmId, paramsInFilm);
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
        FileWorker readFileWorker = new FileWorker(fileName);
        readFileWorker.loadContentFromFile();
        return readFileWorker.getContent();
    }

    /**
     * Записать строки в файл
     *
     * @param fileName
     * @param content
     * @param append
     */
    private void setContentToFile(String fileName, List<String> content, boolean append) {
        FileWorker writeFileWorker = new FileWorker(fileName, content);
        writeFileWorker.putContentToFile(append);
    }

    /**
     * Получить массив строк из строки по разделителю ";"
     *
     * @param str
     * @param count
     * @return
     */
    private String[] getArrayFromString(String str, int count) {
        if (str == null || str.length() < 1) {
            return null;
        }

        String[] strArray = str.split(";");
        if (count != 0 && strArray.length != count) {
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

        //System.out.println(filmToComment.getCriticRates());

        List<String> newRate = new ArrayList<String>() {{
            add(filmToComment.getId() + ";" + rate.toCsvString());
        }};

        this.setContentToFile(this.basePath + "rates.csv", newRate, true);
    }
}
