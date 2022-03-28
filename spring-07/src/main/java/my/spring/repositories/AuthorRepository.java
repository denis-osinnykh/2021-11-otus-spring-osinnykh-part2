package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorById(long id);

    List<Author> findAll();

    Author save(Author author);
}


