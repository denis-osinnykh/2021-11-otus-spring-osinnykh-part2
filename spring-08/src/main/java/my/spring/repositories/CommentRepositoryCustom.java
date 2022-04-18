package my.spring.repositories;

import my.spring.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {

    List<Comment> findAllByBookId(String id);
}
