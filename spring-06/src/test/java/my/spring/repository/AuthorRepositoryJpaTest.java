package my.spring.repository;

import my.spring.domain.Author;
import my.spring.repositories.AuthorRepositoryJpa;
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
@DisplayName("Jpa для работы с авторами должен")
@Import(AuthorRepositoryJpa.class)
public class AuthorRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_AUTHORS = 2;
    private static final long EXPECTED_BOOK_AUTHOR_ID = 1;

    private static final String NEW_BOOK_AUTHOR_NAME = "Test author 2";

    @Autowired
    private AuthorRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемого автора по коду")
    void shouldGetAuthorById() {
        Author expectedAuthor= em.find(Author.class, EXPECTED_BOOK_AUTHOR_ID);
        assertEquals(expectedAuthor, jpa.getById(EXPECTED_BOOK_AUTHOR_ID));
    }

    @Test
    @DisplayName("обновлять автора")
    void shouldUpdateAuthor() {
        Author expectedUpdateAuthor = new Author(EXPECTED_BOOK_AUTHOR_ID, NEW_BOOK_AUTHOR_NAME);
        jpa.save(expectedUpdateAuthor);
        assertEquals(NEW_BOOK_AUTHOR_NAME, jpa.getById(EXPECTED_BOOK_AUTHOR_ID).getName());
    }

    @Test
    @DisplayName("возвращать ожидаемый список авторов")
    void shouldGetAllAuthors() {
        List<Author> expectedAuthors = jpa.getAll();
        assertThat(expectedAuthors).isNotNull().hasSize(EXPECTED_NUMBER_OF_AUTHORS)
                .allMatch(s -> s.getName() != null)
        ;
    }
}
