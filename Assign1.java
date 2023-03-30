/**
 * CET - CS Academic Level 3
 * This class contains the menu and main method to start the program
 * Student Name: Yun Wang
 * Student Number: 041069121
 * Course: CST8130 - Data Structures
 *
 * @author/Yun Wang, taught by Professor: James Mwangi PhD.
 */

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The `Assign1` class implements a basic inventory management system that allows the user to add, display,
 * buy, and sell items.
 */
public class Assign1 {
    /**
     * The main method of the program, where the user is prompted to select from a menu of actions to perform.
     *
     * @param args Command line arguments (not used in this program).
     */
    public static void main(String[] args) {

        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Create an Inventory object to manage the items
        Inventory inventory = new Inventory();

        // Initialize the user's choice to 0
        int choice = 0;

        // Repeat the menu prompt and action selection until the user chooses to exit
        while (choice != 8) {
            displayMenu();

            // Read the user's choice as an integer
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                // If the user's input cannot be parsed as an integer, clear the input and continue
                scanner.nextLine();
            }

            // Perform the selected action based on the user's choice
            switch (choice) {
                case 1:
                    // Add an item to the inventory
                    inventory.addItem(scanner);
                    break;
                case 2:
                    // Bubble sort implementation to sort the inventory by itemCode in ascending order
                    inventory.bubbleSort();
                    // Print out the sorted inventory
                    System.out.println("Inventory:");
                    for (int i = 0; i < inventory.getNumItems(); i++) {
                        System.out.println(inventory.getInventory().get(i));
                    }
                    break;
                case 3:
                    // Buy an item (increase its quantity)
                    if (inventory.getInventory().size() == 0) {
                        System.out.println("Error...could not buy item");
                    } else {
                        inventory.updateQuantity(scanner, true);
                    }
                    break;
                case 4:
                    // Sell an item (decrease its quantity)
                    if (inventory.getInventory().size() == 0) {
                        System.out.println("Error...could not sell item");
                    } else {
                        inventory.updateQuantity(scanner, false);
                    }
                    break;
                case 5:
                    // Search for an item in the inventory
                    inventory.searchForItem(scanner);
                    break;
                case 6:
                    // Save current inventory to a file
                    inventory.saveToFile(scanner);
                    break;
                case 7:
                    // Read from a file
                    inventory.readFromFile(scanner);
                    break;
                case 8:
                    // Exit the program
                    System.out.println("Exiting...");
                    break;
                default:
                    // If the user's choice is not valid, display an error message
                    System.out.println("...Invalid input, please try again...");
            }
        }
        // Close the Scanner object when done
        scanner.close();
    }

    /**
     * Displays a menu of options to the user.
     */
    public static void displayMenu() {
        System.out.println();
        System.out.println("Please select one of the following:");
        System.out.println("1: Add Item to Inventory");
        System.out.println("2: Display Current Inventory");
        System.out.println("3: Buy Item(s)");
        System.out.println("4: Sell Item(s)");
        System.out.println("5: Search for Item");
        System.out.println("6: Save Inventory to File");
        System.out.println("7: Read Inventory from File");
        System.out.println("8: To Exit");
        System.out.print(">");

    }
}
