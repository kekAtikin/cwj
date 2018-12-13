package Book.javavt.title;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="title")
@NamedQuery(name="BookTitle.findAll", query="select m from BookTitle m")

public class BookTitle implements Serializable {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int idTitle;

    @Column(insertable = false, updatable = false)
    private int idAutor;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private BookAutor bookAutor;

    @NotEmpty @Column
    private String titleName;
    @Column private String genre;
    @Column private short numberofpages;

    public BookTitle() {}

    public int getIdTitle() {
        return idTitle;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public BookAutor getBookAutor() {
        return bookAutor;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getGenre() {
        return genre;
    }

    public short getNumberofpages() {
        return numberofpages;
    }

    public void setIdTitle(int idTitle) {
        this.idTitle = idTitle;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setBookAutor(BookAutor bookAutor) {
        this.bookAutor = bookAutor;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNumberofpages(short numberofpages) {
        this.numberofpages = numberofpages;
    }

    @Override
    public String toString() {
        return "BookAutor{" +
                "idTitle=" + idTitle +
                ", bookAutor=" + bookAutor.getName() +
                ", idAutor=" + idAutor +
                ", titleName='" + titleName + '\'' +
                ", genre='" + genre + '\'' +
                ", numberofpages=" + numberofpages +
                '}';
    }
}