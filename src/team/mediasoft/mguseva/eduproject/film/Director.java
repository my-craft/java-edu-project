package team.mediasoft.mguseva.eduproject.film;

/**
 * Режиссер
 */
public class Director extends Person {

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.directors";
    public static final String idColumn = "director_id";
    public static final String nameColumn = "name";

    public Director(String name) {
        super(name);
    }

    public Director(int id, String name) {
        super(id, name);
    }
}
