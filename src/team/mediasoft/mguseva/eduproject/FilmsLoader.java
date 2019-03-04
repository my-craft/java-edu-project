package team.mediasoft.mguseva.eduproject;

import team.mediasoft.mguseva.eduproject.film.Actor;
import team.mediasoft.mguseva.eduproject.film.Film;

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
        HashMap<Integer, Actor> actorsList = this.getActorsList();
        System.out.println(actorsList);
    }

    private ArrayList<String> getContentFromFile(String fileName) {
        FileWorker actorsWorker = new FileWorker(fileName);
        actorsWorker.loadContentFromFile();
        return actorsWorker.getContent();
    }

    private HashMap<Integer, Actor> getActorsList() {
        ArrayList<String> actorsTextList = this.getContentFromFile("src/files/actors.csv");
        HashMap<Integer, Actor> actorsList = new HashMap<Integer, Actor>();
        for (String actorText : actorsTextList) {
            Actor tempActor = this.getActorFromString(actorText);
            actorsList.put((Integer)tempActor.getId(), tempActor);
        }

        return actorsList;
    }

    private Actor getActorFromString(String actorText) {
        if (actorText == null) {
            return null;
        }

        String[] actorInfo = actorText.split(";");
        if (actorInfo.length != 2) {
            return null;
        }

        int actorId = 0;
        try {
            actorId = Integer.parseInt(actorInfo[0]);
        } catch (Exception e) {
            return null;
        }

        return new Actor(actorId, actorInfo[1]);
    }
}
