package ui;

import products.Coffee;
import vending.CoffeeMachine;
import vending.VendingException;

import java.io.*;

/**
 * The {@code ImportCoffeeRunnable} class represents a runnable task for importing coffee products into a coffee vending machine.
 * It implements the Runnable interface to enable execution in a separate thread.
 */
public class ImportCoffeeRunnable implements Runnable {
    private String fileName;
    private CoffeeMachine vendingMachine;

    /**
     * Constructs a new ImportCoffeeRunnable with the specified file name and coffee vending machine.
     *
     * @param file        the name of the file from which the coffee products will be imported
     * @param vend  the coffee vending machine instance to which the products will be imported
     */
    public ImportCoffeeRunnable(String file, CoffeeMachine vend) {
        this.fileName = file;
        this.vendingMachine = vend;
    }

    /**
     * Executes the import task by reading coffee products from a file and adding them to the vending machine.
     * It handles ClassNotFoundException, IOException, and VendingException.
     */
    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Coffee product = (Coffee) ois.readObject();
                    vendingMachine.addCoffee(product);
                } catch (EOFException e) {
                    break;
                } catch (ClassNotFoundException | IOException | VendingException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
