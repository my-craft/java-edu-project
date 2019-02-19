package team.mediasoft.mguseva.eduproject;

public abstract class FilmParameter {

    private int parameterId;
    private int filmId;

    public FilmParameter(int parameterId, int filmId) {
        this.parameterId = parameterId;
        this.filmId = filmId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }
}
