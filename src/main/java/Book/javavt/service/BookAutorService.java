
package Book.javavt.service;

        import Book.javavt.title.BookAutor;
        import java.util.List;

public interface BookAutorService {

    void saveOrUpdate(BookAutor item);

    void delete(int itemId);

    BookAutor get(int itemId);

    List<BookAutor> list();
}