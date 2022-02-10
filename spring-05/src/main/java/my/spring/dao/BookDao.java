package my.spring.dao;

import my.spring.domain.Book;

import java.util.List;

public interface BookDao {
    int getCount();

    Book getById(long id);

    List<Book> getAll();

    void insert(Book book);

    void deleteById(long id);
}
