package team.mediasoft.mguseva.eduproject.film;

import java.util.List;

public class FilmDirector extends FilmParameter {

    public FilmDirector() {}

    public FilmDirector(Director director) {
        super(director);
    }

    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addDirector(this);
        }
    }

    @Override
    public void setParameterByName(String directorName) {
        if (directorName != null && directorName.length() > 0) {
            this.setParameter(new Director(directorName));
        }
    }

    @Override
    public void setAddParameter(List<String> addParameters) {

    }
}
