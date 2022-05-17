package com.platzi.javatests.movies.service;

import com.platzi.javatests.movies.data.MovieRepository;
import com.platzi.javatests.movies.model.Genre;
import com.platzi.javatests.movies.model.Movie;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MovieServiceTest {

    MovieRepository movieRepository;
    MovieService movieService;

    @Before
    public void setUp(){
        movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION),
                        new Movie(2, "Memento", 113, Genre.THRILLER),
                        new Movie(3, "There's Something About Marty", 119, Genre.COMEDY),
                        new Movie(4, "Super 8", 112, Genre.THRILLER),
                        new Movie(5, "Scream", 111, Genre.HORROR),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY),
                        new Movie(7, "Matrix", 136, Genre.ACTION)
                )
        );
        movieService = new MovieService(movieRepository);
    }
    public List<Integer> getMovieIds(Collection<Movie> movies){
        return movies
                .stream()
                .map(Movie::getId)
                .collect((Collectors.toList()));
    }

    @Test
    public void return_movie_by_genre(){
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);

        List<Integer> moviesIds = movies
                .stream()
                .map(Movie::getId)
                .collect((Collectors.toList()));

        assertThat(moviesIds, CoreMatchers.is(Arrays.asList(3, 6)));
    }

    @Test
    public void return_movie_by_minutes(){
        Collection<Movie> movies = movieService.findMoviesByMinutes(120);

        List<Integer> moviesIds = getMovieIds(movies);

        assertThat(moviesIds, CoreMatchers.is(Arrays.asList(2,3,4,5,6)));
    }



}