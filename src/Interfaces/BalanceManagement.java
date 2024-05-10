package Interfaces;

/**
 * The {@code BalanceManagement} interface represents the management of balance
 * in a vending machine, extending the {@link VendingOperations} interface.
 * It defines methods for deducting balance based on the price of items.
 */
public interface BalanceManagement extends VendingOperations {
    /**
     * Deducts the specified price from the balance in the vending machine.
     *
     * @param priceInCents the price of the item to be deducted in cents
     */
    void deductBalance(int priceInCents);
}
