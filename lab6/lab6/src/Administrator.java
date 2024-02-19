import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Administrator {
    static List<Record> records = new ArrayList<Record>();

    public static void registerTake(Library library, int id, Book book, Subscription subscription, LocalDate takenAt, LocalDate plannedReturn) {
        if (subscription.reader.takenBooks.size() < Reader.maxBooks) {
            subscription.reader.addBook(book);
            library.removeBook(book);
            records.add(new Record(id, book, subscription, takenAt, plannedReturn));
        }
    }

    public static void registerReturn(Library library, Subscription subscription, Book book, LocalDate actualReturn) {
        Record record = records.stream()
                .filter(x -> x.book.equals(book))
                .sorted(Comparator.comparingInt(x -> x.id))
                .collect(Collectors.toList())
                .get(0);
        record.setActualReturn(actualReturn);
        library.addBook(book);
        subscription.reader.takenBooks.remove(book);
    }

    public static Record getLatestByBook(Library library, Book book) {
        return records.stream()
                .filter(x -> x.book.equals(book))
                .sorted(Comparator.comparingInt(x -> x.id))
                .collect(Collectors.toList())
                .get(0);
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        Administrator.records = records;
    }
}

