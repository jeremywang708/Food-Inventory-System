/**
 * CET - CS Academic Level 3
 * <p>
 * The Preserve class extends the FoodItem class and represents a specific type of food item
 * that is a preserve. It has an additional field, "jarSize", to store the size of the jar
 * in milliliters.
 * <p>
 * Student Name: Yun Wang
 * Student Number: 041069121
 * Course: CST8130 - Data Structures
 *
 * @author/Yun Wang, taught by Professor: James Mwangi PhD.
 */

import java.util.Scanner;

/**
 * The Preserve class extends the FoodItem class and represents a specific type of food item
 * that is a preserve. It has an additional field, "jarSize", to store the size of the jar
 * in milliliters.
 */
public class Preserve extends FoodItem {

    /**
     * Store the integer value of the jar size.
     */
    private int jarSize;

    /**
     * Default constructor for Preserve class
     */
    public Preserve() {
        super();
        this.jarSize = jarSize;
    }

    /**
     * Returns the size of the jar that the Preserve was obtained from.
     *
     * @return the size of the jar as an integer
     */
    public int getJarSize() {
        return jarSize;
    }

    /**
     * Sets the jar size of this item.
     *
     * @param jarSize the new item jar size
     */
    public void setJarSize(int jarSize) {
        this.jarSize = jarSize;
    }

    /**
     * Adds the preserve item information by calling the parent class' addItems method and
     * then prompts the user to enter the jar size. The input is validated to ensure it is
     * a positive integer.
     *
     * @param scanner a Scanner object used to read user input
     * @return true if the item was successfully added, false otherwise
     */
    @Override
    public boolean addItem(Scanner scanner) {
        super.addItem(scanner);
        boolean jarEntered = false;
        while (!jarEntered) {
            try {
                System.out.print("Enter the size of the jar in millilitres: ");
                this.jarSize = scanner.nextInt();
                if (jarSize < 0) {
                    System.out.println("Invalid input. Quantity must be a positive number.");
                } else {
                    jarEntered = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.nextLine();
            }
        }

        return true;
    }

    /**
     * Returns a string representation of the Preserve object including the parent class'
     * information and the jar size.
     *
     * @return the string representation of the Preserve object
     */
    @Override
    public String toString() {
        return super.toString() + " | Size: " + jarSize + "ml";
    }
}

