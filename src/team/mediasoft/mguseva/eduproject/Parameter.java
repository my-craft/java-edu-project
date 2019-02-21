package team.mediasoft.mguseva.eduproject;

/**
 * Параметр фильма
 */
public abstract class Parameter implements Cloneable {

    private String name;

    public Parameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
