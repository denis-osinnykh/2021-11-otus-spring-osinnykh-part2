package my.spring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Document
public class Book {
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Author author;

    @Getter
    @Setter
    private Genre genre;

    private List<Comment> comments;

    public Book(String name, @Nullable Author author, @Nullable Genre genre){
       this.name = name;
       this.author = author;
       this.genre = genre;
    }

    public Book(String id, String name, @Nullable Author author, @Nullable Genre genre, @Nullable Comment... comments){
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comments);
    }

    public Book(String name, @Nullable Author author, @Nullable Genre genre, @Nullable Comment... comments){
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.comments = Arrays.asList(comments);
    }
}
