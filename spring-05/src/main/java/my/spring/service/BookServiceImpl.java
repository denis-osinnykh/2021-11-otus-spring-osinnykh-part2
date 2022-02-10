package my.spring.service;

import lombok.RequiredArgsConstructor;
import my.spring.dao.BookDao;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookDao dao;

    public int getBooksCount() {
        return dao.getCount();
    }
}
