package my.spring.service;

import my.spring.domain.Book;
import org.springframework.lang.Nullable;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    int getBooksCount();

    Book getBookById(long id);

    List<Book> getAllBooks();

    boolean addBook(String bookName, @Nullable long authorId, @Nullable long genreId);

    boolean updateBookNameById(String bookName, long id);

    boolean updateBookAuthorById(long author_id, long id);

    boolean updateBookGenreById(long genre_id, long id);

    boolean deleteBookById(long id);
}
