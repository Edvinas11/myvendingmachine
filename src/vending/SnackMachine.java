package vending;

import products.Snack;

import java.util.List;
import java.util.LinkedList;

/**
 * The {@code SnackMachine} class represents a Snack Machine, extending an abstract Vending Machine.
 * It manages the list of available snack products and the client's balance.
 */
public class SnackMachine extends AbstractVendingMachine {
    private List<Snack> products;

    private final int MAX_SNACK_CAPACITY = 10;
    private final int MAX_BALANCE_CAPACITY = 40;

    /**
     * Constructor for Snack Machine
     *
     * @param name name of the vending machine
     */
    public SnackMachine(String name){
        super(name);
        products = new LinkedList<>();
    }

    // Getters
    /**
     * Gets the list of available snack products in the snack machine.
     *
     * @return The list of available snack products.
     */
    public List<Snack> getProducts(){
        return products;
    }

    // Setters
    /**
     * Sets the list of available snack products in the snack machine.
     *
     * @param products The list of snack products to set.
     */
    public void setProducts(List<Snack> products){
        this.products = products;
    }

    /**
     * Adds a new snack product to the snack machine.
     *
     * @param snack The snack product to add.
     * @throws VendingException If the maximum capacity of snack products is exceeded.
     */
    public void addSnack(Snack snack) throws VendingException {
        if(products.size() < MAX_SNACK_CAPACITY){
            products.add(snack);
        } else {
            throw new ExceededCapacityException("Cannot add more snack products. Maximum capacity reached.");
        }
    }

    /**
     * Inserts money into the snack machine.
     *
     * @param cents The amount of money in cents to insert.
     * @throws VendingException If an error occurs during money insertion.
     */
    @Override
    public void insertMoney(int cents) throws VendingException{

        int balance = super.getEuro() * 100 + super.getCents() + cents;

        super.insertMoney(cents);
    }

    /**
     * Buys a snack from the snack machine using the provided snack ID.
     *
     * @param id The ID of the snack product to buy.
     * @throws VendingException If an error occurs during the purchase process.
     */
    public void buySnack(int id) throws VendingException{
        // Check if provided id is correct.
        if(!(id > 0 && id <= products.size())) {
            throw new IllegalArgumentException("Invalid snack ID.");
        }

        Snack snackToBuy = null;
        try {
            snackToBuy = products.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid snack ID.");
        }

        // Check if user has enough money
        int snackPrice = snackToBuy.getPrice();
        if((super.getEuro() * 100 + super.getCents()) < snackPrice){
            throw new InsufficientBalanceException("Insufficient funds. Please insert more money.");
        }

        // Check quantity
        int currentQuantity = snackToBuy.getQuantity();
        if (currentQuantity <= 0) {
            throw new OutOfStockException("No more units of this snack available.");
        }

        // Deduct price
        deductBalance(snackToBuy.getPrice());

        // Update quantity
        snackToBuy.setQuantity(currentQuantity - 1);

        // Remove snack item if quantity is 0
        if (currentQuantity == 1) {
            products.remove(snackToBuy);
        }
    }

    /**
     * Returns a string representation of the SnackMachine object.
     *
     * @return A string representation of the SnackMachine object.
     */
    @Override
    public String toString(){
        return "SnackMachine {" +
                "name: " + super.getName() +
                ", euro: " + super.getEuro() +
                ", cents: " + super.getCents() +
                ", snacks: " + products +
                "}";
    }
}
