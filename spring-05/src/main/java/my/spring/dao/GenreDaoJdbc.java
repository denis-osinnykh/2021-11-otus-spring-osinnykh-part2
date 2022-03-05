package my.spring.dao;

import lombok.RequiredArgsConstructor;
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
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations njdbc;

    @Override
    public Genre getById(long id) {
        Map<String, Long> params = Collections.singletonMap("id", id);
        Genre genre = njdbc.queryForObject("select id, name from genre " +
                "where id = :id", params, new GenreMapper());
        return genre;
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> list = njdbc.query("select id, name from genre", new GenreMapper());
        return list;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");

            return new Genre(id, name);
        }
    }
}
