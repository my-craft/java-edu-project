package team.mediasoft.mguseva.eduproject.film;

/**
 * Критик
 */
public class Critic extends Person {

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.critics";
    public static final String idColumn = "critic_id";
    public static final String nameColumn = "name";

    public Critic(String name) {
        super(name);
    }

    public Critic(int id, String name) {
        super(id, name);
    }
}
