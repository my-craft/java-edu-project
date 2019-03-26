package team.mediasoft.mguseva.eduproject.film;

/**
 * Актер
 */
public class Actor extends Person {

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.actors";
    public static final String idColumn = "actor_id";
    public static final String nameColumn = "name";

    public Actor(String name) {
        super(name);
    }

    public Actor(int id, String name) {
        super(id, name);
    }
}
