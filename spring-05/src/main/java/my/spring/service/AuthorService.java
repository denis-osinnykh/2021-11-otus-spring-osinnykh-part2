package my.spring.service;

import my.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(long id);

    List<Author> getAllAuthors();
}
