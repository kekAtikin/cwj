
package Book.javavt.dao.hibernate;

        import Book.javavt.dao.BookAutorDAO;
        import Book.javavt.title.BookAutor;
        import org.hibernate.Criteria;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public class BookAutorDAOHibernateImpl implements BookAutorDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(BookAutor item) {
        if (item.getIdAutor() > 0) {
            //update
            getCurrentSession().merge(item);
        } else {
            //insert
            getCurrentSession().save(item);
        }

    }

    public void delete(int itemId) {
        BookAutor bookAutor = get(itemId);
        if (bookAutor != null)
            getCurrentSession().delete(bookAutor);
    }

    public BookAutor get(int itemId) {
        BookAutor bookAutor = (BookAutor) getCurrentSession().get(BookAutor.class, itemId);

        return bookAutor;
    }

    public List<BookAutor> list() {
        Criteria criteria = getCurrentSession().createCriteria(BookAutor.class);

        return criteria.list();
    }
}