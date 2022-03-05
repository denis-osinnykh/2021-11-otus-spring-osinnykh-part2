package my.spring.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Book {
    @Nullable
    private long id;
    private final String name;
    private Author author;
    private Genre genre;

    public Book(long id, String name, @Nullable Author author, @Nullable Genre genre){
       this.id = id;
       this.name = name;
       this.author = author;
       this.genre = genre;
    }

    public Book(String name, @Nullable Author author, @Nullable Genre genre){
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}
