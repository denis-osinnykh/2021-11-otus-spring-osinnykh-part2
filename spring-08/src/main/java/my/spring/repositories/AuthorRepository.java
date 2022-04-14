package my.spring.repositories;

import my.spring.domain.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

   Author findAuthorById(String id);
}


