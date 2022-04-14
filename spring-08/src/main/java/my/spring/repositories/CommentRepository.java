package my.spring.repositories;

import my.spring.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    //@EntityGraph(value = "comments-entity-graph")
    Comment findCommentById(String id);

    List<Comment> findAllByBookId(String id);
}
