import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class HomeAppliance {
    private String name;
    private String model;
    private String manufacturer;
    private double price;
    private int maxPower;

    public HomeAppliance(String name, String model, String manufacturer, double price, int maxPower) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.price = price;
        this.maxPower = maxPower;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxPower() {
        return maxPower;
    }
}

public class Main {
    public static void main(String[] args) {
        List<HomeAppliance> homeAppliances = new ArrayList<>();

        // Зчитування інформації з файлу та створення об'єктів HomeAppliance
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String name = data[0];
                    String model = data[1];
                    String manufacturer = data[2];
                    double price = Double.parseDouble(data[3]);
                    int maxPower = Integer.parseInt(data[4]);
                    homeAppliances.add(new HomeAppliance(name, model, manufacturer, price, maxPower));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;

            while (!exit) {
                System.out.println("Меню:");
                System.out.println("1. Створити карту виробникiв та моделей");
                System.out.println("2. Знайти частотну характеристику рiзних назв");
                System.out.println("3. Зчитати iнформацiю з iнших файлiв та об'єднати її");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Очищення буфера

                switch (choice) {
                    case 1:
                        Map<String, List<String>> manufacturerModelMap = createManufacturerModelMap(homeAppliances);
                        printNFirstModels(manufacturerModelMap);
                        break;
                    case 2:
                        Map<String, Long> nameFrequencyMap = findNameFrequency(homeAppliances);
                        printNameFrequency(nameFrequencyMap);
                        break;
                    case 3:
                        List<HomeAppliance> secondHomeAppliances = readSecondFile("second_file.txt");
                        List<HomeAppliance> combinedList = combineAndSort(homeAppliances, secondHomeAppliances);
                        int appliancesCount = countAppliances(combinedList);
                        double totalValue = calculateTotalValue(combinedList);
                        System.out.println("Кiлькiсть приладiв: " + appliancesCount);
                        System.out.println("Сумарна вартiсть всiх приладiв: " + totalValue);
                        System.out.println("Спiльна колекцiя вiдсортована за вартiстю в зворотному порядку:");
                        printSortedCollection(combinedList);
                        break;
                    default:
                        exit = true;
                        break;
                }
            }
        }
    }

    private static Map<String, List<String>> createManufacturerModelMap(List<HomeAppliance> homeAppliances) {
        return homeAppliances.stream()
                .collect(Collectors.groupingBy(HomeAppliance::getManufacturer,
                        Collectors.mapping(HomeAppliance::getModel, Collectors.toList())));
    }

    private static void printNFirstModels(Map<String, List<String>> manufacturerModelMap) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введiть n: ");
        int n = scanner.nextInt();

        for (Map.Entry<String, List<String>> entry : manufacturerModelMap.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            List<String> models = entry.getValue();

            for (int i = 0; i < Math.min(n, models.size()); i++) {
                System.out.print(models.get(i));
                if (i < n - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }

    private static Map<String, Long> findNameFrequency(List<HomeAppliance> homeAppliances) {
        return homeAppliances.stream()
                .collect(Collectors.groupingBy(HomeAppliance::getName, Collectors.counting()));
    }

    private static void printNameFrequency(Map<String, Long> nameFrequencyMap) {
        for (Map.Entry<String, Long> entry : nameFrequencyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " шт.");
        }
    }

    private static List<HomeAppliance> readSecondFile(String filename) {
        List<HomeAppliance> secondHomeAppliances = new ArrayList<>();
        // Додайте код для зчитування інформації з іншого файлу
        try (BufferedReader reader = new BufferedReader(new FileReader("input2.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String name = data[0];
                    String model = data[1];
                    String manufacturer = data[2];
                    double price = Double.parseDouble(data[3]);
                    int maxPower = Integer.parseInt(data[4]);
                    secondHomeAppliances.add(new HomeAppliance(name, model, manufacturer, price, maxPower));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return secondHomeAppliances;
    }

    private static List<HomeAppliance> combineAndSort(List<HomeAppliance> list1, List<HomeAppliance> list2) {
        List<HomeAppliance> combinedList = new ArrayList<>(list1);
        combinedList.addAll(list2);
        Collections.sort(combinedList, Comparator.comparingDouble(HomeAppliance::getPrice).reversed());
        return Collections.unmodifiableList(combinedList);
    }

    private static void printSortedCollection(List<HomeAppliance> collection) {
        collection.forEach(item -> {
            System.out.println(item.getName() + ", " + item.getModel() + ", " + item.getManufacturer() + ", "
                    + item.getPrice() + ", " + item.getMaxPower());
        });
    }

    private static int countAppliances(List<HomeAppliance> homeAppliances) {
        return homeAppliances.size();
    }

    private static double calculateTotalValue(List<HomeAppliance> homeAppliances) {
        double totalValue = 0.0;
        for (HomeAppliance appliance : homeAppliances) {
            totalValue += appliance.getPrice();
        }
        return totalValue;
    }
}
