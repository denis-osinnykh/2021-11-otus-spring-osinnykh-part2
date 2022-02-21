package my.spring.shell;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Book;
import my.spring.service.BookService;
import my.spring.service.InputOutputService;
import org.springframework.lang.Nullable;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {
    private final BookService bs;
    private final InputOutputService io;

    @ShellMethod(value = "Get count of books", key = {"gcb", "get count"})
    public void getBooksCount() {
        int count = bs.getBooksCount();
        io.printString("Количество книг: %s", new Object[] { count });
    }

    @ShellMethod(value = "Get book by id", key = {"gbi", "get book"})
    public void getBookById(@ShellOption long id) {
        Book book = bs.getBookById(id);
        if (book != null)
            bs.printBook(book);
    }

    @ShellMethod(value = "Get all books", key = {"gab", "get all book"})
    public void getAllBooks() {
        List<Book> books = bs.getAllBooks();
        if (books != null)
            bs.printListBooks(books);
    }

    /*
    Не понял как сделать команду с необязательными параметрами.
    Хотелось сделать возможность добавлять книгу без автора и жанра.
    */
    @ShellMethod(value = "Add the book", key = {"ab", "add book"})
    public void addBooks(@ShellOption String bookName, @ShellOption @Nullable Long authorId, @ShellOption @Nullable Long genreId) {
        boolean result = bs.addBook(bookName, authorId, genreId);
        if (result)
            io.printString("Книга добавлена!", null);
    }

    @ShellMethod(value = "Delete the book", key = {"db", "delete book"})
    public void deleteBookById(@ShellOption long id) {
        boolean result = bs.deleteBookById(id);
        if (result)
            io.printString("Книга удалена!", null);
    }
}
