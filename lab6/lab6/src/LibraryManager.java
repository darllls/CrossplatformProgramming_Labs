import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LibraryManager {

    public static Library fillLibrarywithBooks() {
        Library library = new Library();

        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length == 3) {
                    String author = bookData[0].trim();
                    String name = bookData[1].trim();
                    int published = Integer.parseInt(bookData[2].trim());
                    Book book = new Book(author, name, published);
                    library.addBook(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return library;
    }

    public static List<Subscription> loadSubscriptionsFromFile() {
        List<Subscription> subscriptions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("subscriptions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] subscriptionData = line.split(",");
                if (subscriptionData.length == 3) {
                    String surname = subscriptionData[0].trim();
                    String name = subscriptionData[1].trim();
                    String email = subscriptionData[2].trim();
                    Reader reader1 = new Reader();
                    Subscription subscription = new Subscription(surname, name, email, reader1);
                    subscriptions.add(subscription);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return subscriptions;
    }

    public static void performRegistrations(Library library, List<Subscription> subscriptions) {

        try (BufferedReader reader = new BufferedReader(new FileReader("records.txt"))) {
            String line;
            int id = 1;

            while ((line = reader.readLine()) != null) {
                String[] registrationData = line.split(",");
                if (registrationData.length == 4) {
                    int bookId = Integer.parseInt(registrationData[0].trim());
                    int subscriptionId = Integer.parseInt(registrationData[1].trim());
                    System.out.println("bookId: " + bookId + ", subscriptionId: " + subscriptionId);
                    LocalDate takenAt = LocalDate.parse(registrationData[2].trim());
                    LocalDate plannedReturn = LocalDate.parse(registrationData[3].trim());


                    Book book = library.getAllBooks().get(bookId-1);
                    Subscription subscription = subscriptions.get(subscriptionId-1);

                    Administrator.registerTake(library, id++, book, subscription, takenAt, plannedReturn);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Book> booksSorting(Library library) {
        return library.getAllBooks()
                .stream()
                .sorted(Comparator.comparingInt(Book::getPublished))
                .collect(Collectors.toList());
    }

    public static void printPopularReaders(Library library) {
        List<String> emailAddresses = library.getAllSubscriptions()
                .stream()
                .filter(subscription -> subscription.reader.getTakenBooks().size() > 2)
                .map(Subscription::getEmail)
                .collect(Collectors.toList());

        // Print email addresses or send notifications
        emailAddresses.forEach(System.out::println);
    }

    public static void booksTakenInitialAuthor(Library library, String author) {
        long count = library.getAllSubscriptions()
                .stream()
                .filter(subscription -> subscription.reader.getTakenBooks()
                        .stream()
                        .anyMatch(book -> book.getAuthor().equals(author)))
                .count();

        System.out.println("Number of users who took books from " + author + ": " + count);
    }

    public static void maxBooksTaken(Library library) {
        int maxBooksCount = library.getAllSubscriptions()
                .stream()
                .mapToInt(subscription -> subscription.reader.getTakenBooks().size())
                .max()
                .orElse(0);

        System.out.println("Maximum number of books taken by one reader: " + maxBooksCount);
    }

    public static void uniqueBooks(Library library){
        List<Book> books = library.getAllBooks().stream().distinct().collect(Collectors.toList());

        books.forEach(System.out :: println);
    }
    public static void notificationsMailing(Library library){
        library.getAllSubscriptions().stream()
                .filter(x -> x.reader.takenBooks.size() >= 2)
                .forEach(x -> x.reader.takenBooks.forEach(y -> System.out.println("Attention, " + x.name + "" + x.surname +
                        ", you should return book " + y.name + " no later than " + Administrator.getLatestByBook(library, y).plannedReturn)));

        library.getAllSubscriptions().stream()
                .filter(x -> x.reader.takenBooks.size() < 2)
                .forEach(x -> System.out.println(x.name + " " + x.surname + " you can take one of this books" + library.getAllBooks()));
    }

    public static void debtorsInforming(){

        for (int i = 5; i < 15; i+=3) {
            LocalDate date = LocalDate.of(2023, 11, i);
            System.out.println(date);
            Administrator.records.stream().filter(x -> date.isAfter(x.plannedReturn) && (x.actualReturn == null || date.isBefore(x.actualReturn)))
                    .forEach(x -> System.out.println("Attention, " + x.subscription.name + " " + x.subscription.surname + " book " + x.book.name
                            + " is running late for " + (date.getDayOfMonth() - x.plannedReturn.getDayOfMonth()) + " days"));
        }
    }
}
