public class GardeningProduct implements Comparable<GardeningProduct> {
    protected String name;
    protected double price;

    public GardeningProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "GardeningProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(GardeningProduct other) {
        return Double.compare(this.getPrice(), other.getPrice());
    }
}
