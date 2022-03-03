package my.spring.shell;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import my.spring.service.*;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {
    private final BookService bs;
    private final AuthorService as;
    private final GenreService gs;
    private final InputOutputService io;
    private final BookPrintService bps;
    private final AuthorPrintService aps;
    private final GenrePrintService gps;

    @ShellMethod(value = "Get count of books", key = {"gcb", "get count"})
    public void getBooksCount() {
        int count = bs.getBooksCount();
        io.printString("Количество книг: %s", new Object[] { count });
    }

    @ShellMethod(value = "Get book by id", key = {"gbi", "get book"})
    public void getBookById(@ShellOption long id) {
        Book book = bs.getBookById(id);
        if (book != null)
            bps.printBook(book);
    }

    @ShellMethod(value = "Get all books", key = {"gab", "get all books"})
    public void getAllBooks() {
        List<Book> books = bs.getAllBooks();
        if (books != null)
            bps.printListBooks(books);
    }
    
    @ShellMethod(value = "Add the book", key = {"ab", "add book"})
    public void addBooks(@ShellOption String bookName, @ShellOption(defaultValue="1") long authorId, @ShellOption(defaultValue="1") long genreId) {
        boolean result = bs.addBook(bookName, authorId, genreId);
        if (result)
            io.printString("Книга добавлена!", null);
    }

    @ShellMethod(value = "Update the book name by id", key = {"ubi", "update book name"})
    public void UpdateBookNameById(@ShellOption long id, @ShellOption String bookName) {
        boolean result = bs.updateBookNameById(bookName, id);
        if (result)
            io.printString("Книга обновлена!", null);
    }

    @ShellMethod(value = "Update the book author by id", key = {"uba", "update book author"})
    public void UpdateBookAuthorById(@ShellOption long id, @ShellOption long authorId) {
        boolean result = bs.updateBookAuthorById(authorId, id);
        if (result)
            io.printString("Автор у книги обновлен!", null);
    }

    @ShellMethod(value = "Update the book genre by id", key = {"ubg", "update book genre"})
    public void UpdateBookGenreById(@ShellOption long id, @ShellOption long genreId) {
        boolean result = bs.updateBookGenreById(genreId, id);
        if (result)
            io.printString("Жанр у книги обновлен!", null);
    }

    @ShellMethod(value = "Delete the book", key = {"db", "delete book"})
    public void deleteBookById(@ShellOption long id) {
        boolean result = bs.deleteBookById(id);
        if (result)
            io.printString("Книга удалена!", null);
    }

    @ShellMethod(value = "Get all authors", key = {"gaa", "get all authors"})
    public void getAllAuthors() {
        List<Author> authors = as.getAllAuthors();
        if (authors != null)
            aps.printListAuthors(authors);
    }

    @ShellMethod(value = "Get all genres", key = {"gag", "get all genres"})
    public void getAllGenres() {
        List<Genre> genres = gs.getAllGenres();
        if (genres != null)
            gps.printListGenres(genres);
    }
}
