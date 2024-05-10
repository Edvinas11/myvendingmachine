package vending;

/**
 * Represents an exception thrown when there is insufficient balance in a vending machine.
 */
public class InsufficientBalanceException extends VendingException {
    /**
     * Constructs a new InsufficientBalanceException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
