package team.mediasoft.mguseva.eduproject;

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
}
