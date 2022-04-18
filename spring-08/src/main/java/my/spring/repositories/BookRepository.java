package my.spring.repositories;

import my.spring.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

    Book findBookById(String id);
}
