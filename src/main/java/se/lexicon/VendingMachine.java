package se.lexicon;

import java.util.List;
import java.util.Optional;

public class VendingMachine {


    //Class only handles logic, does not prompt for any user input
    private final List<Product> products;
    private int balance;

    public VendingMachine(List<Product> products) {
        this.products = products;
        this.balance = 0;
    }

    public List<Product> getProducts() { return products; }
    public int getBalance() { return balance; }

    public String insertCoin(int value) {
        Optional<Coin> coin = Coin.fromValue(value);
        if (coin.isPresent()) {
            balance += coin.get().getValue();
        } else {
            return "Invalid coin, must be 1, 2, 5, 10, 20 or 50.";
        }
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
            IO.println(requestBalance());
        }
        return "Dispensing: " + product.describe();
    }

    //helper method
    public void addProduct(Product product) {
        products.add(product);
    }


}
