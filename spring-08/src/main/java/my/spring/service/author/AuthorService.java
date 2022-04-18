package my.spring.service.author;

import my.spring.domain.Author;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(String id);

    List<Author> getAllAuthors();

    boolean addAuthor(String name);
}
