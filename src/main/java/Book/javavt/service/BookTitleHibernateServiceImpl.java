
package Book.javavt.service;

import Book.javavt.dao.BookTitleDAO;
import Book.javavt.title.BookTitle;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import java.util.List;

@Service("bookTitleHibernateService")
@Transactional(readOnly=false, value = "hibernateTransactionManager")
public class BookTitleHibernateServiceImpl implements BookTitleService {
    @Autowired
    @Qualifier("getBookTitleHibernateDAO")
    private BookTitleDAO bookTitleDAO;

    public void saveOrUpdate(BookTitle item) {
        bookTitleDAO.saveOrUpdate(item);
    }

    public void delete(int itemId) {
        bookTitleDAO.delete(itemId);
    }

    public BookTitle get(int itemId) {
        return bookTitleDAO.get(itemId);
    }

    public List<BookTitle> list() {
        return bookTitleDAO.list();
    }
}