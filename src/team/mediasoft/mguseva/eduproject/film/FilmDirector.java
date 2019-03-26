package team.mediasoft.mguseva.eduproject.film;

public class FilmDirector extends FilmParameter {

    /**
     * Названия для запросов в БД
     */
    public static final String tableName = "film.film_directors";
    public static final String idColumn = "film_director_id";
    public static final String filmIdColumn = "film_id";
    public static final String directorIdColumn = "director_id";

    public FilmDirector() {}

    public FilmDirector(Director director) {
        super(director);
    }

    /**
     * Добавить в фильм текущий текущий экземпляр параметра
     * Режиссер
     *
     * @param film
     */
    @Override
    public void addParameterToFilm(Film film) {
        if (film != null) {
            film.addDirector(this);
        }
    }

    /**
     * Инициализировать экземпляр параметра с помощью parameterName и записать его в параметр текущего экземпляра
     * Привязать режиссера
     *
     * @param directorName
     */
    @Override
    public void setParameterByName(String directorName) {
        if (directorName != null && directorName.length() > 0) {
            this.setParameter(new Director(directorName));
        }
    }

    /**
     * Инициализировать дополнительные поля значениями из списка строк
     *
     * @param addParameters
     */
    @Override
    public void setAddParameter(String[] addParameters) {

    }
}
