package Book.javavt.dao;

import Book.javavt.title.BookTitle;
import java.util.List;

public interface BookTitleDAO {

    void saveOrUpdate(BookTitle item);

    void delete(int itemId);

    BookTitle get(int itemId);

    List<BookTitle> list();

}