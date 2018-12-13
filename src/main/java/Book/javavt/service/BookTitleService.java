
package Book.javavt.service;

        import Book.javavt.title.BookTitle;
        import java.util.List;

public interface BookTitleService {
    void saveOrUpdate(BookTitle item);

    void delete(int itemId);

    BookTitle get(int itemId);

    List<BookTitle> list();
}