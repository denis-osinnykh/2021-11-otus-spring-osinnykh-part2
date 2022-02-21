package my.spring.dao;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@DisplayName("Dao для работы с книгами должен")
@Import(BookDaoJdbc.class)
public class BookDaoJdbcTest {

    private static final int EXPECTED_BOOKS_COUNT = 1;
    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Test book";
    private static final long EXPECTED_BOOK_AUTHOR_ID = 1;
    private static final long EXPECTED_BOOK_GENRE_ID = 1;

    private static final long NEW_BOOK_ID = 2;
    private static final String NEW_BOOK_NAME = "Test book 2";
    private static final long NEW_BOOK_AUTHOR_ID = 1;
    private static final long NEW_BOOK_GENRE_ID = 1;

    @Autowired
    private BookDaoJdbc dao;

    @Test
    @DisplayName("возвращать ожидаемое количество книг")
    void shouldGetBooksCount() {
        int actualBooksCount = dao.getCount();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу по коду")
    void shouldGetBookById() {
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID);
        assertEquals(expectedBook, dao.getById(EXPECTED_BOOK_ID));
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() {
        Book expectedNewBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME, NEW_BOOK_AUTHOR_ID, NEW_BOOK_GENRE_ID);
        dao.insert(expectedNewBook);
        assertEquals(expectedNewBook, dao.getById(NEW_BOOK_ID));
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void shouldGetAllBooks() {
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID));
        assertEquals(expectedBooks, dao.getAll());
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBookById() {
        dao.deleteById(EXPECTED_BOOK_ID);
        assertEquals(0, dao.getCount());
    }
}
