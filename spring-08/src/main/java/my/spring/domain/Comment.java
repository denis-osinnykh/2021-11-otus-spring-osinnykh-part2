package my.spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
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
    private String bookId;

    public Comment(String id, String text, String bookId) {
        this.id = id;
        this.text = text;
        this.bookId = bookId;
    }

    public Comment(String text, String bookId) {
        this.text = text;
        this.bookId = bookId;
    }

    public Comment(String text) {
        this.text = text;
    }
}