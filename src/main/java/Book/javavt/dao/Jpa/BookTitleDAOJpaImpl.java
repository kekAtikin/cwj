package Book.javavt.dao.Jpa;

import Book.javavt.dao.BookTitleDAO;
import Book.javavt.title.BookTitle;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookTitleDAOJpaImpl implements BookTitleDAO {
    @PersistenceContext
    private EntityManager em;

    public void saveOrUpdate(BookTitle item) {
        if (item.getIdAutor() > 0) {
            // update
            em.merge(item);
        } else {
            // insert
            em.persist(item);
        }
    }

    public void delete(int itemId) {
        em.remove(get(itemId));
    }

    public BookTitle get(int itemId) {
        return em.find(BookTitle.class, itemId);
    }

    public List<BookTitle> list() {
        List<BookTitle> bookTitle = em.createNamedQuery("BookTitle.findAll",BookTitle.class).getResultList();
        return bookTitle;
    }
}