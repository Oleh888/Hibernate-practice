package hibernate.practice.dao;

import hibernate.practice.model.Book;
import java.util.List;

public interface BookDao {
    List<Book> getBookByTitle(String title);

    Book add(Book book);
}
