package my.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Column(name = "name", nullable = false)
    private String name;

    @Getter
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Getter
    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Comment> comments;

    public Book(String name, @Nullable Author author, @Nullable Genre genre){
       this.name = name;
       this.author = author;
       this.genre = genre;
    }

    public Book(long id, String name, @Nullable Author author, @Nullable Genre genre){
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
