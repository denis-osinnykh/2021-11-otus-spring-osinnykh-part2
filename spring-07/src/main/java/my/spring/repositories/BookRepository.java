package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    long countAllBy();

    Book findBookById(long id);

    @EntityGraph(value = "books-entity-graph")
    List<Book> findAll();

    Book save(Book book);

    /*void updateNameById(String name, long id);

    void updateAuthorById(Author author, long id);

    void updateGenreById(Genre genre, long id);*/

    void deleteById(long id);
}
