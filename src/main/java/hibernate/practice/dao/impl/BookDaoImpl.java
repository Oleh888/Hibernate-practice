package hibernate.practice.dao.impl;

import hibernate.practice.dao.BookDao;
import hibernate.practice.exceptions.DataProcessingException;
import hibernate.practice.lib.Dao;
import hibernate.practice.model.Book;
import hibernate.practice.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    @Override
    public List<Book> getBookByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery =
                    criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            Predicate predicateForTitle = criteriaBuilder.equal(root.get("title"), title);
            criteriaQuery.where(predicateForTitle);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't get book with title " + title, e);
        }
    }

    @Override
    public Book add(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            LOGGER.info("book " + book + " was added to DB");
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert book entity", e);
        }
    }
}
