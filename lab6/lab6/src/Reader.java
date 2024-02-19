import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reader  implements Serializable {
    public static final int maxBooks = 5;
    List<Book> takenBooks;

    public Reader() {
        this.takenBooks = new ArrayList<Book>();
    }

    public static int getMaxBooks() {
        return maxBooks;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }

    public void addBook(Book book){
        this.takenBooks.add(book);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "takenBooks=" + takenBooks +
                '}';
    }
}
