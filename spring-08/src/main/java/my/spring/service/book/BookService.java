package my.spring.service.book;

import my.spring.domain.Book;
import my.spring.domain.Comment;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BookService {

    long getBooksCount();

    Book getBookById(String id);

    List<Book> getAllBooks();

    boolean addBook(String bookName, @Nullable String authorId, @Nullable String genreId);

    boolean updateBookNameById(String bookName, String id);

    boolean updateBookAuthorById(String author_id, String id);

    boolean updateBookGenreById(String genre_id, String id);

    boolean deleteBookById(String id);

    List<Comment> getAllCommentsByBookId(String id);
}
