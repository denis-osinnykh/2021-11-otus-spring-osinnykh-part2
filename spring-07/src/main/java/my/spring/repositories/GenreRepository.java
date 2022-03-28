package my.spring.repositories;

import my.spring.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findGenreById(long id);

    List<Genre> findAll();
}