package my.spring.domain;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@RequiredArgsConstructor
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
@Entity
@Table(name = "comment")
public class Comment {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Getter
    @Setter
    @Column(name = "text")
    private String text;

    @Getter
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Book book;

    public Comment(long id, String text, Book book) {
        this.id = id;
        this.text = text;
        this.book = book;
    }
}