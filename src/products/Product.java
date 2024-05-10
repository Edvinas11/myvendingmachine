package products;

import java.io.Serializable;

/**
 * The {@code Product} class represents a product in the vending machine.
 * It contains information such as the product's name, price, and ID.
 */
public class Product implements Serializable {
    private int price; // Price in cents
    private String name;
    private int id;
    private static int nextId = 1;

    /**
     * Constructs a new Product with the specified name and price.
     *
     * @param name the name of the product
     * @param price the price of the product in cents
     */
    public Product(String name, int price){
        this.name = name;
        this.price = price;
        this.id = nextId++;
    }

    // Getters
    /**
     * Gets the price of the product.
     *
     * @return the price of the product in cents
     */
    public int getPrice(){
        return price;
    }

    /**
     * Gets the name of the product.
     *
     * @return the name of the product
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the ID of the product.
     *
     * @return the ID of the product
     */
    public int getId() { return id; }

    // Setters
    /**
     * Sets the price of the product.
     *
     * @param price the price of the product in cents
     */
    public void setPrice(int price){
        this.price = price;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name){
        this.name = name;
    }

    // Additional methods
    /**
     * Returns a string representation of the product.
     *
     * @return a string representation of the product
     */
    @Override
    public String toString(){
        return "Product {" +
                "id: " + id +
                "name: " + name +
                ", price: " + price + " cents" +
                "}";
    }
}
