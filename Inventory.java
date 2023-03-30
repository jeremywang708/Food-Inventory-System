import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The `Inventory` class represents a collection of food items.
 * It keeps track of an array of `FoodItem` objects and the number of items in the inventory.
 */
public class Inventory {
    /**
     * store objects in the inventory ArrayList with class type FoodItem
     */
    private ArrayList<FoodItem> inventory;

    /**
     * store the number of items in the inventory array
     */
    private int numItems;

    /**
     * Constructs a new `Inventory` object with an empty ArrayList of `FoodItem` objects with a length of 20
     * and sets the number of items to 0.
     */
    public Inventory() {
        this.inventory = new ArrayList<>(20);
        this.numItems = 0;
    }

    /**
     * Returns the ArrayList of food items in the inventory.
     *
     * @return The ArrayList of food items in the inventory.
     */
    public ArrayList<FoodItem> getInventory() {
        return inventory;
    }

    /**
     * Returns the number of items in the inventory.
     *
     * @return The number of items in the inventory.
     */
    public int getNumItems() {
        return numItems;
    }

    /**
     * Adds an item to the inventory based on the user input.
     *
     * @param scanner the Scanner object used for user input
     * @return true if item is successfully added to the inventory, false otherwise
     */
    public boolean addItem(Scanner scanner) {
        String choice;
        FoodItem item;

        while (true) {
            System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or meat(m)? ");
            choice = scanner.next();

            switch (choice.toLowerCase()) {
                case "f":
                    item = new Fruit();
                    break;
                case "v":
                    item = new Vegetable();
                    break;
                case "p":
                    item = new Preserve();
                    break;
                case "m":
                    item = new Meat();
                    break;
                default:
                    System.out.println("Invalid input, please try again");
                    System.out.println("--------------------------------");
                    continue;
            }
            // Call the method to input a code
            item.inputCode(scanner);
            // Then check if this code already exists in the inventory array.
            // If it does, stop adding the item and loop back to main menu
            if (alreadyExists(item) != -1) {
                return false;
            }
            // If the code is not existent, then call the method to initialize the item with values
            item.addItem(scanner);
            // Put the item into the inventory array and increase the number of items one by one.
            // Handle exception when the array is full.
            try {
                inventory.add(item);
                numItems++;
                return true;
            } catch (InputMismatchException e) {
                System.out.println("The inventory is full!");
                return false;
            }
        }
    }

    /**
     * A method to check if the item code of a FoodItem is already present in the inventory.
     *
     * @param item - the FoodItem to check
     * @return - returns the index of the FoodItem if it already exists in the inventory, returns -1 otherwise.
     */
    public int alreadyExists(FoodItem item) {
        if (inventory.isEmpty()) {
            return -1;
        }

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).itemCode == item.itemCode) {
                System.out.println("...Item code already exists...");
                return i;
            }
        }
        return -1;
    }

    /**
     * This method updates the quantity of an item in the inventory.
     *
     * @param scanner   a Scanner object to get user input
     * @param buyOrSell a boolean value indicating whether to buy (true) or sell (false) an item
     * @return true if the item quantity was updated successfully, false otherwise
     */
    public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
        System.out.print("Enter the code for the item: ");
        int itemCode;

        // Use scanner's nextInt method instead of validating input with a loop
        if (!scanner.hasNextInt()) {
            System.out.println("Invalid item code...Error...could not update quantity");
            scanner.next();
            return false;
        }
        itemCode = scanner.nextInt();

        // Search the inventory for a matching item code
        for (FoodItem item : inventory) {
            if (item.itemCode == itemCode) {
                System.out.print("Enter valid quantity to " + (buyOrSell ? "buy:" : "sell:"));
                int quantityToChange;

                // Validate that the quantity to buy/sell is a positive integer
                if (!scanner.hasNextInt() || (quantityToChange = scanner.nextInt()) <= 0) {
                    System.out.println("Invalid quantity...Error...could not " + (buyOrSell ? "buy" : "sell") + " item");
                    scanner.nextLine();
                    return false;
                }

                // Update the item quantity
                item.updateItem(buyOrSell ? quantityToChange : -quantityToChange);
                return true;
            }
        }

        // If the item code is not found in the inventory, print an error message
        System.out.println("Item code not found in inventory...Error...could not " + (buyOrSell ? "buy" : "sell") + " item");
        return false;
    }

    /**
     * Searches for a food item in the inventory by item code and displays its details.
     * If the item is not found, prints an error message.
     *
     * @param scanner a Scanner object for user input
     */
    public void searchForItem(Scanner scanner) {
        System.out.print("Enter the code for the item: ");

        // Try to parse the input item code as an integer, and handle input mismatch exceptions
        int itemCode;
        try {
            itemCode = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please enter an integer code.");
            scanner.nextLine(); // clear the input buffer
            return;
        }

        // Search for the item with the given item code
        for (FoodItem item : inventory) {
            if (item.getItemCode() == itemCode) {
                // Display the details of the matching item
                System.out.println(item);
                return;
            }
        }

        // If no matching item is found, print an error message
        System.out.println("Code not found in inventory...");
    }


    /**
     * Saves the inventory to a file in the specified format
     *
     * @param scanner the Scanner object used to read user input
     */
    public void saveToFile(Scanner scanner) {
        System.out.print("Enter the filename to save to: ");
        String filename = scanner.next(); // get filename from user input

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) { // create a PrintWriter to write to the file
            for (int i = 0; i < numItems; i++) { // loop through the inventory list
                FoodItem item = inventory.get(i);
                String type = "";

                // Determine the type of the food item by checking its instance
                if (item instanceof Fruit) {
                    type = "f";
                } else if (item instanceof Vegetable) {
                    type = "v";
                } else if (item instanceof Preserve) {
                    type = "p";
                } else if (item instanceof Meat) {
                    type = "m";
                }

                // Write the food item's information to the file based on its type
                switch (type) {
                    case "f":
                        Fruit fruit = (Fruit) item; // cast the item to Fruit type

                        writer.printf("%s\n%d\n%s\n%d\n%.2f\n%.2f\n%s\n", type, item.itemCode, item.itemName,
                                item.itemQuantityInStock, item.itemCost, item.itemPrice, fruit.getOrchardName());
                        break;
                    case "v":
                        Vegetable veggie = (Vegetable) item; // cast the item to Vegetable type

                        writer.printf("%s\n%d\n%s\n%d\n%.2f\n%.2f\n%s\n", type, item.itemCode, item.itemName,
                                item.itemQuantityInStock, item.itemCost, item.itemPrice, veggie.getFarmName());
                        break;
                    case "p":
                        Preserve preserve = (Preserve) item; // cast the item to Preserve type

                        writer.printf("%s\n%d\n%s\n%d\n%.2f\n%.2f\n%d\n", type, item.itemCode, item.itemName,
                                item.itemQuantityInStock, item.itemCost, item.itemPrice, preserve.getJarSize());
                        break;
                    case "m":
                        Meat meat = (Meat) item; // cast the item to Meat type

                        writer.printf("%s\n%d\n%s\n%d\n%.2f\n%.2f\n%s\n", type, item.itemCode, item.itemName,
                                item.itemQuantityInStock, item.itemCost, item.itemPrice, meat.getSlaughterHouse());
                        break;
                }
            }
            System.out.println("Successfully wrote to file."); // indicate success to the user
        } catch (IOException e) { // handle any potential errors with writing to the file
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }

    /**
     * Reads item data from a file and creates new objects based on the data.
     * The file should contain one line for each item, with seven fields separated by commas:
     * - a single character indicating the type of item ("f" for fruit, "v" for vegetable, "p" for preserve, or "m" for meat)
     * - the item code (an integer)
     * - the item name (a string)
     * - the quantity in stock (an integer)
     * - the cost (a float)
     * - the price (a float)
     * - additional data specific to the type of item (an orchard name for fruit, a farm name for vegetable, a jar size for preserve, or a slaughterhouse name for meat)
     *
     * @param scanner a Scanner object to use for user input
     */
    public void readFromFile(Scanner scanner) {
        // Ask the user to enter the filename to read from
        System.out.print("Enter the filename to read from: ");
        String filename = scanner.next(); // get filename from user input

        // Attempt to read from the file specified by the user
        try (Scanner fileScanner = new Scanner(new File(filename))) { // create a Scanner to read the file
            int count = 0; // Counter for the number of fields read
            String[] fields = new String[7]; // Array to store the fields of a single record
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim(); // read the entire line and remove any leading/trailing whitespace
                if (line.isEmpty()) {
                    continue; // skip empty lines
                }
                fields[count++] = line; // add the field to the array
                if (count == 7) { // if all the fields have been read
                    count = 0; // reset the counter
                    try {
                        String type = fields[0];
                        int itemCode = Integer.parseInt(fields[1]);
                        String itemName = fields[2];
                        int itemQuantityInStock = Integer.parseInt(fields[3]);
                        float itemCost = Float.parseFloat(fields[4]);
                        float itemPrice = Float.parseFloat(fields[5]);

                        // Check if the item code is a duplicate
                        if (isDuplicateItemCode(itemCode)) {
                            System.out.println("Error: Duplicate item code " + itemCode + " found in file.");
                            System.out.println("Aborting read. Valid FoodItems before this one have been added to inventory.");
                            return; // abort reading the file
                        }

                        // Create a new food item based on the type specified in the file
                        switch (type) {
                            case "f":
                                String orchardName = fields[6];

                                Fruit fruit = new Fruit();
                                fruit.setItemCode(itemCode);
                                fruit.setItemName(itemName);
                                fruit.setItemQuantityInStock(itemQuantityInStock);
                                fruit.setItemCost(itemCost);
                                fruit.setItemPrice(itemPrice);
                                fruit.setOrchardName(orchardName);

                                inventory.add(fruit);
                                numItems++;
                                break;
                            case "v":
                                String farmName = fields[6];

                                Vegetable vege = new Vegetable();
                                vege.setItemCode(itemCode);
                                vege.setItemName(itemName);
                                vege.setItemQuantityInStock(itemQuantityInStock);
                                vege.setItemCost(itemCost);
                                vege.setItemPrice(itemPrice);
                                vege.setFarmName(farmName);

                                inventory.add(vege);
                                numItems++;
                                break;
                            case "p":
                                int jarSize = Integer.parseInt(fields[6]);

                                Preserve preserve = new Preserve();
                                preserve.setItemCode(itemCode);
                                preserve.setItemName(itemName);
                                preserve.setItemQuantityInStock(itemQuantityInStock);
                                preserve.setItemCost(itemCost);
                                preserve.setItemPrice(itemPrice);
                                preserve.setJarSize(jarSize);

                                inventory.add(preserve);
                                numItems++;
                                break;
                            case "m":
                                String slaughterHouse = fields[6];

                                Meat meat = new Meat();
                                meat.setItemCode(itemCode);
                                meat.setItemName(itemName);
                                meat.setItemQuantityInStock(itemQuantityInStock);
                                meat.setItemCost(itemCost);
                                meat.setItemPrice(itemPrice);
                                meat.setSlaughterHouse(slaughterHouse);

                                inventory.add(meat);
                                numItems++;
                                break;
                            default:
                                System.out.println("Invalid type: " + type);
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid data type in line: " + String.join("\n", fields));
                    }
                    fields = new String[7];
                }
            }
            System.out.println("Successfully read from file.");
        } catch (FileNotFoundException e) { // handle the case where the file is not found
            System.out.println("File not found: " + filename);
        }
    }

    /**
     * Checks whether an item with the given itemCode is already in the inventory.
     *
     * @param itemCode the item code to check
     * @return true if an item with the given itemCode is already in the inventory, false otherwise
     */
    private boolean isDuplicateItemCode(int itemCode) {
        for (FoodItem item : inventory) {
            if (item.getItemCode() == itemCode) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sorts the items in the inventory by their itemCode using a bubble sort algorithm. The method compares
     * adjacent FoodItem objects in the inventory list by calling their compareTo method, which returns a
     * negative integer if this object is less than the other object, zero if they are equal, or a positive
     * integer if this object is greater than the other object. If the result of the comparison is greater
     * than zero, indicating that the j-th element is greater than the j+1-th element, the bubbleSort method
     * swaps the elements.
     */
    public void bubbleSort() {
        int n = numItems; // Get the number of items in the inventory
        FoodItem temp; // Temporary variable used for swapping elements
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (inventory.get(j).compareTo(inventory.get(j + 1)) > 0) {
                    // Swap elements if they are out of order
                    temp = inventory.get(j);
                    inventory.set(j, inventory.get(j + 1));
                    inventory.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Returns a string representation of this object's inventory.
     *
     * @return A string representation of this object's inventory.
     */
    public String toString() {
        return inventory.toString();
    }
}
