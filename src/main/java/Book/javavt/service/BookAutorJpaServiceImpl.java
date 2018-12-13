
package Book.javavt.service;

import Book.javavt.dao.BookAutorDAO;
import Book.javavt.title.BookAutor;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;
        import java.util.List;

@Service("bookAutorJpaService")
@Transactional(readOnly=false, value = "jpaTransactionManager")
public class BookAutorJpaServiceImpl implements BookAutorService {
    @Autowired
    @Qualifier("getBookAutorJpaDAO")
    private BookAutorDAO bookAutorDAO;

    public List<BookAutor> list() {
        return bookAutorDAO.list();
    }

    public BookAutor get(int itemId) {
        return bookAutorDAO.get(itemId);
    }

    public void saveOrUpdate(BookAutor item) {
        bookAutorDAO.saveOrUpdate(item);
    }

    public void delete(int itemId) {
        bookAutorDAO.delete(itemId);
    }
}