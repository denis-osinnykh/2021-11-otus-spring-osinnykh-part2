package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long getCount();

    //Optional<Book> getById(long id);
    Book getById(long id);

    List<Book> getAll();

    void add(Book book);

    void updateNameById(String name, long id);

    void updateAuthorById(Author author, long id);

    void updateGenreById(Genre genre, long id);

    void deleteById(long id);
}
