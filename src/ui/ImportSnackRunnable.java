package ui;

import products.Coffee;
import products.Snack;
import vending.CoffeeMachine;
import vending.SnackMachine;
import vending.VendingException;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The {@code ImportSnackRunnable} class represents a runnable task for importing snack products into a snack vending machine.
 * It implements the Runnable interface to enable execution in a separate thread.
 */
public class ImportSnackRunnable implements Runnable {
    private String fileName;
    private SnackMachine vendingMachine;

    /**
     * Constructs a new ImportSnackRunnable with the specified file name and snack vending machine.
     *
     * @param file        the name of the file from which the snack products will be imported
     * @param vend  the snack vending machine instance to which the products will be imported
     */
    public ImportSnackRunnable(String file, SnackMachine vend) {
        this.fileName = file;
        this.vendingMachine = vend;
    }

    /**
     * Executes the import task by reading snack products from a file and adding them to the vending machine.
     * It handles ClassNotFoundException, IOException, and VendingException.
     */
    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Snack product = (Snack) ois.readObject();
                    vendingMachine.addSnack(product);
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
