package my.spring.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

//@RequiredArgsConstructor
@Data
public class Book {
    private final long id;
    private final String name;
    @Nullable
    private long authorId;
    @Nullable
    private long genreId;

    /*
    Не понял как сделать конструктор с необязательными параметрами через аннотации.
    Через @RequiredArgsConstructor не получилось. При вызове new Book() не получается передать необязательные nullable-параметры.
    */
    public Book(long id, String name, @Nullable long authorId, @Nullable long genreId){
       this.id = id;
       this.name = name;
       this.authorId = authorId;
       this.genreId = genreId;
    }
}
