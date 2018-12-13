package Book.javavt.config;

        import Book.javavt.dao.BookAutorDAO;
        import Book.javavt.dao.BookTitleDAO;
        import Book.javavt.dao.hibernate.BookAutorDAOHibernateImpl;
        import Book.javavt.dao.hibernate.BookTitleDAOHibernateImpl;
        import org.hibernate.SessionFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.PropertySource;
        import org.springframework.orm.hibernate4.HibernateTransactionManager;
        import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
        import org.springframework.transaction.annotation.EnableTransactionManagement;
        import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan({"Book.javavt"})
public class HibernateConfig {

    @Autowired
    private DataSource dataSource;

    //Hibernate configuration
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[] { "Book.javavt.title" });
        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }

    @Bean
    public BookAutorDAO getBookAutorHibernateDAO() {
        return new BookAutorDAOHibernateImpl();
    }
    @Bean
    public BookTitleDAO getBookTitleHibernateDAO() {
        return new BookTitleDAOHibernateImpl();
    }
}