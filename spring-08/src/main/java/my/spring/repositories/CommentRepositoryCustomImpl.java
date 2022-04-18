package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import my.spring.domain.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAllByBookId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("bookId").is(id));
        return mongoTemplate.find(query, Comment.class);
    }
}
