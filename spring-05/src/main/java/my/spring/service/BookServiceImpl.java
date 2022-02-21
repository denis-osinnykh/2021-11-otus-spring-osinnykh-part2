package my.spring.service;

import lombok.RequiredArgsConstructor;
import my.spring.dao.BookDao;
import my.spring.domain.Book;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao dao;
    private final InputOutputService io;

    public int getBooksCount() {
        return dao.getCount();
    }

    public Book getBookById(long id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не найдена!\n " + e.getMessage(), null);
            return null;
        }
    }

    public List<Book> getAllBooks() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книги не найдены!\n " + e.getMessage(), null);
            return null;
        }
    }

    public boolean addBook(String bookName, @Nullable long authorId, @Nullable long genreId) {
        Book newBook =  new Book(dao.getCount()+1, bookName, authorId, genreId);
        try {
            dao.insert(newBook);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не добавлена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public boolean deleteBookById(long id) {
        try {
            dao.deleteById(id);
            return true;
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не удалена!\n " + e.getMessage(), null);
            return false;
        }
    }

    public void printBook(Book book) {
        io.printString("Название книги: %s, код книги: %s", new Object[] { book.getName(), book.getId() });
    }

    public void printListBooks(List<Book> books) {
        for (Book book: books) {
            printBook(book);
        }
    }
}
