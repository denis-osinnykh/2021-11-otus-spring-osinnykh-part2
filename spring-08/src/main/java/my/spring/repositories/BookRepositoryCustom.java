package my.spring.repositories;

import my.spring.domain.Author;
import my.spring.domain.Comment;
import my.spring.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    List<Comment> findAllCommentsByBookId(String id);

    /*void updateNameById(String name, long id);

    void updateAuthorById(Author author, long id);

    void updateGenreById(Genre genre, long id);*/
}
