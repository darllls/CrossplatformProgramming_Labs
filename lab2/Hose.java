public class Hose extends GardeningProduct {
    private double length;

    public Hose(String name, double price, double length) {
        super(name, price);
        this.length = length;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Hose{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", length=" + length +
                '}';
    }
}
