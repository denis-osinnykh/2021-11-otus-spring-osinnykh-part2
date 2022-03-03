package my.spring.service;

import my.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(long id);

    List<Genre> getAllGenres();
    
}
