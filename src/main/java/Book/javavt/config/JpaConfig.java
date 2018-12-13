
package Book.javavt.config;

        import javax.persistence.EntityManagerFactory;
        import javax.sql.DataSource;

        import Book.javavt.dao.BookAutorDAO;
        import Book.javavt.dao.BookTitleDAO;
        import Book.javavt.dao.Jpa.BookAutorDAOJpaImpl;
        import Book.javavt.dao.Jpa.BookTitleDAOJpaImpl;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.PropertySource;
        import org.springframework.orm.jpa.JpaTransactionManager;
        import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
        import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
        import org.springframework.transaction.PlatformTransactionManager;
        import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan
@EnableTransactionManagement
public class JpaConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory =  new LocalContainerEntityManagerFactoryBean();

        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan(new String[] {"Book.javavt.title"});
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        return entityManagerFactory;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

    @Bean
    public BookAutorDAO getBookAutorJpaDAO() {
        return new BookAutorDAOJpaImpl();
    }
    @Bean
    public BookTitleDAO getBookTitleJpaDAO() {
        return new BookTitleDAOJpaImpl();
    }
}