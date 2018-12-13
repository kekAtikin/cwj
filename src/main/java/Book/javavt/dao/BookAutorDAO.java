
package Book.javavt.dao;

        import Book.javavt.title.BookAutor;
        import java.util.List;

public interface BookAutorDAO {

    void saveOrUpdate(BookAutor item);

    void delete(int itemId);

    BookAutor get(int itemId);

    List<BookAutor> list();

}