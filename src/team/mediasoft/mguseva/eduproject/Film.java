package team.mediasoft.mguseva.eduproject;

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
    private short year;

    /**
     * Описание
     */
    private String description;

    public Film(String name, short year, String description) {
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

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
