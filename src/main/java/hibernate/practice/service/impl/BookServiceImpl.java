package hibernate.practice.service.impl;

import hibernate.practice.dao.BookDao;
import hibernate.practice.lib.Inject;
import hibernate.practice.lib.Service;
import hibernate.practice.model.Book;
import hibernate.practice.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public List<Book> getBookByTitle(String title) {
        return bookDao.getBookByTitle(title);
    }

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }
}
