package my.spring.service.genre;

import lombok.RequiredArgsConstructor;
import my.spring.dao.GenreDao;
import my.spring.domain.Genre;
import my.spring.service.InputOutputService;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreDao genreDao;
    private final InputOutputService io;

    public Genre getGenreById(long id) {
        try {
            return genreDao.getById(id);
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Жанр не найден!\n " + e.getMessage(), null);
            return null;
        }
    }

    public List<Genre> getAllGenres() {
        try {
            return genreDao.getAll();
        } catch (Exception e) {
            io.printString("Ошибка выполнения запроса! Жанр не найден!\n " + e.getMessage(), null);
            return null;
        }
    }
}
