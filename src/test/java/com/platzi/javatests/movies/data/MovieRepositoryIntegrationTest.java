package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    @Test
    public void load_all_movies() throws SQLException {

        DataSource dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL","","");       //Instancia el diriver de coneccion a la base de datos

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));   //Realiza conexion a la base de datos

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);                                                       //Trae el Template

        MovieRepositoryJdbc movieRespository = new MovieRepositoryJdbc(jdbcTemplate);

        Collection<Movie> movies = movieRespository.findAll();                                      //Utilizamos el metodo findAll de movieRepository

        assertThat(movies, CoreMatchers.is(Arrays.asList(                                               //Realizamos prueba de integracion para ver si los datos que trae son los los de la base de datos
                new Movie(1, "Dark Knight", 152, Genre.ACTION) ,
                new Movie(2, "Memento", 113, Genre.THRILLER) ,
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

}