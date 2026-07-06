
# Class Diagram: Vending Machine WS

```mermaid
classDiagram
    Direction RL
    VendingMachineUI <-- VendingMachine : uses
    Product "1..*" --* "1" VendingMachine : stocks
    Product <|-- Snack
    Product <|-- Beverage
    Product <|-- Fruit
    class Product {
        <<abstract>>
        -int id
        -String name
        -int price
        -int quantity
        +getId() int
        +getName() String
        +getPrice() int
        +getQuantity() int
        +describe() String
    }
    class Beverage {
        +int volumeMl
        +describe() String
    }
    class Fruit {
        +String origin
        +describe() String
    }
    class Snack {
        +int weightGrams
        +describe() String
    }
    class VendingMachine {
        -List~Product~ products
        -int balance
        +insertCoin(int value) void
        +requestChange() void
        +selectProduct(int id) void
        +listProducts() List~Product~
        +getBalance() void
    }
    class VendingMachineUI {
        -VendingMachine vendingMachine
        +start() void
    }


```