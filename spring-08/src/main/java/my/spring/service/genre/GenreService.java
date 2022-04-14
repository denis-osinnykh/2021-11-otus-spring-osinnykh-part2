package my.spring.service.genre;

import my.spring.domain.Genre;

import java.util.List;

public interface GenreService {
    Genre getGenreById(String id);

    List<Genre> getAllGenres();
    
}
