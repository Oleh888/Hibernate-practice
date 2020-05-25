package hibernate.practice.service;

import hibernate.practice.model.Author;
import hibernate.practice.model.Book;
import java.util.List;

public interface AuthorService {
    List<Book> getListOfBooksByAuthor(Author author);

    void add(Author author);
}
