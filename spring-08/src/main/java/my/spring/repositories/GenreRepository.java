package my.spring.repositories;

import my.spring.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Genre findGenreById(String id);
}