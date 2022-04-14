package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    //@EntityGraph(value = "books-entity-graph")
    Book findBookById(String id);

    //@EntityGraph(value = "books-entity-graph")
    //List<Book> findAll();

    //@Modifying
    //@Query("update Book b set b.name = :name where b.id = :id")

}
