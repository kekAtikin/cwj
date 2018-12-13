
package Book.javavt.service;

import Book.javavt.dao.BookTitleDAO;
import Book.javavt.title.BookTitle;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Qualifier;
        import org.springframework.stereotype.Service;
        import java.util.List;

@Service("bookTitleJdbcService")
public class BookTitleJdbcServiceImpl implements BookTitleService {
    @Autowired
    @Qualifier("getBookTitleJdbcDAO")
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