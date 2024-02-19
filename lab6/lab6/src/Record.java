import java.time.LocalDate;

public class Record {
    int id;
    Book book;
    Subscription subscription;
    LocalDate takenAt;
    LocalDate plannedReturn;
    LocalDate actualReturn;

    public Record() {
    }

    public Record(int id, Book book, Subscription subscription, LocalDate takenAt, LocalDate plannedReturn) {
        this.id = id;
        this.book = book;
        this.subscription = subscription;
        this.takenAt = takenAt;
        this.plannedReturn = plannedReturn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public LocalDate getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }

    public LocalDate getPlannedReturn() {
        return plannedReturn;
    }

    public void setPlannedReturn(LocalDate plannedReturn) {
        this.plannedReturn = plannedReturn;
    }

    public LocalDate getActualReturn() {
        return actualReturn;
    }

    public void setActualReturn(LocalDate actualReturn) {
        this.actualReturn = actualReturn;
    }

}
