package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.Actor;
import team.mediasoft.mguseva.eduproject.film.Film;
import team.mediasoft.mguseva.eduproject.film.Parameter;

import java.util.*;

public class FilmsLoader {

    private ArrayList<Film> films;

    public FilmsLoader() {
        this.films = new ArrayList<Film>();
    }

    public ArrayList<Film> getFilms() {
        return films;
    }

    public void setFilms(ArrayList<Film> films) {
        this.films = films;
    }

    public void loadFilmsFromFile() {
        HashMap<Integer, String> actorsList = this.getParametersList("src/files/actors.csv");
        HashMap<Integer, String> directorsList = this.getParametersList("src/files/directors.csv");
        HashMap<Integer, String> genresList = this.getParametersList("src/files/genres.csv");

        System.out.println(genresList);
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

    private void addParameterFromString(String parameterText, HashMap<Integer, String> parametersList) {
        String[] parameterInfo = this.getArrayFromString(parameterText, 2);
        if (parameterInfo == null) {
            return;
        }

        int id = 0;
        try {
            id = Integer.parseInt(parameterInfo[0]);
        } catch (Exception e) {
            return;
        }

        parametersList.put((Integer)id, parameterInfo[1]);
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
