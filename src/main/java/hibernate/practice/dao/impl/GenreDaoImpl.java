package hibernate.practice.dao.impl;

import hibernate.practice.dao.GenreDao;
import hibernate.practice.exceptions.DataProcessingException;
import hibernate.practice.lib.Dao;
import hibernate.practice.model.Book;
import hibernate.practice.model.Genre;
import hibernate.practice.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {
    private static final Logger LOGGER = Logger.getLogger(GenreDaoImpl.class);

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = builder.createQuery(Book.class);
            Root<Genre> root = criteriaQuery.from(Genre.class);
            criteriaQuery.select(root.get("book"));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all books by this genre " + genre, e);
        }
    }

    @Override
    public void add(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(genre);
            transaction.commit();
            LOGGER.info("genre " + genre + " was added to DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert author entity", e);
        }
    }
}
