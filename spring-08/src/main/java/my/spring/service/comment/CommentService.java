package my.spring.service.comment;

import my.spring.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment getCommentById(String id);

    List<Comment> getAllCommentsByBookId(String id);

    boolean updateCommentById(String text, String book_id);

    boolean addCommentByBookId(String text, String book_id);

    boolean deleteCommentById(String comment_id);
}
