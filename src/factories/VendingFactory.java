package factories;

import vending.CoffeeMachine;
import vending.SnackMachine;

/**
 * The {@code VendingFactory} class provides methods for creating instances of CoffeeMachine and SnackMachine
 * using the abstract factory pattern.
 * These methods enable the creation of vending machines with specific names.
 */
public class VendingFactory {
    /**
     * Creates a new CoffeeMachine instance with the specified name.
     *
     * @param name The name of the coffee machine.
     * @return A new CoffeeMachine instance with the given name.
     */
    public CoffeeMachine createCoffeeMachine(String name){
        return new CoffeeMachine(name);
    }

    /**
     * Creates a new SnackMachine instance with the specified name.
     *
     * @param name The name of the snack machine.
     * @return A new SnackMachine instance with the given name.
     */
    public SnackMachine createSnackMachine(String name){
        return new SnackMachine(name);
    }
}
