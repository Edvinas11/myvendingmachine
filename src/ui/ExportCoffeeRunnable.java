package ui;

import products.Coffee;
import vending.CoffeeMachine;

import java.io.*;
import java.util.List;

/**
 * The {@code ExportCoffeeRunnable} class represents a runnable task for exporting coffee products from a coffee vending machine.
 * It implements the Runnable interface to enable execution in a separate thread.
 */
public class ExportCoffeeRunnable implements Runnable{
    private String fileName;
    private CoffeeMachine vendingMachine;

    /**
     * Constructs a new ExportCoffeeRunnable with the specified file name and coffee vending machine.
     *
     * @param fileName the name of the file to which the coffee products will be exported
     * @param vend the coffee vending machine instance from which the products will be exported
     */
    public ExportCoffeeRunnable(String fileName, CoffeeMachine vend) {
        this.fileName = fileName;
        this.vendingMachine = vend;
    }

    /**
     * Executes the export task by serializing coffee products from the vending machine and writing them to a file.
     */
    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            List<Coffee> products = vendingMachine.getProducts();
            for (Coffee product : products) {
                oos.writeObject(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
