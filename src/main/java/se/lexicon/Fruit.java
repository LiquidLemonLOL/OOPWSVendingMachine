package se.lexicon;

public class Fruit extends Product {

    private String origin;

    public Fruit(int id, String name, int price, int quantity, String origin) {
        super(id, name, price, quantity);
        setOrigin(origin);
    }

    public String getOrigin() { return origin; }

    void setOrigin(String origin) {
        if (origin == null || origin.isEmpty()) {
            throw new IllegalArgumentException("Origin must not be null or empty");
        }
        this.origin = origin;
    }

    @Override
    public String describe() {
        return getName() + " (" + getClass().getSimpleName() + ", " + getOrigin() + ")";
    }
}
