package team.mediasoft.mguseva.eduproject.film;

/**
 * Человек
 */
public abstract class Person extends Parameter {

    public Person(String name) {
        super(name);
    }

    public Person(int id, String name) {
        super(id, name);
    }
}
