
package Book.javavt.dao.hibernate;

        import Book.javavt.dao.BookTitleDAO;
        import Book.javavt.title.BookTitle;
        import org.hibernate.Criteria;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Repository;
        import java.util.List;

@Repository
public class BookTitleDAOHibernateImpl implements BookTitleDAO{

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void saveOrUpdate(BookTitle item) {
        if (item.getIdTitle() > 0) {
            getCurrentSession().merge(item);
        } else {
            getCurrentSession().save(item);
        }

    }

    public void delete(int itemId) {
        BookTitle bookTitle = get(itemId);
        if (bookTitle != null)
            getCurrentSession().delete(bookTitle);
    }

    public BookTitle get(int itemId) {
        BookTitle bookTitle = (BookTitle) getCurrentSession().get(BookTitle.class, itemId);

        return bookTitle;
    }

    public List<BookTitle> list() {
        Criteria criteria = getCurrentSession().createCriteria(BookTitle.class);

        return criteria.list();
    }
}