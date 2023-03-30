import java.util.Scanner;

/**
 * The Vegetable class extends the FoodItem class and represents a specific type of food item
 * that is a vegetable. It has an additional field, "farmName", to store the name of the
 * farm supplier.
 */
public class Vegetable extends FoodItem {

    /**
     * Store the string value of farm name.
     */
    private String farmName;

    /**
     * Default constructor for Vegetable class
     */
    public Vegetable() {
        super();
        this.farmName = farmName;
    }

    /**
     * Returns the name of the farm that the Vegetable was obtained from.
     *
     * @return the name of the farm as a string
     */
    public String getFarmName() {
        return farmName;
    }

    /**
     * Sets the farm name of this item.
     *
     * @param farmName the new item farm name
     */
    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    /**
     * Adds the vegetable item information by calling the parent class' addItems method and
     * then prompts the user to enter the farm name.
     *
     * @param scanner a Scanner object used to read user input
     * @return true if the item was successfully added, false otherwise
     */
    @Override
    public boolean addItem(Scanner scanner) {
        super.addItem(scanner);

        System.out.print("Enter the name of the farm supplier: ");
        Scanner sc = new Scanner(System.in);
        this.farmName = sc.nextLine();

        return true;
    }

    /**
     * Returns a string representation of the Vegetable object including the parent class'
     * information and the farm name.
     *
     * @return the string representation of the Vegetable object
     */
    @Override
    public String toString() {
        return super.toString() + " | Farm: " + farmName;
    }
}

