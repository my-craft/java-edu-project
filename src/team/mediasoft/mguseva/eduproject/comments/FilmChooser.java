package team.mediasoft.mguseva.eduproject.comments;

import team.mediasoft.mguseva.eduproject.film.Film;

import java.io.*;
import java.util.*;

public class FilmChooser extends Reader {

    private List<Film> films;

    public FilmChooser() {
        super();
    }

    public FilmChooser(List<Film> films) {
        this.films = films;
    }

    /**
     * Вывод задачи на ввод
     */
    @Override
    protected void outputTask() throws Exception {
        if (this.films == null) {
            throw new Exception("Нет фильмов для комментирования");
        }

        System.out.println("Какой фильм вы хотите прокомментировать?");
        for (Film film : this.films) {
            System.out.println(film.getId() + " - " + film.getName());
        }

        System.out.print("Введите номер: ");
    }

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     */
    @Override
    protected Film inputInfo(BufferedReader reader) throws Exception {
        if (this.films == null) {
            throw new Exception("Нет фильмов для комментирования");
        }

        try {
            String filmNumber = reader.readLine();

            int filmId = this.getIntegerFromString(filmNumber);

            if (filmId != 0) {
                Film film = this.findFilmById(filmId);
                if (film != null) {
                    System.out.println("Вы выбрали фильм " + film.getName());
                    return film;
                }
            }
        } catch (Exception e) {}

        System.out.println("Вы ввели неправильный номер фильма, попробуйте ещё раз");

        return null;
    }

    /**
     * Найти фильм по его id
     *
     * @param filmId
     * @return
     */
    private Film findFilmById(int filmId) {
        for (Film film : this.films) {
            if (film.getId() == filmId) {
                return film;
            }
        }

        return null;
    }
}
