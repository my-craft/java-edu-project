package team.mediasoft.mguseva.eduproject.film;

import java.sql.*;

public interface FilmParameterAdderFromDB {
    public void add(Film film, ResultSet resultSet);
}