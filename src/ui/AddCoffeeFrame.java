package ui;

import products.Coffee;
import vending.CoffeeMachine;
import vending.VendingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The {@code AddCoffeeFrame} class represents a frame for adding a new coffee product to a coffee vending machine.
 * It provides input fields for specifying the name, price, and required ingredients of the coffee product.
 */
public class AddCoffeeFrame extends JFrame {
    private JTextField nameField;
    private JTextField priceField;
    private JTextField beansField;
    private JTextField milkField;
    private JTextField waterField;
    private JButton createProductButton;

    /**
     * Constructs a new AddCoffeeFrame with input fields for adding a coffee product.
     *
     * @param vend the coffee vending machine instance to which the product will be added
     */
    public AddCoffeeFrame(CoffeeMachine vend) {
        setTitle("Add Product");
        setSize(300, 200);
        setLayout(new GridLayout(8, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        add(new JLabel("Required Beans:"));
        beansField = new JTextField();
        add(beansField);

        add(new JLabel("Required Milk:"));
        milkField = new JTextField();
        add(milkField);

        add(new JLabel("Required Water:"));
        waterField = new JTextField();
        add(waterField);

        createProductButton = new JButton("Create Product");
        createProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int price = Integer.parseInt(priceField.getText());
                int beans = Integer.parseInt(beansField.getText());
                int milk = Integer.parseInt(milkField.getText());
                int water = Integer.parseInt(waterField.getText());

                Coffee product = new Coffee(name, price, beans, milk, water);

                try {
                    // Add the product to the vending machine
                    vend.addCoffee(product);
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
