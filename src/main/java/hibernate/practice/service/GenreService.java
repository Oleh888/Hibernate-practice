package hibernate.practice.service;

import hibernate.practice.model.Book;
import hibernate.practice.model.Genre;
import java.util.List;

public interface GenreService {
    List<Book> getAllBooksByGenre(Genre genre);

    void add(Genre genre);
}
