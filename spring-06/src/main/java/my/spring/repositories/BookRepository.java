package my.spring.repositories;

import my.spring.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    long getCount();

    //Optional<Book> getById(long id);
    Book getById(long id);

    List<Book> getAll();
/*
    void insert(Book book);

    void updateNameById(String name, long id);

    void updateAuthorById(long author_id, long id);

    void updateGenreById(long genre_id, long id);

    void deleteById(long id);*/
}
