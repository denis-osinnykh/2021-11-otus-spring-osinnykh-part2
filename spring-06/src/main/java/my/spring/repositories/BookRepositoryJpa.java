package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//TODO @Transactional
@RequiredArgsConstructor
@Repository
@Transactional
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

    @Override
    public void save(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        }
        else {
            em.merge(book);
        }
    }

    @Override
    public void updateNameById(String name, long id) {
        Query query = em.createQuery("update Book set name = :name where id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateAuthorById(long author_id, long id) {
        Query query = em.createQuery("update Book set author = :author_id where id = :id");
        query.setParameter("author_id", author_id);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateGenreById(long genre_id, long id) {
        Query query = em.createQuery("update Book set genre = :genre_id where id = :id");
        query.setParameter("genre_id", genre_id);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
