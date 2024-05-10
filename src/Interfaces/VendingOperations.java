package Interfaces;

import vending.VendingException;

/**
 * The {@code VendingOperations} interface represents the basic operations
 * that can be performed in a vending machine.
 */
public interface VendingOperations{
    /**
     * Inserts money into the vending machine.
     *
     * @param euro the amount of money in euros
     * @param cents the amount of money in cents
     * @throws VendingException if an error occurs while inserting money
     */
    void insertMoney(int euro, int cents) throws VendingException;

    /**
     * Inserts money into the vending machine.
     *
     * @param cents the amount of money in cents
     * @throws VendingException if an error occurs while inserting money
     */
    void insertMoney(int cents) throws VendingException;
}
