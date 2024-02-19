import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {

    List<Book> allBooks;
    List<Subscription> allSubscriptions;

    public Library() {
        this.allSubscriptions = new ArrayList<Subscription>();
        this.allBooks = new ArrayList<Book>();
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Subscription> getAllSubscriptions() {
        return allSubscriptions;
    }

    public void setAllSubscriptions(List<Subscription> allSubscriptions) {
        this.allSubscriptions = allSubscriptions;
    }

    public void addBook(Book book) {
        this.allBooks.add(book);
    }

    public void removeBook(Book book) {
        this.allBooks.remove(book);
    }

    public void addSubscription(Subscription subscription) {
        this.allSubscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription) {
        this.allSubscriptions.remove(subscription);
    }
}
