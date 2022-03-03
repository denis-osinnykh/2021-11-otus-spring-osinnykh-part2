package my.spring.service;

import my.spring.domain.Genre;

import java.util.List;

public interface GenrePrintService {
    void printGenre(Genre genre);

    void printListGenres(List<Genre> genres);
}
