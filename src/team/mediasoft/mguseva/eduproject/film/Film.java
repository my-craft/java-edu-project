package team.mediasoft.mguseva.eduproject.film;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Фильм
 */
public class Film implements Cloneable {

    private static int firstFilmYear = 1895;

    private int id;

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
    private List<ActorCharacter> actors;

    /**
     * Режиссеры
     */
    private List<FilmDirector> directors;

    /**
     * Жанры
     */
    private List<FilmGenre> genres;

    /**
     * Оценки и отзывы критиков
     */
    private List<CriticRate> rates;

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.films";
    public static final String idColumn = "film_id";
    public static final String nameColumn = "name";
    public static final String yearColumn = "year";
    public static final String descriptionColumn = "description";

    public Film(String name, int year, String description) {
        this.name = name;
        this.year = year;
        this.description = description;
    }

    public Film(int id, String name, int year, String description) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<ActorCharacter> getActors() {
        return actors;
    }

    public void setActors(List<ActorCharacter> actors) {
        this.actors = actors;
    }

    public List<FilmDirector> getDirectors() {
        return directors;
    }

    public void setDirectors(List<FilmDirector> directors) {
        this.directors = directors;
    }

    public List<FilmGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<FilmGenre> genres) {
        this.genres = genres;
    }

    public List<CriticRate> getRates() {
        return rates;
    }

    public void setRates(List<CriticRate> rates) {
        this.rates = rates;
    }

    /**
     * Добавить персонажа в список
     *
     * @param actorCharacter
     */
    public void addActorCharacter(ActorCharacter actorCharacter) {
        if (this.actors == null) {
            this.actors = new ArrayList<ActorCharacter>();
        }

        if (actorCharacter != null) {
            this.actors.add(actorCharacter);
        }
    }

    /**
     * Добавить режиссера в список
     *
     * @param director
     */
    public void addDirector(FilmDirector director) {
        if (this.directors == null) {
            this.directors = new ArrayList<FilmDirector>();
        }

        this.directors.add(director);
    }

    /**
     * Добавить жанр в список
     *
     * @param genre
     */
    public void addGenre(FilmGenre genre) {
        if (this.genres == null) {
            this.genres = new ArrayList<FilmGenre>();
        }

        this.genres.add(genre);
    }

    /**
     * Добавить оценку критика
     *
     * @param rate
     */
    public void addCriticRate(CriticRate rate) {
        if (this.rates == null) {
            this.rates = new ArrayList<CriticRate>();
        }

        this.rates.add(rate);
    }

    /**
     * Текст с полной информацией о фильме
     *
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
     *
     * @return String
     */
    public String getCriticRates() {
        String rates = "";

        boolean hasRates = (this.rates != null && !this.rates.isEmpty());

        if (hasRates) {
            rates += "Средняя оценка: ";

            int rateSum = 0;
            int rateCount = 0;

            for (CriticRate rate : this.rates) {
                rateSum += rate.getRate();
                rateCount++;
            }

            rates += Math.round((float)rateSum / (float)rateCount) + "\n";
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
