package team.mediasoft.mguseva.eduproject.film;

import java.util.Objects;

/**
 * Параметр фильма
 */
public abstract class Parameter implements Cloneable {

    private int id;

    private String name;

    public Parameter(String name) {
        this.name = name;
    }

    public Parameter(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object parameterObject) {
        if (this == parameterObject) {
            return true;
        }

        if (parameterObject == null || getClass() != parameterObject.getClass()) {
            return false;
        }

        Parameter parameter = (Parameter) parameterObject;
        return Objects.equals(this.name, parameter.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
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
        return this.name;
    }
}
