
package Book.javavt.dao.Jdbc;

import Book.javavt.dao.BookAutorDAO;
import Book.javavt.dao.BookTitleDAO;
import Book.javavt.title.BookTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookTitleDAOJdbcImpl implements BookTitleDAO {
    @Autowired
    @Qualifier("getBookTitleJdbcDAO")
    private BookAutorDAO bookAutorDAO;

    private JdbcTemplate jdbcTemplate;

    public BookTitleDAOJdbcImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void saveOrUpdate(BookTitle item) {
        if (item.getIdTitle() > 0) {
            // update
            String sql = "UPDATE title SET idAutor=?, titleName=?, genre=?, numberofpages=? WHERE idTitle=?";
            jdbcTemplate.update(sql, item.getIdTitle(), item.getTitleName(),item.getGenre(),
                    item.getNumberofpages());
        } else {
            // insert
            String sql = "INSERT INTO title (idAutor, titleName, genre, numberofpages)"
                    + " VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, item.getIdTitle(), item.getTitleName(),item.getGenre(),
                    item.getNumberofpages());
        }
    }

    public void delete(int itemId) {
        String sql = "DELETE FROM title WHERE idTitle=?";
        jdbcTemplate.update(sql, itemId);
    }

    public BookTitle get(int itemId) {
        String sql = "SELECT * FROM title WHERE idTitle=" + itemId;
        return jdbcTemplate.query(sql, new ResultSetExtractor<BookTitle>() {

            public BookTitle extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    return getBookTitleFromDB(rs);
                }
                return null;
            }
        });
    }

    public List<BookTitle> list() {
        String sql = "SELECT * FROM title";
        List<BookTitle> listBookTitle = jdbcTemplate.query(sql, new RowMapper<BookTitle>() {

            public BookTitle mapRow(ResultSet rs, int i) throws SQLException {

                return getBookTitleFromDB(rs);
            }
        });
        return listBookTitle;
    }

    private BookTitle getBookTitleFromDB(ResultSet rs) throws SQLException{
        BookTitle title = new BookTitle();
        title.setIdTitle(rs.getInt("idTitle"));
        title.setIdAutor(rs.getInt("idAutor"));
        title.setBookAutor(bookAutorDAO.get(rs.getInt("idAutor")));
        title.setTitleName(rs.getString("titleName"));
        title.setGenre(rs.getString("genre"));
        title.setNumberofpages(rs.getShort("numberofpages"));
        return title;
    }
}