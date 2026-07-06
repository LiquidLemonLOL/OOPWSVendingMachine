package se.lexicon;

public abstract class Product {

    private int id;
    private String name;
    private int price;
    private int quantity;

    public Product(int id, String name, int price, int quantity) {
        setId(id);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setPrice(int price) {
        if (price < 1) {
            throw new IllegalArgumentException("Price must be a positive integer");
        }
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be a negative number");
        }
        this.quantity = quantity;
    }

    public boolean inStock() {
        return quantity > 0;
    }

    public void decreaseQuantity() {
        this.quantity -= 1;
    }

    public abstract String describe();

}
