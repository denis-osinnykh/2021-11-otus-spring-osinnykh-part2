package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Genre;
import org.springframework.stereotype.Repository;

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
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g ", Genre.class).getResultList();
    }
}
