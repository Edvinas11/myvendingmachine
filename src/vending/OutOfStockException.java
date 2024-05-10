package vending;

/**
 * Represents an exception thrown when a vending machine is out of stock.
 */
public class OutOfStockException extends VendingException {
    /**
     * Constructs a new OutOfStockException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public OutOfStockException(String message) {
        super(message);
    }
}
