package team.mediasoft.mguseva.eduproject.film;

/**
 * Жанр
 */
public class Genre extends Parameter {

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.genres";
    public static final String idColumn = "genre_id";
    public static final String nameColumn = "name";

    public Genre(String name) {
        super(name);
    }

    public Genre(int id, String name) {
        super(id, name);
    }
}
