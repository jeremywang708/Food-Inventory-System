/**
 * CET - CS Academic Level 3
 * <p>
 * The Fruit class extends the FoodItem class and represents a specific type of food item
 * that is a fruit. It has an additional field, "orchardName", to store the name of the
 * orchard supplier.
 * <p>
 * Student Name: Yun Wang
 * Student Number: 041069121
 * Course: CST8130 - Data Structures
 *
 * @author/Yun Wang, taught by Professor: James Mwangi PhD.
 */

import java.util.Scanner;

/**
 * The Fruit class extends the FoodItem class and represents a specific type of food item
 * that is a fruit. It has an additional field, "orchardName", to store the name of the
 * orchard supplier.
 */
public class Fruit extends FoodItem {

    /**
     * Store the string value of orchard name.
     */
    private String orchardName;

    /**
     * Default constructor for Fruit class
     */
    public Fruit() {
        super();
        this.orchardName = orchardName;
    }

    /**
     * Returns the name of the orchard that the Fruit was obtained from.
     *
     * @return the name of the orchard as a string
     */
    public String getOrchardName() {
        return orchardName;
    }

    /**
     * Sets the orchard name of this item.
     *
     * @param orchardName the new item orchard name
     */
    public void setOrchardName(String orchardName) {
        this.orchardName = orchardName;
    }

    /**
     * Adds the fruit item information by calling the parent class' addItems method and
     * then prompts the user to enter the orchard name.
     *
     * @param scanner a Scanner object used to read user input
     * @return true if the item was successfully added, false otherwise
     */
    @Override
    public boolean addItem(Scanner scanner) {
        super.addItem(scanner);

        System.out.print("Enter the name of the orchard supplier: ");
        Scanner sc = new Scanner(System.in);
        this.orchardName = sc.nextLine();
        return true;
    }

    /**
     * Returns a string representation of the Fruit object including the parent class'
     * information and the orchard name.
     *
     * @return the string representation of the Fruit object
     */
    @Override
    public String toString() {
        return super.toString() + " | Orchard: " + orchardName;
    }
}

