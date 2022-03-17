package my.spring.dao;

import my.spring.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);

    List<Author> getAll();
}
