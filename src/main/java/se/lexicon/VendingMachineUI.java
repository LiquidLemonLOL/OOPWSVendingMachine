package se.lexicon;

import java.util.List;

public class VendingMachineUI {

    private final VendingMachine vendingMachine;

    public VendingMachineUI(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    public void start() {
        IO.println("Welcome to the Lexicon Vending Machine");
        IO.println("--------------------------------------------");
        listProducts();
        IO.println("--------------------------------------------\n");

        boolean active = true;
        while (active) {
            printMenu();
            String choice = IO.readln("Select an option: ").trim();

            switch (choice) {
                case "1" -> {
                    int val = Integer.parseInt(IO.readln("Enter coin value (1, 2, 5, 10, 20, 50): "));
                    IO.println(vendingMachine.insertCoin(val));
                    printBalance();
                }
                case "2" -> {
                    int id = Integer.parseInt(IO.readln("Enter product id: "));
                    IO.println(vendingMachine.selectProduct(id));
                    printBalance();
                }
                case "3" -> listProducts();
                case "4" -> {
                    IO.println(vendingMachine.requestBalance());
                    printBalance();
                }
                case "0" -> active = false;
                default -> IO.println("Invalid choice. Try again.");
            }

        }

    }

    private void listProducts() {
        List<Product> products = vendingMachine.getProducts();
        for (Product product : products) {
            IO.println(String.format("[%d] %-17s - %d kr | %s |  Stock: %d", product.getId(), product.getName(), product.getPrice(), product.describe(), product.getQuantity()));
        }
    }

    private void printMenu() {
        IO.println("1) Insert coins");
        IO.println("2) Select product");
        IO.println("3) List products");
        IO.println("4) Request balance");
        IO.println("0) Quit\n");

    }

    public void printBalance() {
        IO.println("Current balance is: " + vendingMachine.getBalance() +" kr");
    }


}
