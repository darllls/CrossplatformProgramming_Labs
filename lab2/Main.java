import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<GardeningProduct> products = new ArrayList<>();
        products.add(new Hose("Гумовий шланг", 25.0, 20.0));
        products.add(new Hose("Гумовий шланг", 15.0, 10.0));
        products.add(new Hose("Гумовий шланг", 21.0, 17.0));
        products.add(new Pump("Електричний насос", 60.0));
        products.add(new Sprinkler("Ручний розпилювач", 12.0));
        products.add(new Sprinkler("Автоматичний розпилювач", 30.0));

        System.out.println("Початковий список товарів:");
        for (GardeningProduct product : products) {
            System.out.println(product);
        }

        List<GardeningProduct> sortedByNameAscending = GardeningProductManager.sortByNameAscending(products);

        System.out.println("\nСортування за іменем (зростання):");
        for (GardeningProduct product : sortedByNameAscending) {
            System.out.println(product);
        }

        List<GardeningProduct> sortedByNameDescending = GardeningProductManager.sortByNameDescending(products);

        System.out.println("\nСортування за іменем (спадання):");
        for (GardeningProduct product : sortedByNameDescending) {
            System.out.println(product);
        }

        List<GardeningProduct> sortedByPriceAscending = GardeningProductManager.sortByPriceAscending(products);

        System.out.println("\nСортування за ціною (зростання):");
        for (GardeningProduct product : sortedByPriceAscending) {
            System.out.println(product);
        }

        List<GardeningProduct> sortedByPriceDescending = GardeningProductManager.sortByPriceDescending(products);

        System.out.println("\nСортування за ціною (спадання):");
        for (GardeningProduct product : sortedByPriceDescending) {
            System.out.println(product);
        }

        List<GardeningProduct> gardeningProducts = GardeningProductManager.searchForGardeningProductsHose(products,
                15.0);

        System.out.println("\nТовари для поливу квітів (шланги з довжиною 15 та більше):");
        for (GardeningProduct product : gardeningProducts) {
            System.out.println(product);
        }

        List<GardeningProduct> gardeningProductsSprinkler = GardeningProductManager
                .searchForGardeningProductsSprinkler(products);

        System.out.println("\nТовари для поливу квітів (розпилювач):");
        for (GardeningProduct product : gardeningProductsSprinkler) {
            System.out.println(product);
        }
    }
}