package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieRepositoryIntegrationTest {

    private MovieRepositoryJdbc movieRespository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception{
        dataSource =
                new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL","","");       //Instancia el diriver de coneccion a la base de datos

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));   //Realiza conexion a la base de datos

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);                                                       //Trae el Template

        movieRespository = new MovieRepositoryJdbc(jdbcTemplate);
    }

    @Test
    public void load_all_movies() throws SQLException {

        Collection<Movie> movies = movieRespository.findAll();                                      //Utilizamos el metodo findAll de movieRepository

        assertThat(movies, CoreMatchers.is(Arrays.asList(                                               //Realizamos prueba de integracion para ver si los datos que trae son los los de la base de datos
                new Movie(1, "Dark Knight", 152, Genre.ACTION) ,
                new Movie(2, "Memento", 113, Genre.THRILLER) ,
                new Movie(3, "Matrix", 136, Genre.ACTION)
        )));
    }

    @Test
    public void load_movie_by_id(){
        Movie movie = movieRespository.findbyId(2);
        assertThat(movie, CoreMatchers.is(new Movie(2, "Memento", 113, Genre.THRILLER)));
    }

    /**
     * Borramos la base de datos cada que ejecutamos un test,
     * esto se hace con el fin de que no se acomulen en la base
     * de datos
     * @throws Exception
     */
    @Test
    public void insert_a_movie(){
        Movie movie = new Movie("super 8",112,Genre.THRILLER);  //Creamos la pelicula que vamos a guardar

        movieRespository.saverOrUpdate(movie);                                  //Se guarda la pelicula creada anteriormente

        Movie movieFromDb = movieRespository.findbyId(4);                       //Buscamos la pelicula que acabamos de ingresar

        assertThat(movieFromDb,CoreMatchers.is(new Movie(4,"super 8",112,Genre.THRILLER)));                         //Testeamos que si devuelva la pelicula que ingreamos
    }

    @After
    public void tearDown() throws Exception {

        // Remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files"); // "shutdown" is also enough for mem db
    }
}