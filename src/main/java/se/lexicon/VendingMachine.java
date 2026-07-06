package se.lexicon;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class VendingMachine {


    //Class only handles logic, does not prompt for any user input
    private static final Set<Integer> ACCEPTED_COINS = Set.of(1, 2, 5, 10, 20, 50);
    private final List<Product> products;
    private int balance;

    public VendingMachine(List<Product> products) {
        this.products = products;
        this.balance = 0;
    }

    public List<Product> getProducts() { return products; }
    public int getBalance() { return balance; }

    public String insertCoin(int value) {
        if (!isValidCoinValue(value)) {
            return value + " is an invalid coin value, must be 1, 2, 5, 10, 20 or 50 kr";
        }
        balance += value;
        return value + " kr has been inserted into Vending Machine.";
    }

    public String requestBalance() {
        if (balance == 0) {
            return "Balance is 0, no coins returned.";
        }
        int returned = balance;
        balance = 0;
        return "Balance returned, " + returned + " kr received from Vending Machine.";
    }

    public String selectProduct(int id) {
        // Using streams and Optional because fun + need to learn, the method itself
        //Finds first product in the List that matches the id set by the user
        Optional<Product> selectedProduct = products.stream().filter
                (product -> product.getId() == id).findFirst();

        if (selectedProduct.isEmpty()) {
            return "There is no product with the id " + id + ".";
        }

        Product product = selectedProduct.get();

        if (!product.inStock()) {
            return product.getName() + " is out of stock.";
        }

        if (balance < product.getPrice()) {
            return "Insufficient funds, " + product.getName() + " costs " + product.getPrice() + " kr."
                    + " Missing amount: " + (product.getPrice() - balance) + " kr";
        }

        balance -= product.getPrice();
        product.decreaseQuantity();

        if (balance > 0) {
            IO.println("Change returned : " + getBalance() + " kr");
            balance = 0;
        }
        return "Dispensing: " + product.describe();
    }

    public static boolean isValidCoinValue(int value) {
        return ACCEPTED_COINS.contains(value);
    }

    //helper method
    public void addProduct(Product product) {
        products.add(product);
    }


}
