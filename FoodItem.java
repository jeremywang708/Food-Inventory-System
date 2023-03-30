/**
 * CET - CS Academic Level 3
 * This class acts as a parent class to represent a food item
 * Student Name: Yun Wang
 * Student Number: 041069121
 * Course: CST8130 - Data Structures
 *
 * @author/Yun Wang, taught by Professor: James Mwangi PhD.
 */

import java.util.Scanner;

/**
 * This class represents a food item in the inventory. It contains information about the item's code,
 * name, quantity in stock, cost, and price.
 */
public class FoodItem implements Comparable <FoodItem> {
    /**
     * The code of the food item
     */
    protected int itemCode;
    /**
     * The name of the food item
     */
    protected String itemName;
    /**
     * The price of the food item
     */
    protected float itemPrice;
    /**
     * The quantity in stock of the food item
     */
    protected int itemQuantityInStock;
    /**
     * The cost of the food item
     */
    protected float itemCost;

    /**
     * Default constructor for the FoodItem class.
     */
    public FoodItem() {
    }

    /**
     * Returns the item code of this FoodItem.
     * @return the item code of this FoodItem
     */
    public int getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code for this item.
     * @param itemCode the new item code
     */
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    /**
     * Sets the item name for this item.
     * @param itemName the new item name
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Sets the item price for this item.
     * @param itemPrice the new item price
     */
    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * Sets the quantity of this item in stock.
     * @param itemQuantityInStock the new quantity in stock
     */
    public void setItemQuantityInStock(int itemQuantityInStock) {
        this.itemQuantityInStock = itemQuantityInStock;
    }

    /**
     * Sets the cost of this item.
     * @param itemCost the new item cost
     */
    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    /**
     * This method adds an item to the inventory by prompting the user to enter the name,
     * quantity in stock, cost, and sales price of the item. The input is read from the Scanner object
     * passed as an argument to the method. If the user enters an invalid value, an error message is displayed
     * and the prompt is repeated until valid input is entered.
     *
     * @param scanner Scanner object to read user input
     * @return true if item was successfully added to the inventory, false otherwise
     */
    public boolean addItem(Scanner scanner) {
        // Read the name of the item from user input
        System.out.print("Enter the name for the item: ");
        Scanner sc = new Scanner(System.in);
        itemName = sc.nextLine();

        // Read the quantity in stock for the item from user input
        boolean quantityEntered = false;
        while (!quantityEntered) {
            try {
                System.out.print("Enter the quantity for the item: ");
                itemQuantityInStock = scanner.nextInt();
                if (itemQuantityInStock < 0) {
                    System.out.println("Invalid input. Quantity must be a positive number.");
                } else {
                    quantityEntered = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.nextLine();
            }
        }

        // Read the cost of the item from user input
        boolean costEntered = false;
        while (!costEntered) {
            try {
                System.out.print("Enter the cost of the item: ");
                itemCost = scanner.nextFloat();
                if (itemCost < 0) {
                    System.out.println("Invalid input. Cost must be a positive number.");
                } else {
                    costEntered = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a float value.");
                scanner.nextLine();
            }
        }

        // Read the sales price of the item from user input
        boolean salesPriceEntered = false;
        while (!salesPriceEntered) {
            try {
                System.out.print("Enter the sales price of the item: ");
                itemPrice = scanner.nextFloat();
                if (itemPrice < 0) {
                    System.out.println("Invalid input. Price must be a positive number.");
                } else {
                    salesPriceEntered = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a float value.");
                scanner.nextLine();
            }
        }

        return true;
    }

    /**
     * Updates the quantity field of a FoodItem object by a specified amount.
     *
     * @param amount the amount to change the item quantity (can be positive or negative)
     * @return true if the update was successful, false otherwise
     * <p>
     * Note: the itemQuantityInStock field will never be less than 0.
     */
    public boolean updateItem(int amount) {
        itemQuantityInStock += amount;
        if (itemQuantityInStock < 0) {
            itemQuantityInStock -= amount; // set quantity to be its original number once it is smaller than 0
            System.out.println("Error...You cannot sell " + (-amount) + " " +
                    itemName + "(s) because you currently only have " +
                    itemQuantityInStock + " " + itemName + "(s) in stock.");
            return false;
        }
        return true;
    }

    /**
     * Determines if two FoodItem objects have the same item code.
     *
     * @param item the FoodItem object to compare with the current object
     * @return true if both objects have the same item code, false otherwise
     */
    public boolean isEqual(FoodItem item) {
        return this.itemCode == item.itemCode;
    }

    /**
     * Method to input code for an item.
     *
     * @param scanner the Scanner object to read user input
     * @return true if a valid code was successfully entered, false otherwise
     */
    public boolean inputCode(Scanner scanner) {
        boolean codeEntered = false;
        while (!codeEntered) {
            try {
                System.out.print("Enter the code for the item: ");
                itemCode = scanner.nextInt(); // read the code from user input
                codeEntered = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.nextLine(); // discard the invalid input
            }
        }
        return true;
    }

    /**
     * Compares this food item to another food item by their itemCode. Returns a negative integer if
     * this item is less than the other item, zero if they are equal, or a positive integer if this
     * item is greater than the other item.
     *
     * @param other the other food item to compare to
     * @return a negative integer if this item is less than the other item, zero if they are equal,
     *         or a positive integer if this item is greater than the other item
     */
    @Override
    public int compareTo(FoodItem other) {
        return Integer.compare(this.getItemCode(), other.getItemCode());
    }

    /**
     * This method returns a string representation of the InventoryItem object
     *
     * @return a formatted string that includes item code, name, quantity, sales price, and cost.
     */
    @Override
    public String toString() {
        return "Item code: " + itemCode + " | Name: " + itemName + " | Quantity: " + itemQuantityInStock +
                " | Sales price: $" + String.format("%.2f", itemPrice) + " | Cost: $" + String.format("%.2f", itemCost);
    }
}
