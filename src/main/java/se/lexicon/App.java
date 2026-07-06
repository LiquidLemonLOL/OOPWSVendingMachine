package se.lexicon;


import java.util.ArrayList;
import java.util.List;

public class App {

    static void main() {

        List<Product> products = new ArrayList<>();
        VendingMachine vendingMachine = new VendingMachine(products);
        vendingMachine.addProduct(new Snack(1, "Chips", 15, 5, 130));
        vendingMachine.addProduct(new Beverage(2, "Cola", 20, 3, 330));
        vendingMachine.addProduct(new Fruit(3, "Apple", 10, 8, "Sweden"));

        VendingMachineUI vendingMachineUI = new VendingMachineUI(vendingMachine);
        vendingMachineUI.start();
    }
}
