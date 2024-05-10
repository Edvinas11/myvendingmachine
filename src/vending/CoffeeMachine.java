package vending;
import products.Coffee;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * The {@code CoffeeMachine} class represents a Coffee Machine, extending an abstract Vending Machine.
 * It manages the water level, coffee beans level, and milk level for brewing coffee,
 * as well as the list of available coffee products.
 */
public class CoffeeMachine extends AbstractVendingMachine implements Cloneable{
    /**
     * The current water level in the coffee machine
     */
    private int waterLevel;

    /**
     * The current beans level in the coffee machine
     */
    private int coffeeBeansLevel;

    /**
     * The current milk level in the coffee machine
     */
    private int milkLevel;

    /**
     * The list of available coffees in the coffee machine
     */
    private List<Coffee> products;

    /**
     * The max capacity of water level in the coffee machine
     */
    private final int MAX_WATER_CAPACITY = 1000;

    /**
     * The max capacity of beans level in the coffee machine
     */
    private final int MAX_COFFEE_BEANS_CAPACITY = 1000;

    /**
     * The max capacity of milk level in the coffee machine
     */
    private final int MAX_MILK_CAPACITY = 1000;

    /**
     * The max capacity of coffees in the coffee machine
     */
    private final int MAX_COFFEE_CAPACITY = 10;

    /**
     * The max capacity of client's balance in the coffee machine
     */
    private final int MAX_BALANCE_CAPACITY = 30;

    /**
     * Constructor for Coffee Machine
     *
     * @param name name of the vending machine
     */
    public CoffeeMachine(String name) {
        super(name);
        waterLevel = 0;
        coffeeBeansLevel = 0;
        milkLevel = 0;
        products = new LinkedList<>();
    }

    // Getters
    /**
     * Get the current water level in the coffee machine.
     * @return The current water level.
     */
    public int getWaterLevel(){
        return waterLevel;
    }

    /**
     * Get the current coffee beans level in the coffee machine.
     * @return The current coffee beans level.
     */
    public int getCoffeeBeansLevel(){
        return coffeeBeansLevel;
    }

    /**
     * Get the current milk level in the coffee machine.
     * @return The current milk level.
     */
    public int getMilkLevel(){
        return milkLevel;
    }

    /**
     * Get the list of available coffee products in the coffee machine.
     * @return The list of available coffee products.
     */
    public List<products.Coffee> getProducts(){
        return products;
    }

    // Setters
    /**
     * Set the water level in the coffee machine.
     * @param waterAmount The amount of water to set.
     * @throws VendingException if the water amount is invalid.
     */
    public void setWaterLevel(int waterAmount) throws VendingException {
        if(waterAmount >= 0 && waterAmount <= MAX_WATER_CAPACITY) {
            this.waterLevel = waterAmount;
        } else {
            throw new IllegalArgumentException("Invalid water level. Water level must be between 0 and " + MAX_WATER_CAPACITY);
        }
    }

    /**
     * Set the coffee beans level in the coffee machine.
     * @param beansAmount The amount of coffee beans to set.
     * @throws VendingException if the coffee beans amount is invalid.
     */
    public void setCoffeeBeansLevel(int beansAmount) throws VendingException{
        if(beansAmount >= 0 && beansAmount <= MAX_COFFEE_BEANS_CAPACITY){
            this.coffeeBeansLevel = beansAmount;
        } else {
            throw new IllegalArgumentException("Invalid coffee beans level. Coffee beans level must be between 0 and " + MAX_COFFEE_BEANS_CAPACITY);
        }
    }

    /**
     * Set the milk level in the coffee machine.
     * @param milkAmount The amount of milk to set.
     * @throws VendingException if the milk amount is invalid.
     */
    public void setMilkLevel(int milkAmount) throws VendingException{
        if (milkAmount >= 0 && milkAmount <= MAX_MILK_CAPACITY) {
            this.milkLevel = milkAmount;
        } else {
            throw new IllegalArgumentException("Invalid milk level. Milk level must be between 0 and " + MAX_MILK_CAPACITY);
        }
    }

    /**
     * Set the list of available coffee products in the coffee machine.
     * @param products The list of coffee products to set.
     */
    public void setProducts(List<Coffee> products){
        this.products = products;
    }

    // Additional methods
    /**
     * Increase the water level in the coffee machine.
     * @param amount The amount of water to increase.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public void increaseWaterLevel(int amount) {
        if(amount >= 0 && amount <= MAX_WATER_CAPACITY && this.waterLevel < MAX_WATER_CAPACITY) {
            this.waterLevel += amount;
        } else {
            throw new IllegalArgumentException("Invalid water level. Water level must be between 0 and " + MAX_WATER_CAPACITY);
        }
    }

    /**
     * Increase the coffee beans level in the coffee machine.
     * @param amount The amount of coffee beans to increase.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public void increaseCoffeeBeansLevel(int amount) {
        if(amount >= 0 && amount <= MAX_COFFEE_BEANS_CAPACITY && this.coffeeBeansLevel < MAX_COFFEE_BEANS_CAPACITY) {
            this.coffeeBeansLevel += amount;
        } else {
            throw new IllegalArgumentException("Invalid coffee beans level. Coffee beans level must be between 0 and " + MAX_COFFEE_BEANS_CAPACITY);
        }
    }

    /**
     * Increase the milk level in the coffee machine.
     * @param amount The amount of milk to increase.
     * @throws IllegalArgumentException if the amount is invalid.
     */
    public void increaseMilkLevel(int amount) {
        if(amount >= 0 && amount <= MAX_MILK_CAPACITY && this.milkLevel < MAX_MILK_CAPACITY) {
            this.milkLevel += amount;
        } else {
            throw new IllegalArgumentException("Invalid milk level. Milk level must be between 0 and " + MAX_MILK_CAPACITY);
        }
    }

    @Override
    public Object clone(){
        try {
            CoffeeMachine clonedMachine = (CoffeeMachine) super.clone();

            List<Coffee> clonedProducts = new LinkedList<>();
            for(Coffee coffee : this.products) {
                clonedProducts.add(coffee.clone());
            }
            clonedMachine.setProducts(clonedProducts);

            return clonedMachine;
        } catch (CloneNotSupportedException exc){
            throw new Error ("Cloning not supported");
        }
    }

    /**
     * Add a new coffee product to the coffee machine.
     * @param coffee The coffee product to add.
     * @throws VendingException if the maximum capacity of coffee products is exceeded.
     */
    public void addCoffee(Coffee coffee) throws VendingException{
        if(products.size() < MAX_COFFEE_CAPACITY){
            products.add(coffee);
        } else {
            throw new ExceededCapacityException("Cannot add more coffee products. Maximum capacity reached.");
        }
    }
    @Override
    public void insertMoney(int cents) throws VendingException{
        int balance = super.getEuro() * 100 + super.getCents() + cents;

        if(balance > MAX_BALANCE_CAPACITY * 100){
            throw new ExceededCapacityException("Maximum capacity exceeded. Cannot insert money.");
        }

        super.insertMoney(cents);
    }

    /**
     * Brew coffee using the provided coffee ID.
     * @param id The ID of the coffee product to brew.
     * @throws VendingException if the provided ID is invalid, there is insufficient balance,
     * or there are insufficient resources to brew the coffee.
     */
    public void brewCoffee(int id) throws VendingException{
        // Check if provided id is correct.
        if(!(id > 0 && id <= products.size())) {
            throw new VendingException("Invalid coffee ID.");
        }

        Coffee coffeeToBrew = null;
        try {
            coffeeToBrew = products.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid coffee ID.");
        }

        // Check if user has enough money
        int coffeePrice = coffeeToBrew.getPrice();
        if((super.getEuro() * 100 + super.getCents()) < coffeePrice){
            throw new InsufficientBalanceException("Insufficient funds. Please insert more money.");
        }

        // Check for insufficient resources
        if(waterLevel < coffeeToBrew.getRequiredWater() ||
                coffeeBeansLevel < coffeeToBrew.getRequiredBeans() ||
                milkLevel < coffeeToBrew.getRequiredMilk()) {
            throw new InsufficientResourceException("Insufficient resources to brew this coffee.", coffeeToBrew.getRequiredWater());
        }

        // Deduct price
        deductBalance(coffeeToBrew.getPrice());

        // Brew coffee
        waterLevel -= coffeeToBrew.getRequiredWater();
        coffeeBeansLevel -= coffeeToBrew.getRequiredBeans();
        milkLevel -= coffeeToBrew.getRequiredMilk();
    }

    @Override
    public String toString(){
        return "CoffeeMachine {" +
                "name: " + super.getName() +
                ", euro: " + super.getEuro() +
                ", cents: " + super.getCents() +
                ", waterLevel: " + waterLevel +
                ", coffeeBeansLevel: " + coffeeBeansLevel +
                ", milkLevel: " + milkLevel +
                ", coffees: " + products +
                "}";
    }
}