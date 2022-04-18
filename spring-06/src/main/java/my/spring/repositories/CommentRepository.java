package my.spring.repositories;

import my.spring.domain.Comment;

import java.util.List;

public interface CommentRepository {

    Comment getById(long id);

    List<Comment> getAllByBookId(long id);

    void save(Comment comment);

    void deleteById(long id);
}
