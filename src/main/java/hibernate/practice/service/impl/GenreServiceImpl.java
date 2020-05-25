package hibernate.practice.service.impl;

import hibernate.practice.dao.GenreDao;
import hibernate.practice.lib.Inject;
import hibernate.practice.lib.Service;
import hibernate.practice.model.Book;
import hibernate.practice.model.Genre;
import hibernate.practice.service.GenreService;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return genreDao.getAllBooksByGenre(genre);
    }

    @Override
    public void add(Genre genre) {
        genreDao.add(genre);
    }
}
