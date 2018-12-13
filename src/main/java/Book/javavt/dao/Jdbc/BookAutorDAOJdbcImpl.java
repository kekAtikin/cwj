
package Book.javavt.dao.Jdbc;

        import Book.javavt.dao.BookAutorDAO;
        import Book.javavt.title.BookAutor;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.List;
        import javax.sql.DataSource;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.dao.DataAccessException;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.core.ResultSetExtractor;
        import org.springframework.jdbc.core.RowMapper;


public class BookAutorDAOJdbcImpl implements BookAutorDAO {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public BookAutorDAOJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(BookAutor item) {
        if (item.getIdAutor() > 0) {
            // update
            System.out.println("BookAutor update");
            String sql = "UPDATE autor SET name=?, publisher=? WHERE idAutor=?";
            jdbcTemplate.update(sql, item.getName(),item.getPublisher(), item.getIdAutor());
        } else {
            // insert
            System.out.println("BookAutor insert");
            String sql = "INSERT INTO autor (name, publisher)"
                    + " VALUES (?, ?)";
            jdbcTemplate.update(sql, item.getName(), item.getPublisher());
        }
    }

    public void delete(int itemId) {
        String sql = "DELETE FROM autor WHERE idAutor=?";
        jdbcTemplate.update(sql, itemId);
    }

    public BookAutor get(int itemId) {
        String sql = "SELECT * FROM autor WHERE idAutor=" + itemId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<BookAutor>() {

            public BookAutor extractData(ResultSet rs) throws SQLException,
                    DataAccessException {
                if (rs.next()) {
                    return getBookAutorFromDB(rs);
                }
                return null;
            }
        });
    }

    public List<BookAutor> list() {
        String sql = "SELECT * FROM autor";
        List<BookAutor> listBookAutor = jdbcTemplate.query(sql, new RowMapper<BookAutor>() {

            public BookAutor mapRow(ResultSet rs, int i) throws SQLException {

                return getBookAutorFromDB(rs);
            }
        });
        return listBookAutor;
    }

    private BookAutor getBookAutorFromDB(ResultSet rs) throws SQLException{
        BookAutor autor = new BookAutor();
        autor.setIdAutor(rs.getInt("idAutor"));
        autor.setName(rs.getString("name"));
        autor.setPublisher(rs.getString("publisher"));
        return autor;
    }
}