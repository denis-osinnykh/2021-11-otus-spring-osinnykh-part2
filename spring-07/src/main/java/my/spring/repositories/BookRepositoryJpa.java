package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Genre;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepositoryJpa {
/*
    @PersistenceContext
    private final EntityManager em;

    @Override
    public long getCount() {
        long count = em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
        return count;
    }

    @Override
    public Book getById(long id) {
        TypedQuery<Book> query = em.createQuery("select b from Book b join fetch b.author join fetch b.genre " +
                "where b.id = :id", Book.class);
        query.setParameter("id", id);
        return query.getSingleResult();
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
    }*/
}
