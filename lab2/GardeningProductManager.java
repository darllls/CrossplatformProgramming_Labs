import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GardeningProductManager {
    public static List<GardeningProduct> sortByNameAscending(List<GardeningProduct> products) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(GardeningProduct::getName));
        return sortedProducts;
    }

    public static List<GardeningProduct> sortByNameDescending(List<GardeningProduct> products) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(GardeningProduct::getName).reversed());
        return sortedProducts;
    }

    public static List<GardeningProduct> sortByPriceAscending(List<GardeningProduct> products) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(GardeningProduct::getPrice));
        return sortedProducts;
    }

    public static List<GardeningProduct> sortByPriceAscending1(List<GardeningProduct> products) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        Collections.sort(sortedProducts);
        return sortedProducts;
    }

    public static List<GardeningProduct> sortByPriceDescending(List<GardeningProduct> products) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        sortedProducts.sort(Comparator.comparing(GardeningProduct::getPrice).reversed());
        return sortedProducts;
    }

    public static List<GardeningProduct> sortByPriceUsingLambda(List<GardeningProduct> products, boolean ascending) {
        List<GardeningProduct> sortedProducts = new ArrayList<>(products);
        if (ascending) {
            sortedProducts.sort(Comparator.comparing(GardeningProduct::getPrice));
        } else {
            sortedProducts.sort((product1, product2) -> Double.compare(product2.getPrice(), product1.getPrice()));
        }
        return sortedProducts;
    }

    public static List<GardeningProduct> searchForGardeningProductsHose(List<GardeningProduct> products,
            double minHoseLength) {
        return products.stream()
                .filter(product -> product instanceof Hose && ((Hose) product).getLength() >= minHoseLength)
                .collect(Collectors.toList());
    }

    public static List<GardeningProduct> searchForGardeningProductsHose1(List<GardeningProduct> products,
            double minHoseLength) {
        List<GardeningProduct> result = new ArrayList<>();

        for (GardeningProduct product : products) {
            if (product instanceof Hose) {
                Hose hose = (Hose) product;
                if (hose.getLength() >= minHoseLength) {
                    result.add(hose);
                }
            }
        }

        return result;
    }

    public static List<GardeningProduct> searchForGardeningProductsSprinkler(List<GardeningProduct> products) {
        return products.stream()
                .filter(product -> product instanceof Sprinkler)
                .collect(Collectors.toList());
    }
}
