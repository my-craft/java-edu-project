package team.mediasoft.mguseva.eduproject.film;

import java.util.List;

public class FilmGenre extends FilmParameter {

    public FilmGenre() {}

    public FilmGenre(Genre genre) {
        super(genre);
    }

    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addGenre(this);
        }
    }

    @Override
    public void setParameterByName(String genreName) {
        if (genreName != null && genreName.length() > 0) {
            this.setParameter(new Genre(genreName));
        }
    }

    @Override
    public void setAddParameter(List<String> addParameters) {

    }
}
