package factories;

import products.Coffee;
import products.Product;
import products.Snack;

/**
 * The {@code ProductFactory} class provides methods for creating instances of different types of products,
 * including generic products, coffees, and snacks using the abstract factory pattern.
 * These methods enable the creation of vending machines with specific names.
 */
public class ProductFactory {
    /**
     * Creates a new generic Product instance with the specified name and price.
     *
     * @param name  The name of the product.
     * @param price The price of the product.
     * @return A new Product instance with the given name and price.
     */
    public Product createProduct(String name, int price){
        return new Product(name, price);
    }

    /**
     * Creates a new Coffee instance with the specified name, price, and requirements for brewing.
     *
     * @param name           The name of the coffee.
     * @param price          The price of the coffee.
     * @param requiredBeans  The amount of coffee beans required to brew the coffee.
     * @param requiredMilk   The amount of milk required to brew the coffee.
     * @param requiredWater  The amount of water required to brew the coffee.
     * @return A new Coffee instance with the given parameters.
     */
    public Coffee createCoffee(String name, int price, int requiredBeans, int requiredMilk, int requiredWater){
        return new Coffee(name, price, requiredBeans, requiredMilk, requiredWater);
    }

    /**
     * Creates a new Snack instance with the specified name, price, and quantity.
     *
     * @param name     The name of the snack.
     * @param price    The price of the snack.
     * @param quantity The quantity of the snack.
     * @return A new Snack instance with the given parameters.
     */
    public Snack createSnack(String name, int price, int quantity){
        return new Snack(name, price, quantity);
    }
}
