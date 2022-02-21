package my.spring.domain;

import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class Book {
    private final long id;
    private final String name;
    @Nullable
    private long authorId;
    @Nullable
    private long genreId;

    public Book(long id, String name, @Nullable long authorId, @Nullable long genreId){
       this.id = id;
       this.name = name;
       this.authorId = authorId;
       this.genreId = genreId;
    }
}
