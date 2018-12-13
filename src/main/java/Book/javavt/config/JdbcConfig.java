
package Book.javavt.config;

        import Book.javavt.dao.BookAutorDAO;
        import Book.javavt.dao.BookTitleDAO;
        import Book.javavt.dao.Jdbc.BookAutorDAOJdbcImpl;
        import Book.javavt.dao.Jdbc.BookTitleDAOJdbcImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import javax.sql.DataSource;

@Configuration
public class JdbcConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public BookAutorDAO getBookAutorJdbcDAO() {
        return new BookAutorDAOJdbcImpl(dataSource);
    }
    @Bean
    public BookTitleDAO getBookTitleJdbcDAO() {
        return new BookTitleDAOJdbcImpl(dataSource);
    }
}