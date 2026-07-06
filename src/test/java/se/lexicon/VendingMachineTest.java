package se.lexicon;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setup() {
        List<Product> products = new ArrayList<>();
        vendingMachine = new VendingMachine(products);
    }

    @Test
    @DisplayName("Insert Valid Coin")
    public void testInsertValidCoin() {
        //Balance = 0 is default case at init
        vendingMachine.insertCoin(10);
        assertEquals(10, vendingMachine.getBalance());
    }

    @Test
    @DisplayName("Reject Invalid Coin")
    public void testRejectInvalidCoin() {
        vendingMachine.insertCoin(7);
        assertEquals(0, vendingMachine.getBalance());
    }

    @Test
    @DisplayName("Purchase succeeds")
    public void testPurchaseSuccess() {
        Product product = new Snack(1, "Chips", 20, 3, 130);
        vendingMachine.addProduct(product);
        vendingMachine.insertCoin(20);
        assertAll(
                //should return description of product + empty balance, double check through both print statement and values might be redundant?
                () -> assertEquals("Dispensing: Chips (Snack, 130g)", vendingMachine.selectProduct(1)),
                () -> assertEquals(0, vendingMachine.getBalance()),
                () -> assertEquals(2, vendingMachine.getProducts().getFirst().getQuantity())
        );
    }


    @Test
    @DisplayName("Purchase fails")
    public void testPurchaseFail() {
        Product product = new Snack(1, "Chips", 20, 1, 130);
        vendingMachine.addProduct(product);
        vendingMachine.insertCoin(10);
        assertAll(
                () -> assertEquals("Insufficient funds, Chips costs 20 kr. Missing amount: 10 kr", vendingMachine.selectProduct(1)),
                () -> assertEquals(10, vendingMachine.getBalance()),
                () -> assertEquals(1, vendingMachine.getProducts().getFirst().getQuantity())
        );
    }

    @Test
    @DisplayName("Purchase fails - out of stock")
    public void testPurchaseFailOutOfStock() {
        Product product = new Snack(1, "Chips", 10, 0, 130);
        vendingMachine.addProduct(product);
        vendingMachine.insertCoin(10);
        assertAll(
                () -> assertEquals("Chips is out of stock.", vendingMachine.selectProduct(1)),
                () -> assertEquals(0, vendingMachine.getProducts().getFirst().getQuantity()),
                () -> assertEquals(10, vendingMachine.getBalance())
        );
    }

    @Test
    @DisplayName("Return change")
    public void testReturnChange() {
        vendingMachine.insertCoin(50);
        assertAll(
                () -> assertEquals(50, vendingMachine.getBalance()),
                () -> assertEquals("Balance returned, 50 kr received from Vending Machine.",
                        vendingMachine.requestBalance()),
                () -> assertEquals(0, vendingMachine.getBalance())
        );
    }

    @Test
    @DisplayName("Product list")
    public void testProductList() {
        vendingMachine.addProduct(new Snack(1, "Chips", 15, 5, 130));
        vendingMachine.addProduct(new Beverage(2, "Cola", 20, 3, 330));
        vendingMachine.addProduct(new Fruit(3, "Apple", 10, 8, "Sweden"));
        assertEquals(3, vendingMachine.getProducts().size());
    }



}
