package vending;

/**
 * Represents an exception thrown when there are insufficient resources to perform an operation in a vending machine.
 */
public class InsufficientResourceException extends VendingException {
    private int amount;

    /**
     * Constructs a new InsufficientResourceException with the specified detail message and amount.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param amount The amount of the resource that is insufficient.
     */
    public InsufficientResourceException(String message, int amount) {
        super(message);
        this.amount = amount;
    }

    /**
     * Gets the amount of the resource that is insufficient.
     *
     * @return The amount of the resource that is insufficient.
     */
    public int getAmount(){
        return amount;
    }
}
