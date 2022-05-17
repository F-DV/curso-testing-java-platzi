package com.platzi.javatests.movies.data;

import com.platzi.javatests.movies.model.Movie;

import java.util.Collection;

public interface MovieRepository {

    Movie findbyId(long id);
    Collection<Movie> findAll();
    void saverOrUpdate(Movie movie);
}
