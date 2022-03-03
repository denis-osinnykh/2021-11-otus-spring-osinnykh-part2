package my.spring.service;

import my.spring.domain.Author;

import java.util.List;

public interface AuthorPrintService {

    void printAuthor(Author author);

    void printListAuthors(List<Author> authors);
}
