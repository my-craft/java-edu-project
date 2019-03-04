package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.*;

import java.util.*;

public class FilmsLoader {

    private ArrayList<Film> films;
    private HashMap<Integer, String> actorsList;
    private HashMap<Integer, String> directorsList;
    private HashMap<Integer, String> genresList;

    public FilmsLoader() {
        this.films = new ArrayList<Film>();
        this.actorsList = new HashMap<Integer, String>();
        this.directorsList = new HashMap<Integer, String>();
        this.genresList = new HashMap<Integer, String>();
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public void loadFilms() {
        this.loadFilmInfo();
        if (this.films == null || this.films.isEmpty()) {
            return;
        }

        HashMap<Integer, String> loadedActorsList = this.getParametersList("src/files/actors.csv");
        if (loadedActorsList != null) {
            this.actorsList = loadedActorsList;
        }

        HashMap<Integer, String> loadedDirectorsList = this.getParametersList("src/files/directors.csv");
        if (loadedDirectorsList != null) {
            this.directorsList = loadedDirectorsList;
        }

        HashMap<Integer, String> loadedGenresList = this.getParametersList("src/files/genres.csv");
        if (loadedGenresList != null) {
            this.genresList = loadedGenresList;
        }

        this.enrichFilmsInfo();
    }

    private void loadFilmInfo() {
        ArrayList<String> filmsTextList = this.getContentFromFile("src/files/films.csv");
        if (filmsTextList == null) {
            return;
        }

        this.films = new ArrayList<Film>();
        for (String filmText : filmsTextList) {
            String[] filmInfo = this.getArrayFromString(filmText, 4);

            Integer id = this.getIntegerFromString(filmInfo[0]);
            Integer year = this.getIntegerFromString(filmInfo[2]);

            if (id != null) {
                this.films.add(new Film((int)id, filmInfo[1], (int)year, filmInfo[3]));
            }
        }
    }

    private void enrichFilmsInfo() {
        HashMap<Integer, ArrayList<HashMap<Integer, String>>> filmCharactersTextList = this.getFilmParametersList("src/files/actor_characters.csv");
        HashMap<Integer, ArrayList<HashMap<Integer, String>>> filmDirectorsTextList = this.getFilmParametersList("src/files/film_directors.csv");
        HashMap<Integer, ArrayList<HashMap<Integer, String>>> filmGenresTextList = this.getFilmParametersList("src/files/film_genres.csv");



        for (Film film : this.films) {

            System.out.println(film.getFullInfo());
        }
    }

    private HashMap<Integer, String> getParametersList(String csvFileName) {
        ArrayList<String> parametersTextList = this.getContentFromFile(csvFileName);

        if (parametersTextList == null) {
            return null;
        }

        HashMap<Integer, String> parametersList = new HashMap<Integer, String>();
        for (String parameterText : parametersTextList) {
            this.addParameterFromString(parameterText, parametersList);
        }

        return parametersList;
    }

    private HashMap<Integer, ArrayList<HashMap<Integer, String>>> getFilmParametersList(String csvFileName) {
        ArrayList<String> parametersTextList = this.getContentFromFile(csvFileName);

        if (parametersTextList == null) {
            return null;
        }

        HashMap<Integer, ArrayList<HashMap<Integer, String>>> parametersList = new HashMap<Integer, ArrayList<HashMap<Integer, String>>>();
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

            if (filmId != null && paramId != null) {
                String paramText = (strArray.length == 3) ? strArray[2] : "";

                ArrayList<HashMap<Integer, String>> paramsInFilm = parametersList.get(filmId);
                if (paramsInFilm == null) {
                    paramsInFilm = new ArrayList<HashMap<Integer, String>>();
                }

                paramsInFilm.add(new HashMap<Integer, String>() {{ put(paramId, paramText); }});

                parametersList.put(filmId, paramsInFilm);
            }
        }

        System.out.println(parametersList);

        return parametersList;
    }

    private void addParameterFromString(String parameterText, HashMap<Integer, String> parametersList) {
        String[] parameterInfo = this.getArrayFromString(parameterText, 2);
        if (parameterInfo == null) {
            return;
        }

        Integer id = this.getIntegerFromString(parameterInfo[0]);

        if (id != null) {
            parametersList.put(id, parameterInfo[1]);
        }
    }

    private Integer getIntegerFromString(String valueStr) {
        int value = 0;
        try {
            value = Integer.parseInt(valueStr);
        } catch (Exception e) {
            return null;
        }

        return (Integer) value;
    }

    private ArrayList<String> getContentFromFile(String fileName) {
        FileWorker actorsWorker = new FileWorker(fileName);
        actorsWorker.loadContentFromFile();
        return actorsWorker.getContent();
    }

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
}
