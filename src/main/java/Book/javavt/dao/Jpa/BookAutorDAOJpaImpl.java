
package Book.javavt.dao.Jpa;

import Book.javavt.dao.BookAutorDAO;
import Book.javavt.title.BookAutor;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookAutorDAOJpaImpl implements BookAutorDAO {
    @PersistenceContext
    private EntityManager em;

    public void saveOrUpdate(BookAutor item) {
        if (item.getIdAutor() > 0) {
            //update
            em.merge(item);
        } else {
            //insert
            em.persist(item);
        }
    }

    public void delete(int itemId) {
        em.remove(get(itemId));

    }

    public BookAutor get(int itemId) {
        return em.find(BookAutor.class, itemId);
    }

    public List<BookAutor> list() {
        List<BookAutor> bookAutor = em.createNamedQuery("BookAutor.findAll",BookAutor.class).getResultList();
        return bookAutor;
    }
}