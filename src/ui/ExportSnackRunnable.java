package ui;

import products.Coffee;
import products.Snack;
import vending.CoffeeMachine;
import vending.SnackMachine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * The {@code ExportSnackRunnable} class represents a runnable task for exporting snack products from a snack vending machine.
 * It implements the Runnable interface to enable execution in a separate thread.
 */
public class ExportSnackRunnable implements Runnable{
    private String fileName;
    private SnackMachine vendingMachine;

    /**
     * Constructs a new ExportSnackRunnable with the specified file name and snack vending machine.
     *
     * @param fileName the name of the file to which the snack products will be exported
     * @param vend the snack vending machine instance from which the products will be exported
     */
    public ExportSnackRunnable(String fileName, SnackMachine vend) {
        this.fileName = fileName;
        this.vendingMachine = vend;
    }

    /**
     * Executes the export task by serializing snack products from the vending machine and writing them to a file.
     */
    @Override
    public void run() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            List<Snack> products = vendingMachine.getProducts();
            for (Snack product : products) {
                oos.writeObject(product);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
