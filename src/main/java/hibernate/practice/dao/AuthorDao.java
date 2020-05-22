package hibernate.practice.dao;

import hibernate.practice.model.Author;
import hibernate.practice.model.Book;
import java.util.List;

public interface AuthorDao {
    List<Book> getListOfBooksByAuthor(Author author);

    Author add(Author author);
}
