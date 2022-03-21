package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Book;
import my.spring.domain.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;

    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment getById(long id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c " +
                "where c.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Comment> getAllByBookId(long id) {
        Query query = em.createQuery("select c from Comment c " +
                "where c.book.id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void save(Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        }
        else {
            em.merge(comment);
        }
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
