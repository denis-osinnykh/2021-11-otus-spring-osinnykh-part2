package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Genre;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Genre getById(long id) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g " +
                "where g.id = :id", Genre.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g ", Genre.class).getResultList();
    }
}
