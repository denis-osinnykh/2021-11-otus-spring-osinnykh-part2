package my.spring.repositories;

import my.spring.domain.Genre;

import java.util.List;

public interface GenreRepository {

    Genre getById(long id);

    List<Genre> getAll();
}