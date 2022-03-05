package my.spring.dao;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
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
        Integer count = njdbc.queryForObject("select count(*) from book", params, Integer.class);
        return count == null? 0: count;
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        Book book = njdbc.queryForObject("select b.id, b.name, b.author_id, b.genre_id, a.name as a_name, g.name as g_name from book b " +
                "inner join author a on b.author_id = a.id " +
                "inner join genre g on b.genre_id = g.id " +
                "where b.id = :id", params, new BookMapper());
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> list = njdbc.query("select b.id, b.name, b.author_id, b.genre_id, a.name as a_name, g.name as g_name from book b " +
                "inner join author a on b.author_id = a.id " +
                "inner join genre g on b.genre_id = g.id ", new BookMapper());
        return list;
    }

    @Override
    public void insert(Book book) {
        njdbc.update("insert into book (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                Map.of("name", book.getName()
                        , "author_id", book.getAuthor().getId()
                        , "genre_id", book.getGenre().getId()
                ));
    }

    @Override
    public void updateNameById(String name, long id) {
        njdbc.update("update book set name = :name where id = :id",
                Map.of("name", name,
                        "id", id));
    }

    @Override
    public void updateAuthorById(long author_id, long id) {
        njdbc.update("update book set author_id = :author_id where id = :id",
                Map.of("author_id", author_id,
                        "id", id));
    }

    @Override
    public void updateGenreById(long genre_id, long id) {
        njdbc.update("update book set genre_id = :genre_id where id = :id",
                Map.of("genre_id", genre_id,
                        "id", id));
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
            Author author = new Author(resultSet.getLong("author_id"), resultSet.getString("a_name"));
            Genre genre = new Genre(resultSet.getLong("genre_id"), resultSet.getString("g_name"));

            return new Book(id, name, author, genre);
        }
    }
}
