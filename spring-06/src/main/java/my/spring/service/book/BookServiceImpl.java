package my.spring.service.book;

import lombok.RequiredArgsConstructor;
import my.spring.dao.AuthorDao;
import my.spring.dao.BookDao;
import my.spring.dao.GenreDao;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import my.spring.repositories.BookRepository;
import my.spring.service.InputOutputService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final InputOutputService io;
    private final BookRepository bookJpa;

    public long getBooksCount() {
        return bookJpa.getCount();
    }

    public Book getBookById(long id) {
        try {
            return bookJpa.getById(id);
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не найдена!\n " + e.getMessage(), null);
            return null;
        }
    }

    public List<Book> getAllBooks() {
        try {
            return bookJpa.getAll();
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книги не найдены!\n " + e.getMessage(), null);
            return null;
        }
    }

    public boolean addBook(String bookName, long authorId, long genreId) {
        try {
            Author author = authorDao.getById(authorId);
            Genre genre = genreDao.getById(genreId);
            Book newBook =  new Book(0, bookName, null, null);

            bookJpa.save(newBook);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не добавлена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public boolean updateBookNameById(String bookName, long id) {
        try {
            bookDao.updateNameById(bookName, id);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не обновлена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public boolean updateBookAuthorById(long author_id, long id) {
        try {
            bookDao.updateAuthorById(author_id, id);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не обновлена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public boolean updateBookGenreById(long genre_id, long id) {
        try {
            bookDao.updateGenreById(genre_id, id);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не обновлена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public boolean deleteBookById(long id) {
        try {
            bookDao.deleteById(id);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не удалена!\n " + e.getMessage(), null);
            return false;
        }
    }
}
