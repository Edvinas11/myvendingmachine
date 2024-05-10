package vending;

/**
 * Represents a generic exception that can occur in a vending machine.
 */
public class VendingException extends Exception {
    /**
     * Constructs a new VendingException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public VendingException(String message){
        super(message);
    }
}

