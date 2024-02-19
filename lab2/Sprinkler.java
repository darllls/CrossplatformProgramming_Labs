public class Sprinkler extends GardeningProduct {
    public Sprinkler(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return "Sprinkler{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
