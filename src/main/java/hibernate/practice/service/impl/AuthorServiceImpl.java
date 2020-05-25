package hibernate.practice.service.impl;

import hibernate.practice.dao.AuthorDao;
import hibernate.practice.lib.Inject;
import hibernate.practice.lib.Service;
import hibernate.practice.model.Author;
import hibernate.practice.model.Book;
import hibernate.practice.service.AuthorService;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        return authorDao.getListOfBooksByAuthor(author);
    }

    @Override
    public void add(Author author) {
        authorDao.add(author);
    }
}
