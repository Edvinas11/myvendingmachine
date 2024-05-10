package vending;

/**
 * Represents an exception thrown when the capacity of a vending machine is exceeded.
 */
public class ExceededCapacityException extends VendingException {
    /**
     * Constructs a new ExceededCapacityException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ExceededCapacityException(String message) {
        super(message);
    }
}
