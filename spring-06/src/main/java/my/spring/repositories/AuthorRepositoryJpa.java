package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
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
public class AuthorRepositoryJpa implements AuthorRepository {
    
    /*private final NamedParameterJdbcOperations njdbc;

    @Override
    public Author getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        Author author = njdbc.queryForObject("select id, name from author " +
                "where id = :id", params, new AuthorMapper());
        return author;
    }

    @Override
    public List<Author> getAll() {
        List<Author> list = njdbc.query("select id, name from author", new AuthorMapper());
        return list;
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            return new Author(id, name);
        }
    }*/
}
