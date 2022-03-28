package my.spring.repository;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Comment;
import my.spring.domain.Genre;
import my.spring.repositories.CommentRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("Jpa для работы с комментариями должен")
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    private static final long EXPECTED_COMMENT_ID = 1;
    private static final int EXPECTED_NUMBER_OF_COMMENTS = 1;
    private static final long EXPECTED_BOOK_ID = 1;
    private static final String EXPECTED_BOOK_NAME = "Test book";
    private static final long EXPECTED_BOOK_AUTHOR_ID = 1;
    private static final String EXPECTED_BOOK_AUTHOR_NAME = "Test author";
    private static final long EXPECTED_BOOK_GENRE_ID = 1;
    private static final String EXPECTED_BOOK_GENRE_NAME = "Test genre";

    private static final long NEW_COMMENT_ID = 2;
    private static final String NEW_COMMENT_TEXT = "Test comment 2";

    @Autowired
    private CommentRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемый комментарий по коду")
    void shouldGetCommentById() {
        Comment expectedComment = em.find(Comment.class, EXPECTED_COMMENT_ID);
        assertEquals(expectedComment, jpa.getById(EXPECTED_COMMENT_ID));
    }

    @Test
    @DisplayName("добавлять комментарий")
    void shouldAddComment() {
        Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, expectedAuthor, expectedGenre);
        Comment expectedNewComment = new Comment(NEW_COMMENT_ID, NEW_COMMENT_TEXT, expectedBook);
        jpa.save(expectedNewComment);
        assertEquals(NEW_COMMENT_TEXT, em.find(Comment.class, NEW_COMMENT_ID).getText());
    }

    @Test
    @DisplayName("обновлять комментарий")
    void shouldUpdateComment() {
        Author expectedAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, EXPECTED_BOOK_AUTHOR_NAME);
        Genre expectedGenre = new Genre(EXPECTED_BOOK_GENRE_ID, EXPECTED_BOOK_GENRE_NAME);
        Book expectedBook = new Book(EXPECTED_BOOK_ID, EXPECTED_BOOK_NAME, expectedAuthor, expectedGenre);
        Comment expectedUpdateComment = new Comment(EXPECTED_COMMENT_ID, NEW_COMMENT_TEXT, expectedBook);
        jpa.save(expectedUpdateComment);
        assertEquals(NEW_COMMENT_TEXT, jpa.getById(EXPECTED_COMMENT_ID).getText());
    }

    @Test
    @DisplayName("возвращать ожидаемый список комментариев")
    void shouldGetAllComments() {
        List<Comment> expectedComments = jpa.getAllByBookId(EXPECTED_BOOK_ID);
        assertThat(expectedComments).isNotNull().hasSize(EXPECTED_NUMBER_OF_COMMENTS)
                .allMatch(s -> !s.getText().equals(""))
                .allMatch(s -> s.getBook() != null)
        ;
    }

    @Test
    @DisplayName("удалять комментарий")
    void shouldDeleteCommentById() {
        jpa.deleteById(EXPECTED_COMMENT_ID);
        assertThat(em.find(Comment.class, EXPECTED_COMMENT_ID)).isNull();
    }
}
