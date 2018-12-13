
package Book.javavt.title;

        import org.hibernate.validator.constraints.NotEmpty;
        import javax.persistence.*;
        import java.io.Serializable;

@Entity
@Table(name = "autor")
@NamedQuery(name = "BookAutor.findAll", query = "select c from BookAutor c")

public class BookAutor implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idAutor;
    @NotEmpty
    @Column(unique=true, nullable=false)
    public String name;


    @Column private String publisher;

    public BookAutor() {}

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "BookAutor{" +
                "idAutor=" + idAutor +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}