import java.util.Scanner;

/**
 * The Meat class extends the FoodItem class and represents a type of food item that is meat.
 * It has a private instance variable "slaughterHouse" that holds the name of the slaughterhouse
 * where the meat was processed.
 */
public class Meat extends FoodItem {

    /**
     * Store the integer value of the jar size.
     */
    private String slaughterHouse;

    /**
     * Default constructor for the Meat class that calls the default constructor of the parent class
     * and sets the value of the instance variable "slaughterHouse".
     */
    public Meat() {
        super();
        this.slaughterHouse = slaughterHouse;
    }

    /**
     * Returns the name of the slaughterhouse that the Meat was obtained from.
     *
     * @return the name of the slaughterhouse as a string
     */
    public String getSlaughterHouse() {
        return slaughterHouse;
    }

    /**
     * Sets the slaughterhouse of this item.
     *
     * @param slaughterHouse the new item slaughterhouse
     */
    public void setSlaughterHouse(String slaughterHouse) {
        this.slaughterHouse = slaughterHouse;
    }

    /**
     * Overrides the addItem method from the parent class to add the slaughterhouse information to the food item.
     *
     * @param scanner Scanner object to take input from the user.
     * @return boolean value indicating success or failure of adding the item.
     */
    @Override
    public boolean addItem(Scanner scanner) {
        super.addItem(scanner);

        System.out.print("Enter the name of the slaughterHouse: ");
        Scanner sc = new Scanner(System.in);
        this.slaughterHouse = sc.nextLine();
        return true;
    }

    /**
     * Overrides the toString method to return a string representation of the Meat object that
     * includes the slaughterhouse information.
     *
     * @return a string representation of the Meat object.
     */
    @Override
    public String toString() {
        return super.toString() + " | Slaughter House: " + slaughterHouse;
    }
}

