package my.spring.repositories;

import my.spring.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {

    Comment findCommentById(String id);
}
