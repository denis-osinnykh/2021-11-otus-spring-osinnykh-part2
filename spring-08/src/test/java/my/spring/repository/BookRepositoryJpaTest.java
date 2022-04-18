package my.spring.repository;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import my.spring.repositories.BookRepository;
import my.spring.repositories.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataMongoTest
@EnableConfigurationProperties
@DisplayName("Jpa для работы с книгами должен")
public class BookRepositoryJpaTest {

    private static final String EXPECTED_BOOKS_COUNT = "1";
    private static final String EXPECTED_BOOK_ID = "1";
    private static final String EXPECTED_BOOK_NAME = "Test book";
    private static final String EXPECTED_BOOK_AUTHOR_ID = "1";
    private static final String EXPECTED_BOOK_AUTHOR_NAME = "Test author";
    private static final String EXPECTED_BOOK_GENRE_ID = "1";
    private static final String EXPECTED_BOOK_GENRE_NAME = "Test genre";
    private static final String EXPECTED_BOOK_COMMENT_TEXT= "Same comment";

    private static final String NEW_BOOK_ID = "2";
    private static final String NEW_BOOK_NAME = "Test book 2";
    private static final String NEW_BOOK_AUTHOR_ID = "2";
    private static final String NEW_BOOK_AUTHOR_NAME = "Test author 2";
    private static final String NEW_BOOK_GENRE_ID = "2";
    private static final String NEW_BOOK_GENRE_NAME = "Test genre 2";

    @Autowired
    private BookRepository jpaBook;
    @Autowired
    private CommentRepository jpaComment;

    @Test
    @DisplayName("добавлять книгу корректно")
    void shouldAddBook() {
        Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        Book expectedNewBook = new Book(NEW_BOOK_NAME, expectedAuthor, expectedGenre);
        jpaBook.save(expectedNewBook);
        assertThat(jpaBook.findAll().get(1)).isNotNull()
                .hasFieldOrPropertyWithValue("name", NEW_BOOK_NAME)
                .hasFieldOrPropertyWithValue("author", expectedAuthor)
                .hasFieldOrPropertyWithValue("genre", expectedGenre)
        ;
    }

    @Test
    @DisplayName("обновлять книгу")
    void shouldUpdateBook() {
        Book book = jpaBook.findAll().get(0);
        book.setName(NEW_BOOK_NAME);
        jpaBook.save(book);
        assertEquals(NEW_BOOK_NAME, jpaBook.findAll().get(0).getName());
    }

    @Test
    @DisplayName("обновлять автора у книги")
    void shouldUpdateAuthorBook() {
        Book book = jpaBook.findAll().get(0);
        Author newAuthor = new Author(NEW_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        book.setAuthor(newAuthor);
        jpaBook.save(book);
        assertEquals(newAuthor, jpaBook.findAll().get(0).getAuthor());
    }

    @Test
    @DisplayName("обновлять жанр у книги")
    void shouldUpdateGenreBook() {
        Book book = jpaBook.findAll().get(0);
        Genre newGenre = new Genre(NEW_BOOK_GENRE_ID, NEW_BOOK_GENRE_NAME);
        book.setGenre(newGenre);
        jpaBook.save(book);
        assertEquals(newGenre, jpaBook.findAll().get(0).getGenre());
    }

    @Test
    @DisplayName("возвращать корректный комментарий по коду книги")
    void shouldGetAllCommentByBookId() {
        Book book = jpaBook.findAll().get(0);
        assertThat(jpaComment.findAllByBookId(book.getId()).get(0))
                .isNotNull()
                .hasFieldOrPropertyWithValue("text", EXPECTED_BOOK_COMMENT_TEXT)
        ;
        assertNotEquals(jpaComment.findAllByBookId(book.getId()).get(0).getId(), null);
    }
}
