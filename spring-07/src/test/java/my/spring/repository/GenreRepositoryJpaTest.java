package my.spring.repository;

import my.spring.repositories.GenreRepository;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("Jpa для работы с жанрами должен")
@Import(GenreRepository.class)
public class GenreRepositoryJpaTest {

    private static final int EXPECTED_NUMBER_OF_GENRES = 2;
    private static final long EXPECTED_BOOK_GENRE_ID = 1;

    @Autowired
    private GenreRepository jpa;

    @Autowired
    private TestEntityManager em;
/*
    @Test
    @DisplayName("возвращать ожидаемый жанр по коду")
    void shouldGetGenreById() {
        Genre expectedGenre = em.find(Genre.class, EXPECTED_BOOK_GENRE_ID);
        assertEquals(expectedGenre, jpa.findGenreById(EXPECTED_BOOK_GENRE_ID));
    }

    @Test
    @DisplayName("возвращать ожидаемый список авторов")
    void shouldGetAllGenres() {
        List<Genre> expectedGenres = jpa.getAll();
        assertThat(expectedGenres).isNotNull().hasSize(EXPECTED_NUMBER_OF_GENRES)
                .allMatch(s -> s.getName() != null)
        ;
    }*/
}
