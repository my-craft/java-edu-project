package team.mediasoft.mguseva.eduproject.film;

import java.util.List;

public class FilmGenre extends FilmParameter {

    public FilmGenre() {}

    public FilmGenre(Genre genre) {
        super(genre);
    }

    /**
     * Добавить в фильм текущий текущий экземпляр параметра
     * Жанр
     *
     * @param film
     */
    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addGenre(this);
        }
    }

    /**
     * Инициализировать экземпляр параметра с помощью parameterName и записать его в параметр текущего экземпляра
     * Привязать жанр
     *
     * @param genreName
     */
    @Override
    public void setParameterByName(String genreName) {
        if (genreName != null && genreName.length() > 0) {
            this.setParameter(new Genre(genreName));
        }
    }

    /**
     * Инициализировать дополнительные поля значениями из списка строк
     *
     * @param addParameters
     */
    @Override
    public void setAddParameter(List<String> addParameters) {

    }
}
