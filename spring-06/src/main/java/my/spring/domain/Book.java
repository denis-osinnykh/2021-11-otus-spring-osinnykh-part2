package my.spring.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "book")
public class Book {
    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany
    @JoinColumn(name = "comment_id")
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
