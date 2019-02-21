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
