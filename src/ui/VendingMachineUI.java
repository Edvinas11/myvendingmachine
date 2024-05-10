package ui;

import factories.VendingFactory;
import products.Coffee;
import products.Snack;
import vending.CoffeeMachine;
import vending.SnackMachine;
import vending.VendingException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

/**
 * The {@code VendingMachineUI} class represents the user interface for creating and interacting with vending machines.
 * It allows users to create either a coffee machine or a snack machine and perform various actions such as adding products,
 * increasing balance, and brewing coffee or buying snacks.
 */
public class VendingMachineUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JFrame coffeeMachineFrame;
    private JFrame snackMachineFrame;
    private JComboBox<String> vendingTypeComboBox;
    private JTextField vendingNameInput;
    private JLabel statusMessage;

    /**
     * Constructs a new VendingMachineUI and initializes the user interface.
     */
    public VendingMachineUI() {
        frame = new JFrame("Vending Machine Creation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new FlowLayout());

        statusMessage = new JLabel();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));

        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        firstRow.add(new Label("Vending Machine name: "));
        vendingNameInput = new JTextField(15);
        firstRow.add(vendingNameInput);
        mainPanel.add(firstRow);

        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] vendingTypes = { "Coffee Machine", "Snack Machine" };
        vendingTypeComboBox = new JComboBox<>(vendingTypes);
        secondRow.add(vendingTypeComboBox);

        JButton createVendingButton = new JButton("Create Selected Vending Machine");
        createVendingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createSelectedVendingMachine(vendingNameInput.getText());
            }
        });
        secondRow.add(createVendingButton);
        mainPanel.add(secondRow);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Creates the selected vending machine based on the specified vending machine name.
     *
     * @param vendingMachineName the name of the vending machine to be created
     */
    private void createSelectedVendingMachine(String vendingMachineName) {
        String selectedVendingType = (String) vendingTypeComboBox.getSelectedItem();
        VendingFactory vendingFactory = new VendingFactory();

        if (selectedVendingType.equalsIgnoreCase("coffee machine")) {
            frame.dispose();
            openCoffeeVendingMachinePanel(vendingFactory.createCoffeeMachine(vendingMachineName));
        }
        else {
            frame.dispose();
            openSnackVendingMachinePanel(vendingFactory.createSnackMachine(vendingMachineName));
        }
    }

    /**
     * Opens the user interface for a coffee vending machine.
     *
     * @param vend the coffee vending machine instance
     */
    private void openCoffeeVendingMachinePanel(CoffeeMachine vend) {
        coffeeMachineFrame = new JFrame("Coffee Machine");
        coffeeMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        coffeeMachineFrame.setSize(800, 700);
        coffeeMachineFrame.setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(2, 1));

        // ---------------------------- FIRST ROW ----------------------------
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelName = new JLabel("Vending machine name: " + vend.getName());
        JLabel labelWater = new JLabel("Water Level: " + vend.getWaterLevel());
        JLabel labelBeans = new JLabel("Coffee Beans Level: " + vend.getCoffeeBeansLevel());
        JLabel labelMilk = new JLabel("Milk Level: " + vend.getMilkLevel());
        JLabel labelBalance = new JLabel("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

        firstRow.add(labelName);
        firstRow.add(labelWater);
        firstRow.add(labelBeans);
        firstRow.add(labelMilk);
        firstRow.add(labelBalance);

        statusPanel.add(firstRow);

        // ---------------------------- SECOND ROW ----------------------------
        JPanel secondRow = new JPanel();
        secondRow.setLayout(new GridLayout(4, 1));

        JLabel labelIncreaseWater = new JLabel("Increase Water Level by:");
        JTextField waterAmountInput = new JTextField(5);
        JButton increaseWaterButton = new JButton("Increase");
        increaseWaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(waterAmountInput.getText());
                try {
                    vend.increaseWaterLevel(amount);
                    labelWater.setText("Water Level: " + vend.getWaterLevel());

                    // Display status message
                    displayStatusMessage("Water level updated", false);
                } catch (RuntimeException ex) {
                    displayStatusMessage("Cannot update water level", true);
                }
            }
        });

        JLabel labelIncreaseBeans = new JLabel("Increase Coffee Beans Level by:");
        JTextField beansAmountInput = new JTextField(5);
        JButton increaseBeansButton = new JButton("Increase");
        increaseBeansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(beansAmountInput.getText());
                try {
                    vend.increaseCoffeeBeansLevel(amount);
                    labelBeans.setText("Coffee Beans Level: " + vend.getCoffeeBeansLevel());

                    // Display status message
                    displayStatusMessage("Coffee beans level updated", false);
                } catch (Exception ex) {
                    displayStatusMessage("Cannot update coffee beans level", true);
                }
            }
        });

        JLabel labelIncreaseMilk = new JLabel("Increase Milk Level by:");
        JTextField milkAmountInput = new JTextField(5);
        JButton increaseMilkButton = new JButton("Increase");
        increaseMilkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt(milkAmountInput.getText());
                try {
                    vend.increaseMilkLevel(amount);
                    labelMilk.setText("Milk Level: " + vend.getMilkLevel());

                    // Display status message
                    displayStatusMessage("Milk level updated", false);
                } catch (Exception ex) {
                    displayStatusMessage("Cannot update milk level", true);
                }
            }
        });

        JLabel labelIncreaseBalance = new JLabel("Add Cents: ");
        JTextField balanceAmountInput = new JTextField(5);
        JButton increaseBalanceButton = new JButton("Add");
        increaseBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt((balanceAmountInput.getText()));

                try {
                    vend.insertMoney(amount);

                    labelBalance.setText("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

                    // Display status message
                    displayStatusMessage("Balance updated", false);
                } catch (RuntimeException | VendingException ex) {
                    displayStatusMessage("Cannot update balance", true);
                }
            }
        });

        secondRow.add(labelIncreaseWater);
        secondRow.add(waterAmountInput);
        secondRow.add(increaseWaterButton);

        secondRow.add(labelIncreaseBeans);
        secondRow.add(beansAmountInput);
        secondRow.add(increaseBeansButton);

        secondRow.add(labelIncreaseMilk);
        secondRow.add(milkAmountInput);
        secondRow.add(increaseMilkButton);

        secondRow.add(labelIncreaseBalance);
        secondRow.add(balanceAmountInput);
        secondRow.add(increaseBalanceButton);

        statusPanel.add(secondRow);

        // ---------------------------- PRODUCT PANEL ----------------------------
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        JLabel headingProducts = new JLabel("Current available products: ");
        productPanel.add(headingProducts, BorderLayout.NORTH);

        JPanel productInfoPanel = new JPanel(new GridLayout(0, 1));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));

        Runnable updateProductPanel = () -> {
            productInfoPanel.removeAll();

            List<Coffee> products = vend.getProducts();
            if (products.isEmpty()) {
                JLabel noProducts = new JLabel("No products to display");
                productInfoPanel.add(noProducts);
            }
            else {
                for (Coffee product : products) {
                    JPanel productDetailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                    JLabel productId = new JLabel("ID: " + product.getId());
                    JLabel productName = new JLabel("Name: " + product.getName());
                    JLabel productPrice = new JLabel("Price: " + product.getPrice());
                    JLabel productBeans = new JLabel("Required beans: " + product.getRequiredBeans());
                    JLabel productWater = new JLabel("Required water: " + product.getRequiredWater());
                    JLabel productMilk = new JLabel("Required milk: " + product.getRequiredMilk());

                    JButton brewCoffeeButton = new JButton("Brew");

                    brewCoffeeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                vend.brewCoffee(product.getId());

                                // Update vending machine status
                                labelWater.setText("Water Level: " + vend.getWaterLevel());
                                labelBeans.setText("Coffee Beans Level: " + vend.getCoffeeBeansLevel());
                                labelMilk.setText("Milk Level: " + vend.getMilkLevel());
                                labelBalance.setText("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

                                // Display status message
                                displayStatusMessage("Brewed " + product.getName(), false);
                            } catch (VendingException ex) {
                                displayStatusMessage("Cannot brew selected coffee", true);
                            }
                        }
                    });

                    productDetailsPanel.add(productId);
                    productDetailsPanel.add(productName);
                    productDetailsPanel.add(productPrice);
                    productDetailsPanel.add(productBeans);
                    productDetailsPanel.add(productWater);
                    productDetailsPanel.add(productMilk);
                    productDetailsPanel.add(brewCoffeeButton);

                    productInfoPanel.add(productDetailsPanel);
                }
            }

            productInfoPanel.revalidate();
            productInfoPanel.repaint();
        };

        updateProductPanel.run();

        JButton addCoffeeButton = new JButton("Add Product");
        addCoffeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCoffeeFrame addCoffeeFrame = new AddCoffeeFrame(vend);

                addCoffeeFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        updateProductPanel.run();
                    }
                });
            }
        });

        JButton importButton = new JButton("Import Products");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread importThread = new Thread(new ImportCoffeeRunnable("C:\\Users\\ADMIN\\Desktop\\VendingMachine\\resources\\products.bin", vend));
                importThread.start();

                try {
                    importThread.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                SwingUtilities.invokeLater(updateProductPanel);

                // Display status message
                displayStatusMessage("Products successfully imported", false);
            }
        });

        JButton exportButton = new JButton("Export Products");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread exportThread = new Thread(new ExportCoffeeRunnable("C:\\Users\\ADMIN\\Desktop\\VendingMachine\\resources\\products.bin", vend));
                exportThread.start();

                // Display status message
                displayStatusMessage("Products successfully exported", false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addCoffeeButton);
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);

        buttonPanel.add(statusMessage);

        productPanel.add(new JScrollPane(productInfoPanel), BorderLayout.CENTER);
        productPanel.add(buttonPanel, BorderLayout.SOUTH);

        coffeeMachineFrame.add(statusPanel, BorderLayout.NORTH);
        coffeeMachineFrame.add(productPanel, BorderLayout.CENTER);

        coffeeMachineFrame.setLocationRelativeTo(null);
        coffeeMachineFrame.setVisible(true);
    }

    /**
     * Opens the user interface for a snack vending machine.
     *
     * @param vend the snack vending machine instance
     */
    private void openSnackVendingMachinePanel(SnackMachine vend) {
        snackMachineFrame = new JFrame("Snack Machine");
        snackMachineFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snackMachineFrame.setSize(800, 700);
        snackMachineFrame.setLayout(new BorderLayout());

        JPanel statusPanel = new JPanel();
//        statusPanel.setLayout(new GridLayout(2, 1));

        // ---------------------------- FIRST ROW ----------------------------
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelName = new JLabel("Vending machine name: " + vend.getName());
        JLabel labelBalance = new JLabel("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

        firstRow.add(labelName);
        firstRow.add(labelBalance);

        statusPanel.add(firstRow);

        // ---------------------------- SECOND ROW ----------------------------
        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        secondRow.setLayout(new GridLayout(4, 1));

        JLabel labelIncreaseBalance = new JLabel("Add Cents: ");
        JTextField balanceAmountInput = new JTextField(5);
        JButton increaseBalanceButton = new JButton("Add");
        increaseBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int amount = Integer.parseInt((balanceAmountInput.getText()));

                try {
                    vend.insertMoney(amount);

                    labelBalance.setText("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

                    // Display status message
                    displayStatusMessage("Balance updated", false);
                } catch (RuntimeException | VendingException ex) {
                    displayStatusMessage("Cannot update balance", true);
                }
            }
        });

        secondRow.add(labelIncreaseBalance);
        secondRow.add(balanceAmountInput);
        secondRow.add(increaseBalanceButton);

        statusPanel.add(secondRow);

        // ---------------------------- PRODUCT PANEL ----------------------------
        JPanel productPanel = new JPanel(new BorderLayout());

        JLabel headingProducts = new JLabel("Current available products: ");
        productPanel.add(headingProducts, BorderLayout.NORTH);

        JPanel productInfoPanel = new JPanel(new GridLayout(0, 1));
        productInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));

        Runnable updateProductPanel = () -> {
            productInfoPanel.removeAll();

            List<Snack> products = vend.getProducts();
            if (products.isEmpty()) {
                JLabel noProducts = new JLabel("No products to display");
                productInfoPanel.add(noProducts);
            }
            else {
                for (Snack product : products) {
                    JPanel productDetailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                    JLabel productId = new JLabel("ID: " + product.getId());
                    JLabel productName = new JLabel("Name: " + product.getName());
                    JLabel productPrice = new JLabel("Price: " + product.getPrice());

                    JButton buySnackButton = new JButton("Buy");

                    buySnackButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                vend.buySnack(product.getId());

                                // Update vending machine status
                                labelBalance.setText("Balance: " + vend.getEuro() + " Eur. " + vend.getCents() + " Cents.");

                                // Display status message
                                displayStatusMessage("Bought " + product.getName(), false);
                            } catch (VendingException ex) {
                                displayStatusMessage("Cannot buy selected snack", true);
                            }
                        }
                    });

                    productDetailsPanel.add(productId);
                    productDetailsPanel.add(productName);
                    productDetailsPanel.add(productPrice);
                    productDetailsPanel.add(buySnackButton);

                    productInfoPanel.add(productDetailsPanel);
                }
            }

            productInfoPanel.revalidate();
            productInfoPanel.repaint();
        };

        updateProductPanel.run();

        JButton addSnackButton = new JButton("Add Product");
        addSnackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSnackFrame addCoffeeFrame = new AddSnackFrame(vend);

                addCoffeeFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        updateProductPanel.run();
                    }
                });
            }
        });

        JButton importButton = new JButton("Import Products");
        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread importThread = new Thread(new ImportSnackRunnable("C:\\Users\\ADMIN\\Desktop\\VendingMachine\\resources\\products.bin", vend));
                importThread.start();

                try {
                    importThread.join();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

                SwingUtilities.invokeLater(updateProductPanel);

                // Display status message
                displayStatusMessage("Products successfully imported", false);
            }
        });

        JButton exportButton = new JButton("Export Products");
        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread exportThread = new Thread(new ExportSnackRunnable("C:\\Users\\ADMIN\\Desktop\\VendingMachine\\resources\\products.bin", vend));
                exportThread.start();

                // Display status message
                displayStatusMessage("Products successfully exported", false);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(addSnackButton);
        buttonPanel.add(importButton);
        buttonPanel.add(exportButton);

        buttonPanel.add(statusMessage);

        productPanel.add(new JScrollPane(productInfoPanel), BorderLayout.CENTER);
        productPanel.add(buttonPanel, BorderLayout.SOUTH);

        snackMachineFrame.add(statusPanel, BorderLayout.NORTH);
        snackMachineFrame.add(productPanel, BorderLayout.CENTER);

        snackMachineFrame.setLocationRelativeTo(null);
        snackMachineFrame.setVisible(true);
    }

    /**
     * Displays a status message on the UI.
     *
     * @param message the message to be displayed
     * @param isError {@code true} if the message represents an error, {@code false} otherwise
     */
    private void displayStatusMessage(String message, Boolean isError) {
        statusMessage.setText(message);

        statusMessage.setForeground(isError ? new Color(233, 29, 29) : new Color(146, 197, 109));

        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                statusMessage.setText("");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
