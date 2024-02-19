import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        Library library = LibraryManager.fillLibrarywithBooks();
        System.out.println("Number of books in the library: " + library.getAllBooks().size());
        List<Book> sortedBooks = LibraryManager.booksSorting(library);
        
        List<Subscription> subscriptions = LibraryManager.loadSubscriptionsFromFile();
        subscriptions.forEach(library::addSubscription);

        LibraryManager.performRegistrations(library, subscriptions);
        Serializer.serialize(library);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Sort books in ascending order");
            System.out.println("2. Emails readers who took more than 2 books");
            System.out.println("3. Count books with initial author which readers took");
            System.out.println("4. Max books count which took one reader");
            System.out.println("5. Notifications for every type readers");
            System.out.println("6. Notification debtors for returning taken books");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //sortedBooks.forEach(System.out::println);
                    LibraryManager.uniqueBooks(library);
                    break;
                case 2:
                    LibraryManager.printPopularReaders(library);
                    break;
                case 3:
                    System.out.print("Enter author name: ");
                    scanner.nextLine();
                    String authorName = scanner.nextLine();
                    LibraryManager.booksTakenInitialAuthor(library, authorName);
                    break;
                case 4:
                    LibraryManager.maxBooksTaken(library);
                    break;
                case 5:
                    LibraryManager.notificationsMailing(library);
                    break;
                case 6:
                    LibraryManager.debtorsInforming();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (choice != 0);
    }
}
