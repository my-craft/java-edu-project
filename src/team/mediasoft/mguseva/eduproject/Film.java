package team.mediasoft.mguseva.eduproject;

import java.util.*;

/**
 * Фильм
 */
public class Film implements Cloneable {

    /**
     * Название
     */
    private String name;

    /**
     * Год выпуска
     */
    private int year;

    /**
     * Описание
     */
    private String description;

    /**
     * Актеры
     */
    private ArrayList<ActorCharacter> actors;

    /**
     * Режиссеры
     */
    private ArrayList<Director> directors;

    /**
     * Жанры
     */
    private ArrayList<Genre> genres;

    /**
     * Оценки и отзывы критиков
     */
    private ArrayList<CriticRate> rates;

    public Film(String name, int year, String description) {
        this.name = name;
        this.year = year;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<ActorCharacter> getActors() {
        return actors;
    }

    public void setActors(ArrayList<ActorCharacter> actors) {
        this.actors = actors;
    }

    public ArrayList<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<Director> directors) {
        this.directors = directors;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }

    public ArrayList<CriticRate> getRates() {
        return rates;
    }

    public void setRates(ArrayList<CriticRate> rates) {
        this.rates = rates;
    }

    /**
     * Текст с полной информацией о фильме
     * @return
     */
    public String getFullInfo()
    {
        String info = "Фильм: " + this.name +
                        "\nГод выпуска: " + this.year;

        if (this.directors != null && this.directors.size() > 0) {
            String directorLabel = "\nРежиссер";
            if (this.directors.size() > 1) {
                directorLabel += "ы";
            }

            /*info += directorLabel + ": " + String.join(", ", this.directors.toArray());

            for (Director director : this.directors) {
                info +=
            }*/
        }

        return info;
    }

    @Override
    public boolean equals(Object filmObject) {
        if (this == filmObject) {
            return true;
        }

        if (filmObject == null || getClass() != filmObject.getClass()) {
            return false;
        }

        Film film = (Film) filmObject;
        return this.year == film.getYear() &&
                Objects.equals(this.name, film.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.year);
    }

    @Override
    public Object clone() {
        try {
            Object newObject = super.clone();

            return newObject;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return ((this.name != null) ? (this.name + " ") : "") +
                ((this.year != 0) ? (this.year + " ") : "") +
                ((this.description != null) ? this.description : "");
    }
}
