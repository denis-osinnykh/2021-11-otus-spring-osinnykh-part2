package my.spring.repository;

import my.spring.domain.Genre;
import my.spring.repositories.GenreRepositoryJpa;
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
@DisplayName("Jpa для работы с жанрами должен")
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 2;
    private static final long EXPECTED_BOOK_GENRE_ID = 1;

    @Autowired
    private GenreRepositoryJpa jpa;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("возвращать ожидаемый жанр по коду")
    void shouldGetGenreById() {
        Genre expectedGenre = em.find(Genre.class, EXPECTED_BOOK_GENRE_ID);
        assertEquals(expectedGenre, jpa.getById(EXPECTED_BOOK_GENRE_ID));
    }

    @Test
    @DisplayName("возвращать ожидаемый список авторов")
    void shouldGetAllGenres() {
        List<Genre> expectedGenres = jpa.getAll();
        assertThat(expectedGenres).isNotNull().hasSize(EXPECTED_NUMBER_OF_GENRES)
                .allMatch(s -> s.getName() != null)
        ;
    }
}
