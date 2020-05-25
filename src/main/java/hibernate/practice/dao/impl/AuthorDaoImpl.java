package hibernate.practice.dao.impl;

import hibernate.practice.dao.AuthorDao;
import hibernate.practice.exceptions.DataProcessingException;
import hibernate.practice.lib.Dao;
import hibernate.practice.model.Author;
import hibernate.practice.model.Book;
import hibernate.practice.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    private static final Logger LOGGER = Logger.getLogger(AuthorDaoImpl.class);

    @Override
    public List<Book> getListOfBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
            Root<Author> root = criteriaQuery.from(Author.class);
            criteriaQuery.select(root.get("book"));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all books by this author: " + author, e);
        }
    }

    @Override
    public void add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            LOGGER.info("author " + author + " was added to DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert author entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
