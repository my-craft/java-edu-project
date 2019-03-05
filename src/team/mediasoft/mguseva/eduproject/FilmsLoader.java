package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.*;

import java.util.*;

public class FilmsLoader {

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

            if (id != null) {
                this.films.add(new Film((int)id, filmInfo[1], (int)year, filmInfo[3]));
            }
        }
    }

    private void enrichFilmsInfo() {
        Map<Integer, List<Map<Integer, String>>> filmCharactersTextList = this.getFilmParametersList(FilmsLoader.basePath + "actor_characters.csv");
        Map<Integer, List<Map<Integer, String>>> filmDirectorsTextList = this.getFilmParametersList(FilmsLoader.basePath + "film_directors.csv");
        Map<Integer, List<Map<Integer, String>>> filmGenresTextList = this.getFilmParametersList(FilmsLoader.basePath + "film_genres.csv");

        for (Film film : this.films) {
            int filmId = film.getId();
            if (filmCharactersTextList != null && this.actorsList != null) {
                List<Map<Integer, String>> filmCharacters = filmCharactersTextList.get(filmId);
                if (filmCharacters != null) {
                    for (Map<Integer, String> filmCharacter : filmCharacters) {
                        Map.Entry<Integer,String> entry = filmCharacter.entrySet().iterator().next();
                        if (entry != null) {
                            Integer key = entry.getKey();
                            String value = entry.getValue();

                            String actorName = this.actorsList.get(key);
                            if (actorName != null && actorName.length() > 0 && value != null && value.length() > 0) {
                                film.addActorCharacter(new ActorCharacter(new Actor(actorName), value));
                            }
                        }
                    }
                }
            }

            System.out.println(film.getFullInfo() + "\n\n\n");
        }
    }

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

            if (filmId != null && paramId != null) {
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

    private void addParameterFromString(String parameterText, Map<Integer, String> parametersList) {
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

    private List<String> getContentFromFile(String fileName) {
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
