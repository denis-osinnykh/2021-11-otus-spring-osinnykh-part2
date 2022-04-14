package my.spring.repositories;

import lombok.RequiredArgsConstructor;
import lombok.val;
import my.spring.domain.Book;
import my.spring.domain.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Comment> findAllCommentsByBookId(String id) {
        val aggregation = newAggregation(
                match(Criteria.where("id").is(id))
                , unwind("comments")
                , project().andExclude("_id").and("comments.id").as("_id").and("comments.text").as("text")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Comment.class).getMappedResults();
    }

//    @Override
//    public void updateNameById(String name, long id) {
//
//    }
}
