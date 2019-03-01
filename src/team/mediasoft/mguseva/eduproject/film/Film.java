package team.mediasoft.mguseva.eduproject.film;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Фильм
 */
public class Film implements Cloneable {

    private static int firstFilmYear = 1895;

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
     * @return String
     */
    public String getFullInfo()
    {
        String info = "Фильм: " + ((this.name != null && !this.name.isEmpty()) ? this.name : "-") +
                        "\nГод выпуска: " + (this.year >= Film.firstFilmYear ? this.year : "-");

        if (this.genres != null && !this.genres.isEmpty()) {
            String genreLabel = "\nЖанр";
            if (this.genres.size() > 1) {
                genreLabel += "ы";
            }

            // метод склеивания взят отсюда http://qaru.site/questions/20303/best-way-to-convert-an-arraylist-to-a-string
            info += genreLabel + ": " + this.genres.stream().map(Object::toString).collect(Collectors.joining(", "));
        }

        info += "\nОписание: " + ((this.description != null && !this.description.isEmpty()) ? this.description : "-") + "\n";

        if (this.directors != null && !this.directors.isEmpty()) {
            String directorLabel = "\nРежиссер";
            if (this.directors.size() > 1) {
                directorLabel += "ы";
            }

            info += directorLabel + ": " + this.directors.stream().map(Object::toString).collect(Collectors.joining(", "));
        }

        if (this.actors != null && !this.actors.isEmpty()) {
            String actorLabel = "\n\nАктер";
            if (this.actors.size() > 1) {
                actorLabel += "ы";
            }

            info += actorLabel + ": \n" + this.actors.stream().map(Object::toString).collect(Collectors.joining("\n"));
        }

        return info;
    }

    /**
     * Получить отзывы критиков
     * @return String
     */
    public String getCriticRates() {
        String rates = "Средняя оценка: ";

        boolean hasRates = (this.rates != null && !this.rates.isEmpty());

        if (hasRates) {
            int rateSum = 0;
            int rateCount = 0;

            for (CriticRate rate : this.rates) {
                rateSum += rate.getRate();
                rateCount++;
            }

            rates += ((float)rateSum / (float)rateCount) + "\n";
        }

        rates += "Отзывы: \n";

        if (hasRates) {
            rates += this.rates.stream().map(Object::toString).collect(Collectors.joining("\n***\n"));
        } else {
            rates += "Никто ещё не оставил ни одного отзыва";
        }

        return  rates;
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
