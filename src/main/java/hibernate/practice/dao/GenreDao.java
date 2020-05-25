package hibernate.practice.dao;

import hibernate.practice.model.Book;
import hibernate.practice.model.Genre;
import java.util.List;

public interface GenreDao {
    List<Book> getAllBooksByGenre(Genre genre);

    void add(Genre genre);
}
