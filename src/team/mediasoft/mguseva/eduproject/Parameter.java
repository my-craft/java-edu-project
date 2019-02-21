package team.mediasoft.mguseva.eduproject;

import java.util.Objects;

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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
