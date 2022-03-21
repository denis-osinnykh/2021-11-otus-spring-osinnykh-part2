package my.spring.repository;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import my.spring.repositories.BookRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("Dao для работы с книгами должен")
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {

    private static final long EXPECTED_BOOKS_COUNT = 1;
    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Test book";
    private static final long EXPECTED_BOOK_AUTHOR_ID = 1;
    private static final String EXPECTED_BOOK_AUTHOR_NAME = "Test author";
    private static final long EXPECTED_BOOK_GENRE_ID = 1;
    private static final String EXPECTED_BOOK_GENRE_NAME = "Test genre";
    private static final int EXPECTED_NUMBER_OF_BOOKS = 1;

    private static final long NEW_BOOK_ID = 2;
    private static final String NEW_BOOK_NAME = "Test book 2";
    private static final long NEW_BOOK_AUTHOR_ID = 2;
    private static final String NEW_BOOK_AUTHOR_NAME = "Test author 2";
    private static final long NEW_BOOK_GENRE_ID = 2;
    private static final String NEW_BOOK_GENRE_NAME = "Test genre 2";

    @Autowired
    private BookRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемое количество книг")
    void shouldGetBooksCount() {
        long actualBooksCount = jpa.getCount();
        assertEquals(EXPECTED_BOOKS_COUNT, actualBooksCount);
    }

    @Test
    @DisplayName("возвращать ожидаемую книгу по коду")
    void shouldGetBookById() {
        Book expectedBook = em.find(Book.class, EXPECTED_BOOK_ID);
        assertEquals(expectedBook, jpa.getById(EXPECTED_BOOK_ID));
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() {
        Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        Book expectedNewBook = new Book(NEW_BOOK_ID, NEW_BOOK_NAME, expectedAuthor, expectedGenre);
        jpa.add(expectedNewBook);
        assertEquals(expectedNewBook, jpa.getById(NEW_BOOK_ID));
    }

    @Test
    @DisplayName("обновлять книгу")
    void shouldUpdateBook() {
        Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        //Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        //Book expectedUpdateBook = new Book(EXPECTED_BOOK_ID, NEW_BOOK_NAME, expectedAuthor, expectedGenre);
        jpa.updateNameById(NEW_BOOK_NAME, EXPECTED_BOOK_ID);
        assertEquals(NEW_BOOK_NAME, jpa.getById(EXPECTED_BOOK_ID).getName());
    }

    @Test
    @DisplayName("обновлять автора у книги")
    void shouldUpdateAuthorBook() {
        Author newAuthor = new Author(NEW_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        //Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        //Book expectedUpdateBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, newAuthor, expectedGenre);
        jpa.updateAuthorById(newAuthor, EXPECTED_BOOK_ID);
        assertEquals(newAuthor, jpa.getById(EXPECTED_BOOK_ID).getAuthor());
    }

    @Test
    @DisplayName("обновлять жанр у книги")
    void shouldUpdateGenreBook() {
        //Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        Genre newGenre = new Genre(NEW_BOOK_GENRE_ID, NEW_BOOK_GENRE_NAME);
        //Book expectedUpdateBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, expectedAuthor, newGenre);
        jpa.updateGenreById(newGenre, EXPECTED_BOOK_ID);
        assertEquals(newGenre, jpa.getById(EXPECTED_BOOK_ID).getGenre());
    }

    @Test
    @DisplayName("возвращать ожидаемый список книг")
    void shouldGetAllBooks() {
        List<Book> expectedBooks = jpa.getAll();
        assertThat(expectedBooks).isNotNull().hasSize(EXPECTED_NUMBER_OF_BOOKS)
                .allMatch(s -> !s.getName().equals(""))
                .allMatch(s -> s.getAuthor() != null)
                .allMatch(s -> s.getGenre() != null);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBookById() {
        jpa.deleteById(EXPECTED_BOOK_ID);
        assertEquals(0, jpa.getCount());
    }
}
