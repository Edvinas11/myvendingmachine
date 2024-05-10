package products;

import java.io.Serializable;

/**
 * The {@code Snack} class represents a type of product specifically for snacks in the vending machine.
 * It extends the {@link Product} class and contains additional properties such as the quantity available.
 */
public class Snack extends Product implements Serializable {
    private int quantity;

    /**
     * Constructs a new Snack product with the specified name, price, and quantity.
     *
     * @param name     the name of the snack
     * @param price    the price of the snack in cents
     * @param quantity the quantity of the snack available
     */
    public Snack(String name, int price, int quantity){
        super(name, price);
        this.quantity = quantity;
    }

    // Getters
    /**
     * Gets the quantity of this snack available.
     *
     * @return the quantity of this snack
     */
    public int getQuantity() {
        return quantity;
    }

    // Setters
    /**
     * Sets the quantity of this snack.
     *
     * @param quantity the quantity of this snack
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Additional methods
    /**
     * Returns a string representation of the snack product.
     *
     * @return a string representation of the snack
     */
    @Override
    public String toString(){
        return "Snack {" +
                "name: " + super.getName() +
                ", price: " + super.getPrice() + " cents" +
                ", quantity: " + quantity +
                "}";
    }
}
