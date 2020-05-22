package hibernate.practice.service;

import hibernate.practice.model.Book;
import java.util.List;

public interface BookService {
    List<Book> getBookByTitle(String title);

    Book add(Book book);
}
