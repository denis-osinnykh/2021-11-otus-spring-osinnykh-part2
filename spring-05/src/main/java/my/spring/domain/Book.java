package my.spring.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Book {
    @Nullable
    private Long id;
    private final String name;
    @Nullable
    private Author author;
    @Nullable
    private Genre genre;

    public Book(@Nullable Long id, String name, @Nullable Author author, @Nullable Genre genre){
       if (id != null) this.id = id.longValue();
       this.name = name;
       this.author = author;
       this.genre = genre;
    }
}
