package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Author;
import my.spring.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Author getById(long id) {
        TypedQuery<Author> query = em.createQuery("select a from Author a " +
                "where a.id = :id", Author.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a ", Author.class).getResultList();
    }

    @Override
    public Author save(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        }
        else {
            return em.merge(author);
       }
    }
}
