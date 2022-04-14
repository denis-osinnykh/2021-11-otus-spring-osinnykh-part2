package my.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@RequiredArgsConstructor
@Document
public class Comment {
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    private String text;

    @Getter
    private Book book;

    public Comment(String id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }

    public Comment(String text/*, Book book*/) {
        this.text = text;
    }
}