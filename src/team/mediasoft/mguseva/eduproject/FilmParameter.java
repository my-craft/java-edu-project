package team.mediasoft.mguseva.eduproject;

import java.util.Objects;

public abstract class FilmParameter {

    private Parameter parameter;
    private Film film;

    public FilmParameter(Parameter parameter, Film film) {
        this.parameter = parameter;
        this.film = film;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
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
        return Objects.equals(this.parameter, filmParameter.getParameter()) &&
                Objects.equals(this.film, filmParameter.getParameter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.parameter, this.film);
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
        return this.film.toString() + " - " + this.parameter.toString();
    }
}
