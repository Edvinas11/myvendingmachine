package products;

import java.io.Serializable;

/**
 * The {@code Coffee} class represents a type of product specifically for coffee in the vending machine.
 * It extends the {@link Product} class and contains additional properties such as the required amounts
 * of coffee beans, milk, and water to make a cup of coffee.
 */
public class Coffee extends Product implements Cloneable, Serializable {
    private int requiredBeans;
    private int requiredMilk;
    private int requiredWater;

    /**
     * Constructs a new Coffee product with the specified name, price, and required ingredients.
     *
     * @param name          the name of the coffee
     * @param price         the price of the coffee in cents
     * @param requiredBeans the required amount of coffee beans
     * @param requiredMilk  the required amount of milk
     * @param requiredWater the required amount of water
     */
    public Coffee(String name, int price, int requiredBeans, int requiredMilk, int requiredWater){
        super(name, price);
        this.requiredBeans = requiredBeans;
        this.requiredMilk = requiredMilk;
        this.requiredWater = requiredWater;
    }

    // Getters
    /**
     * Gets the required amount of coffee beans for this coffee.
     *
     * @return the required amount of coffee beans
     */
    public int getRequiredBeans(){
        return requiredBeans;
    }

    /**
     * Gets the required amount of milk for this coffee.
     *
     * @return the required amount of milk
     */
    public int getRequiredMilk(){
        return requiredMilk;
    }

    /**
     * Gets the required amount of water for this coffee.
     *
     * @return the required amount of water
     */
    public int getRequiredWater(){
        return requiredWater;
    }

    // Setters
    /**
     * Sets the required amount of coffee beans for this coffee.
     *
     * @param requiredBeans the required amount of coffee beans
     */
    public void setRequiredBeans(int requiredBeans){
        this.requiredBeans = requiredBeans;
    }

    /**
     * Sets the required amount of milk for this coffee.
     *
     * @param requiredMilk the required amount of milk
     */
    public void setRequiredMilk(int requiredMilk){
        this.requiredMilk = requiredMilk;
    }

    /**
     * Sets the required amount of water for this coffee.
     *
     * @param requiredWater the required amount of water
     */
    public void setRequiredWater(int requiredWater){
        this.requiredWater = requiredWater;
    }

    // Additional methods
    /**
     * Returns a string representation of the coffee product.
     *
     * @return a string representation of the coffee
     */
    @Override
    public String toString(){
        return "Coffee {" +
                "id: " + super.getId() +
                ", name: " + super.getName() +
                ", price: " + super.getPrice() + " cents" +
                ", required beans amount: " + requiredBeans +
                ", required milk amount: " + requiredMilk +
                ", required water amount: " + requiredWater +
                "}";
    }

    /**
     * Creates and returns a copy of this coffee product.
     *
     * @return a clone of this coffee product
     */
    @Override
    public Coffee clone() {
        try {
            Coffee clone = (Coffee) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
