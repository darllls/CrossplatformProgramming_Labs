public class Pump extends GardeningProduct {
    public Pump(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return "Pump{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
