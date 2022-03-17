package my.spring.service.author;

import lombok.RequiredArgsConstructor;
import my.spring.dao.AuthorDao;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.repositories.AuthorRepository;
import my.spring.repositories.BookRepository;
import my.spring.service.InputOutputService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorJpa;
    private final InputOutputService io;

    public Author getAuthorById(long id) {
        try {
            return authorJpa.getById(id);
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книга не найдена!\n " + e.getMessage(), null);
            return null;
        }
    }

    public List<Author> getAllAuthors() {
        try {
            return authorJpa.getAll();
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Книги не найдены!\n " + e.getMessage(), null);
            return null;
        }
    }
}
