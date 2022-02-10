package my.spring.dao;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations njdbc;

    @Override
    public int getCount() {
        Map<String, Object> params = Collections.singletonMap("id", 1);
        int count = njdbc.queryForObject("select count(*) from book", params, Integer.class);
        return count;
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        Book book = njdbc.queryForObject("select id, name from book where id = :id", params, new BookMapper());
        return book;
    }

    @Override
    public List<Book> getAll() {
        Map<String, Long> params = Collections.singletonMap("id", null);
        List<Book> list = njdbc.queryForList("select id, name from book", params, Book.class);
        return list;
    }

    @Override
    public void insert(Book book) {
        njdbc.update("insert into book (id, name, author_id, genre_id) values (:id, :name, :author_id, :genre_id)",
                Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthorId(), "genre_id", book.getGenreId()));
    }

    @Override
    public void deleteById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        njdbc.update("delete from book where id = :id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long author_id = resultSet.getLong("author_id");
            long genre_id = resultSet.getLong("genre_id");
            return new Book(id, name, author_id, genre_id);
        }
    }
}
