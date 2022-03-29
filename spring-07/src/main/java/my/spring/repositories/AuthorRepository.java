package my.spring.repositories;

import my.spring.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorById(long id);

    List<Author> findAll();

    Author save(Author author);
}


