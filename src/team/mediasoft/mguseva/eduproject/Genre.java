package team.mediasoft.mguseva.eduproject;

/**
 * Жанр
 */
public class Genre {

    private int id;
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
