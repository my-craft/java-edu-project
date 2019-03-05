package team.mediasoft.mguseva.eduproject.film;

import java.util.Objects;

public abstract class FilmParameter {

    private Parameter parameter;

    public FilmParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public String getParameterName(Parameter parameter) {
        return this.parameter.getName();
    }

    @Override
    public boolean equals(Object filmParameterObject) {
        if (this == filmParameterObject) {
            return true;
        }

        if (filmParameterObject == null || getClass() != filmParameterObject.getClass()) {
            return false;
        }

        FilmParameter filmParameter = (FilmParameter) filmParameterObject;
        return Objects.equals(this.parameter, filmParameter.getParameter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parameter);
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
        return this.parameter.toString();
    }
}
