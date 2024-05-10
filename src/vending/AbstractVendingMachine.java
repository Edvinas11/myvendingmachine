package vending;

import Interfaces.BalanceManagement;
import products.Product;
import java.util.List;
import java.util.LinkedList;

/**
 * The {@code AbstractVendingMachine} class represents an abstract vending machine with basic functionality
 * for managing balance and vending products.
 */
public abstract class AbstractVendingMachine implements BalanceManagement {
    private String name;
    private int euro;
    private int cents;
    private static int count = 0;
    protected final int MAX_BALANCE_CAPACITY = 100;

    // Constructors
    /**
     * Constructs a new AbstractVendingMachine object with zero balance and an empty name.
     */
    public AbstractVendingMachine() {
        this.euro = 0;
        this.cents = 0;
        this.name = "";
        count++;
    }

    /**
     * Constructs a new AbstractVendingMachine object with the specified name and zero balance.
     *
     * @param name The name of the vending machine.
     */
    public AbstractVendingMachine(String name) {
        this();
        this.name = name;
    }

    // Getters
    /**
     * Retrieves the name of the vending machine.
     *
     * @return The name of the vending machine.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the balance in euros of the vending machine.
     *
     * @return The balance in euros.
     */
    public int getEuro() {
        return euro;
    }

    /**
     * Retrieves the balance in cents of the vending machine.
     *
     * @return The balance in cents.
     */
    public int getCents() {
        return cents;
    }

    /**
     * Retrieves the total count of AbstractVendingMachine objects created.
     *
     * @return The total count of AbstractVendingMachine objects.
     */
    public static int getObjectCount() {
        return count;
    }

    // Setters
    /**
     * Sets the name of the vending machine.
     *
     * @param name The new name of the vending machine.
     */
    public void setName(String name) {
        this.name = name;
    }

    // Balance Management methods
    /**
     * Inserts money into the vending machine balance in euros and cents.
     *
     * @param euro  The amount of money in euros to insert.
     * @param cents The amount of money in cents to insert.
     * @throws VendingException If the inserted money exceeds the maximum capacity of the vending machine.
     */
    @Override
    public void insertMoney(int euro, int cents) throws VendingException {
        if (euro < 0 || cents < 0) {
            throw new IllegalArgumentException("Euro and cents values must be positive.");
        }

        int balance = this.euro * 100 + this.cents + euro * 100 + cents;

        if (balance > MAX_BALANCE_CAPACITY * 100) {
            throw new ExceededCapacityException("Maximum capacity exceeded. Cannot insert money.");
        }

        this.euro += euro;
        this.cents += cents;

        if (this.cents >= 100) {
            this.euro += this.cents / 100;
            this.cents %= 100;
        }
    }

    /**
     * Inserts money into the vending machine balance in cents.
     *
     * @param cents The amount of money in cents to insert.
     * @throws VendingException If the inserted money exceeds the maximum capacity of the vending machine.
     */
    @Override
    public void insertMoney(int cents) throws VendingException {
        if (cents < 0) {
            throw new IllegalArgumentException("Cents values must be positive.");
        }

        int balance = this.euro * 100 + this.cents + cents;

        if (balance > MAX_BALANCE_CAPACITY * 100) {
            throw new ExceededCapacityException("Maximum capacity exceeded. Cannot insert money.");
        }

        this.cents += cents;

        if (this.cents >= 100) {
            this.euro += this.cents / 100;
            this.cents %= 100;
        }
    }

    /**
     * Deducts a specified amount from the vending machine balance.
     *
     * @param priceInCents The amount to deduct from the balance in cents.
     */
    @Override
    public void deductBalance(int priceInCents) {
        int currentBalance = this.euro * 100 + this.cents;
        currentBalance -= priceInCents;

        if (currentBalance <= 0) {
            currentBalance = 0;
        }

        this.euro = currentBalance / 100;
        this.cents = currentBalance % 100;
    }

    // Abstract method
    /**
     * Returns a string representation of the vending machine.
     *
     * @return A string representation of the vending machine.
     */
    public abstract String toString();
}
