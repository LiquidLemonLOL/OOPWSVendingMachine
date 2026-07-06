package se.lexicon;


import java.util.ArrayList;
import java.util.List;

public class App {

    static void main() {

        List<Product> products = new ArrayList<>();
        products.add(new Snack(1, "Chips", 15, 5, 130));
        products.add(new Beverage(2, "Cola", 20, 3, 330));
        products.add(new Fruit(3, "Apple", 10, 8, "Sweden"));

        VendingMachine vendingMachine = new VendingMachine(products);

        VendingMachineUI vendingMachineUI = new VendingMachineUI(vendingMachine);
        vendingMachineUI.start();
    }
}
