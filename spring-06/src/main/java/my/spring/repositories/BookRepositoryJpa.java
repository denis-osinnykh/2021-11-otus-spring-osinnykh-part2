package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Book getById(long id) {
        EntityGraph eg = em.getEntityGraph("books-entity-graph");
        Map<String, Object> props = new HashMap<>();
        props.put("javax.persistence.fetchgraph", eg);
        return em.find(Book.class, id, props);
    }

    @Override
    public List<Book> getAll() {
         return em.createQuery("select b from Book b join fetch b.author join fetch b.genre ", Book.class).getResultList();
    }

    @Override
    public void add(Book book) {
        if (book.getId() <= 0) {
            em.persist(book);
        }
        else {
            em.merge(book);
        }
    }

    @Override
    public void updateNameById(String name, long id) {
        Query query = em.createQuery("update Book b set b.name = :name where b.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateAuthorById(Author author, long id) {
        Query query = em.createQuery("update Book b set b.author = :author where b.id = :id");
        query.setParameter("author", author);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateGenreById(Genre genre, long id) {
        Query query = em.createQuery("update Book b set b.genre = :genre where b.id = :id");
        query.setParameter("genre", genre);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
