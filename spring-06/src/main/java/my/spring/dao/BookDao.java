package my.spring.dao;

import my.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int getCount();

    Book getById(long id);

    List<Book> getAll();

    void insert(Book book);

    void updateNameById(String name, long id);

    void updateAuthorById(long author_id, long id);

    void updateGenreById(long genre_id, long id);

    void deleteById(long id);
}
