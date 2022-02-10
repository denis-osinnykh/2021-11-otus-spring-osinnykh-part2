package my.spring.shell;

import lombok.RequiredArgsConstructor;
import my.spring.service.BookService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationCommands {
    private final BookService bs;

    @ShellMethod(value = "Get count of books", key = {"gcb", "get count"})
    public void getBooksCount() {
        int count = bs.getBooksCount();
        System.out.println(String.format("Количество книг: %s", count));
    }

//    @ShellMethod(value = "Login command", key = {"l", "login"})
//    public String login(@ShellOption(defaultValue = "AnyUser") String userName) {
//        this.userName = userName;
//        return String.format("Добро пожаловать: %s", userName);
//    }
}
