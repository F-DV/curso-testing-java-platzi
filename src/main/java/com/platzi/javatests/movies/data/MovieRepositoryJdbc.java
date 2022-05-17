package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class MovieRepositoryJdbc implements MovieRepository {

    /**
       La clase JdbcTemplate es proporcionada por la libreria spring
     */
    private JdbcTemplate jdbcTemplate;

    public MovieRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Movie findbyId(long id) {
        Object[] args = {id};
        return jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?",args,movieMapper);
    }

    /**
        Este metodo realiza una consulta  a la base de datos y transforma cada pelicula en
        un objeto movie
     */
    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies",movieMapper);
    }

    /**
     * Guardamos peliculas en la base de datos
     * @param movie
     */
    @Override
    public void saverOrUpdate(Movie movie) {

        jdbcTemplate.update("insert into movies (name,minutes,genre) values (?,?,?)",
                movie.getName(),movie.getMinutes(),movie.getGenre().toString());
    }
    /*
        La dependencia de spring-jdbc nos permite utilizar RowMapper para ingresar a la base de datos
        y convertir cada fila en un objeto Movie
     */
    private static RowMapper<Movie> movieMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("minutes"),
            Genre.valueOf(rs.getString("genre")));
}
