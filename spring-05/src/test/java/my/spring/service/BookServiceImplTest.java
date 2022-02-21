package my.spring.service;

import lombok.RequiredArgsConstructor;
import my.spring.dao.BookDao;
import my.spring.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Класс BookServiceImpl должен")
public class BookServiceImplTest {

    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Test book 1";
    private static final long EXPECTED_BOOK_AUTHOR_ID = 1;
    private static final long EXPECTED_BOOK_GENRE_ID = 1;

    private static final long NOT_EXPECTED_BOOK_ID = -1;
    private static final long DELETED_BOOK_ID = 2;

    private static final long NEW_BOOK_ID = 10;
    private static final String NEW_BOOK_NAME = "Test book 10";
    private static final long NEW_BOOK_AUTHOR_ID = 1;
    private static final long NEW_BOOK_GENRE_ID = 1;

    @MockBean
    private BookDao dao;
    @Autowired
    private BookService bs;

    /*@Autowired
    BookServiceImplTest(BookServiceImpl bs) { this.bs = bs; }*/

    @Test
    @DisplayName("возвращать количество книг")
    void shouldGetBooksCount() {
        //assertEquals(1, bs.getBooksCount());
        assertThat(bs.getBooksCount()).isNotNull();
    }

    @Test
    @DisplayName("возвращать книгу по коду")
    void shouldGetBookById() {
        given(this.dao.getById(EXPECTED_BOOK_ID))
                .willReturn(new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID));
        assertThat(bs.getBookById(EXPECTED_BOOK_ID)).isNotNull();
    }

    @Test
    @DisplayName("возвращать null, если книга по коду не найдена")
    void shouldGetEmptyBookById() {
        given(this.dao.getById(NOT_EXPECTED_BOOK_ID))
                .willReturn(null);
        assertEquals(null, bs.getBookById(NOT_EXPECTED_BOOK_ID));
    }

    @Test
    @DisplayName("возвращать true при добавлении, если книга была добавлена")
    void shouldReturnTrueAfterAdding() {
        Book newBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME, NEW_BOOK_AUTHOR_ID, NEW_BOOK_GENRE_ID);
        doNothing().when(this.dao).insert(newBook);
        boolean result = bs.addBook(NEW_BOOK_NAME, NEW_BOOK_AUTHOR_ID, NEW_BOOK_GENRE_ID);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("возвращать false при добавлении, если книга добавлена не была")
    void shouldReturnFalseAfterAdding() {
        Book newBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID);
        doThrow(new DuplicateKeyException("")).when(this.dao).insert(newBook);
        boolean result = bs.addBook(EXPECTED_BOOK_NAME, EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_GENRE_ID);
        assertEquals(false, result);
    }

    @Test
    @DisplayName("возвращать список книг")
    void shouldGetNullAllBooks() {
        given(this.dao.getAll())
                .willReturn(new ArrayList<>());
        assertThat(bs.getAllBooks()).isNotNull();
    }

    @Test
    @DisplayName("возвращать true, если книга удалена")
    void shouldReturnTrueAfterDeleting() {
        doNothing().when(this.dao).deleteById(DELETED_BOOK_ID);
        boolean result = bs.deleteBookById(DELETED_BOOK_ID);
        assertEquals(true, result);
    }

    @Test
    @DisplayName("возвращать false, если книга не была удалена")
    void shouldReturnFalseAfterDeleting() {
        doThrow(new EmptyResultDataAccessException(1)).when(this.dao).deleteById(NOT_EXPECTED_BOOK_ID);
        boolean result = bs.deleteBookById(NOT_EXPECTED_BOOK_ID);
        assertEquals(false, result);
    }
}
