package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Comment;

import java.util.List;

public interface AuthorRepository {

    Author getById(long id);

    List<Author> getAll();

    Author save(Author author);
}
