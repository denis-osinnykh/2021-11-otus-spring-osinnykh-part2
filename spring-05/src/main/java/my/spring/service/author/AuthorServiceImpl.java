package my.spring.service.author;

import lombok.RequiredArgsConstructor;
import my.spring.dao.AuthorDao;
import my.spring.domain.Author;
import my.spring.service.InputOutputService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;
    private final InputOutputService io;

    public Author getAuthorById(long id) {
        try {
            return authorDao.getById(id);
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Автор не найден!\n " + e.getMessage(), null);
            return null;
        }
    }

    public List<Author> getAllAuthors() {
        try {
            return authorDao.getAll();
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Авторы не найдены!\n " + e.getMessage(), null);
            return null;
        }
    }
}
