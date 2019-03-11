package team.mediasoft.mguseva.eduproject.comments;

import team.mediasoft.mguseva.eduproject.film.Film;

import java.io.*;
import java.util.*;

public class FilmChooser extends StringReader {

    private List<Film> films;

    public FilmChooser() {
        super();
        this.setMessages();
    }

    public FilmChooser(List<Film> films) {
        this.films = films;
        this.setMessages();
    }

    private void setMessages() {
        this.setInputMessage("Введите номер: ");
        this.setOutputMessage("Вы выбрали фильм ");
        this.setErrorMessage("Вы ввели неправильный номер фильма, попробуйте ещё раз");
    }

    /**
     * Вывод задачи на ввод
     *
     * @throws IllegalArgumentException
     */
    @Override
    protected void outputTask() throws IllegalArgumentException {
        if (this.films == null || this.films.size() < 1) {
            throw new IllegalArgumentException("Нет фильмов для комментирования");
        }

        System.out.println("Какой фильм вы хотите прокомментировать?");
        for (Film film : this.films) {
            System.out.println(film.getId() + " - " + film.getName());
        }

        super.outputTask();
    }

    /**
     * Ввод данных
     *
     * @param reader
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    @Override
    protected Object inputInfo(BufferedReader reader) throws IOException, IllegalArgumentException {
        if (this.films == null || this.films.size() < 1) {
            throw new IllegalArgumentException("Нет фильмов для комментирования");
        }

        return super.inputInfo(reader);
    }

    /**
     * Проверка введеной строки
     *
     * @param inputStr
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    protected Object getValueFromBuffer(String inputStr) throws IllegalArgumentException {
        int filmId = this.getIntegerFromString(inputStr);

        if (filmId != 0) {
            Film film = this.findFilmById(filmId);
            if (film != null) {
                System.out.println(this.getOutputMessage() + film.getName());
                return film;
            }
        }

        throw new IllegalArgumentException(this.getErrorMessage());
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
