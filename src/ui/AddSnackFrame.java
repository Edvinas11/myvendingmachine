package ui;

import products.Coffee;
import products.Snack;
import vending.CoffeeMachine;
import vending.SnackMachine;
import vending.VendingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code AddSnackFrame} class represents a frame for adding a new snack product to a snack vending machine.
 * It provides input fields for specifying the name, price, and quantity of the snack product.
 */
public class AddSnackFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JButton createProductButton;

    /**
     * Constructs a new AddSnackFrame with input fields for adding a snack product.
     *
     * @param vend the snack vending machine instance to which the product will be added
     */
    public AddSnackFrame(SnackMachine vend) {
        setTitle("Add Product");
        setSize(300, 200);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        createProductButton = new JButton("Create Product");
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());

                Snack product = new Snack(name, price, quantity);

                try {
                    // Add the product to the vending machine
                    vend.addSnack(product);
                } catch (VendingException ex) {
                    throw new RuntimeException(ex);
                }

                dispose();
            }
        });
        add(createProductButton);

        setVisible(true);
    }
}
