package my.spring.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import my.spring.domain.Author;
import my.spring.domain.Book;
import my.spring.domain.Comment;
import my.spring.domain.Genre;
import my.spring.repositories.AuthorRepository;
import my.spring.repositories.BookRepository;
import my.spring.repositories.CommentRepository;
import my.spring.repositories.GenreRepository;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private Author sameAuthor;
    private Author sameAuthor2;
    private Genre sameGenre;
    private Genre sameGenre2;
    private Comment sameComment;
    private Book sameBook;

    @ChangeSet(order = "000", id = "dropDB", author = "osinnykh", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "osinnykh", runAlways = true)
    public void initAuthors(AuthorRepository repository){
        sameAuthor = repository.save(new Author("Same author"));
        sameAuthor2 = repository.save(new Author("Same author 2"));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "osinnykh", runAlways = true)
    public void initGenres(GenreRepository repository){
        sameGenre = repository.save(new Genre("Same genre"));
        sameGenre2 = repository.save(new Genre("Same genre 2"));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "osinnykh", runAlways = true)
    public void initBooks(BookRepository repository){
        sameBook = repository.save(new Book("Same book", sameAuthor, sameGenre));
    }

    @ChangeSet(order = "004", id = "initComments", author = "osinnykh", runAlways = true)
    public void initComments(CommentRepository repository){
        sameComment = repository.save(new Comment("Same comment", sameBook.getId()));
    }
}
