package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long getCount() {
        long count = em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
        return count;
    }

    /*@Override
    public Optional<Book> getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "where b.id = :id", Book.class);
        query.setParameter("id", id);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }*/

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b " +
                "where b.id = :id", Book.class);
        query.setParameter("id", id);
            return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
         return em.createQuery("select b from Book b ", Book.class).getResultList();
    }
/*
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
    }*/
}
