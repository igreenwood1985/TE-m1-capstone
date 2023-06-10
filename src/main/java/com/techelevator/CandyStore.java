package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import com.techelevator.filereader.LogFileWriter;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {
    private static final int NICKEL = 5;
    private static final int DIME = 10;
    private static final int QUARTER = 25;
    private static final int ONE = 100;
    private static final int FIVE = 500;
    private static final int TEN = 1000;
    private static final int TWENTY = 2000;
    private static final String CHOCOLATE_CODE = "CH";
    private static final String CHOCOLATE_TYPE_STRING = "Chocolate Confectionery";
    private static final String SOUR_CODE = "SR";
    private static final String SOUR_TYPE_STRING = "Sour Flavored Candies";
    private static final String HARD_CANDY_CODE = "HC";
    private static final String HARD_CANDY_TYPE_STRING = "Hard Tack Confectionery";
    private static final String LICORICE_CODE = "LI";
    private static final String LICORICE_TYPE_STRING = "Licorice and Jellies";
    private double currentCustomerBalance = 0.0;

    private LogFileWriter logFileUpdateWriter = new LogFileWriter();

    public void resetBalance(){
        this.currentCustomerBalance = 0.0;
    }
    /*
    BRJ - 6/8, POST 5PM:
    This constant represents the number of delimiter characters (pipes) that are found in a valid inventory file item line.
    If the number of pipes in a given line is not equal to this number, then we'll know the line has something wrong with it.
    I use this constant to validate file lines in our parsing method below - added it as a second condition in addition to our null check.
    I thought about adding another constant to represent our delimiter character, so that in case we started getting like, comma-delimited files instead of pipe delimited ones, we could change it from a central location, but didn't cuz I'm lazy.
     */
    private static final int VALID_LINE_DELIM_COUNT = 4;
    private Map<String, CandyStoreItem> candyStoreItemInventory = new TreeMap<String, CandyStoreItem>();

    private List<CandyStoreItem> cart = new ArrayList<CandyStoreItem>();

    public Map<String, CandyStoreItem> getCandyStoreItemInventory() {
        return candyStoreItemInventory;
    }

    public double getCurrentCustomerBalance(){
        return this.currentCustomerBalance;
    }


    // BRJ - 6/8, POST 5PM:
    // Allowed the default constructor for testing purposes.
    public CandyStore(){

    }
    // BRJ - 6/8, POST 5PM:
    // This constructor will build a candy store with an inventory file path supplied by the user in our menu class.

    public CandyStore(String inventoryFilePath) {
        try {
            buildInventory(loadInventoryFile(inventoryFilePath));
        } catch (FileNotFoundException e ){
            e.getMessage();
        }
    }

    public CandyStoreItem parseCandyStoreItemFromInventoryString(String inventoryFileLine) throws IllegalArgumentException {
        // BRJ - 6/8, POST 5PM:
        // This integer, pipeCount, is a count of the number of pipes in our input string.
        // We get this number by taking the incoming inventoryFileLine, replacing every character that isn't a pipe with an empty string, and then taking the length of the result.
        // In the replaceAll call, we pass a special kind of string: a regular expression. Regular expressions are sort of like search terms - they're patterns of characters that match the contents of strings.
        // The expression I've placed in this method call will match all characters that aren't a pipe. replaceAll takes this set of characters from inventoryFile, and replaces them with an empty string; leaving us with only pipes remaining.
        // To see regular expressions work and learn to write them yourself, the best thing to do is experiment. I recommend this website: regexcrossword.com

        int pipeCount = inventoryFileLine.replaceAll("[^\\|]", "").length();
        if( inventoryFileLine == null
        || pipeCount != VALID_LINE_DELIM_COUNT) {
            throw new IllegalArgumentException("File Inventory Line not valid");
        }
        else {
            CandyStoreItem item = null;
            // get item type from inventory string
            String[] itemPieces = inventoryFileLine.split("\\|");
            String iD = itemPieces[1];
            String name = itemPieces[2];
            double price = Double.parseDouble(itemPieces[3]);
            boolean isWrapped = false;

            if (itemPieces[4].equals("T")) {
                isWrapped = true;
            } else {
                isWrapped = false;
            }

            if (itemPieces[0].equals(CHOCOLATE_CODE)) {
                item = new Chocolate(iD, name, price, isWrapped, CHOCOLATE_TYPE_STRING);
            } else if (itemPieces[0].equals(SOUR_CODE)) {
                item = new Sour(iD, name, price, isWrapped, SOUR_TYPE_STRING);
            } else if (itemPieces[0].equals(HARD_CANDY_CODE)) {
                item = new HardCandy(iD, name, price, isWrapped, HARD_CANDY_TYPE_STRING);
            } else if (itemPieces[0].equals(LICORICE_CODE)) {
                item = new Licorice(iD, name, price, isWrapped, LICORICE_TYPE_STRING);
            }

            return item;
        }
    }
    public List<String> loadInventoryFile (String inventoryFileName) throws FileNotFoundException {
        InventoryFileReader inventoryFileScan = new InventoryFileReader(inventoryFileName);
        List<String> inventoryStrings = inventoryFileScan.readFile();
        return inventoryStrings;
    }
    // BRJ - 6/8, POST 5PM:
    // Refactored this method to be the setter for our store's inventory property, which we call from the CandyStore constructor; which gets called from the main method when the user fires up our application.
    // Instead of returning the inventory map, we're now trying to
    public void buildInventory (List<String> inventoryStrings) throws IllegalArgumentException {
        // changed this to a tree map to sort it.
        Map<String, CandyStoreItem> candyListMap = new TreeMap<>();
        // parser method goes here. it'll build the map.
        // This loop will run the parser over all strings derived from our input file loading method.
        for (String inventoryItem : inventoryStrings){
            CandyStoreItem item = parseCandyStoreItemFromInventoryString(inventoryItem);
            candyListMap.put(item.getId(), item);
        }
        this.candyStoreItemInventory = candyListMap;
        //return candyListMap;
    }

    public void addMoneyToCustomerBalance(int dollarsToAdd) {
        if (dollarsToAdd + currentCustomerBalance > 1000){
           throw new IllegalArgumentException("Balance may not exceed $1000, try again. \n");
        } else if ( dollarsToAdd > 100){
             throw new IllegalArgumentException("Only 100 dollars may be added at a time. Try again. \n");
        } else if ( dollarsToAdd < 0 ) {
            throw new IllegalArgumentException("Enter a positive number. \n");
        }
        else {
            this.currentCustomerBalance += dollarsToAdd;
        }
    }


    public void updateCart(String itemToPurchase, int numberOfItems){
        if (itemToPurchase == null){
            throw new IllegalArgumentException("Enter an ID.");
        }
        CandyStoreItem item = this.candyStoreItemInventory.get(itemToPurchase);
        if (item == null ) {
            throw new IllegalArgumentException("Item does not exist, try again. \n");
        } else if(item.getQuantity() == 0){
            throw new IllegalArgumentException("Product sold out, do you wish to purchase another product? \n");
        } else if(numberOfItems > item.getQuantity()){
            throw new IllegalArgumentException("Insufficient Stock, try again. \n");
        } else if (numberOfItems < 0){
            throw new IllegalArgumentException("Please enter a positive number. \n");
        } else {
            double totalPrice = numberOfItems * item.getPrice();
            if (totalPrice > currentCustomerBalance){
                throw new IllegalArgumentException("Insufficient funds.");
            } else {
                item.sellItem(numberOfItems);
                CandyStoreItem soldItem = buildItem(item);
                this.candyStoreItemInventory.put(item.getId(), item);
                double initialCustomerBalance = this.currentCustomerBalance;
                this.currentCustomerBalance -= totalPrice;
                soldItem.setQuantity(numberOfItems);
                this.cart.add(soldItem);

                logFileUpdateWriter.writeLoggedActivity(
                        numberOfItems + " " + item.getName() + " " + item.getId(),
                        initialCustomerBalance, this.currentCustomerBalance);
            }
        }


    }

    public void clearCart(){
        this.cart = new ArrayList<>();
    }


    // Clears cart at end
    // Resets customer balance
    // Sends return to printReceipt
    public void emptyCartResetBalance(){
        clearCart();
        resetBalance();
    }

    public List<CandyStoreItem> getCart(){
        return this.cart;
    }

    public double calculateTotalSalePrice(){
        double totalPrice = 0;
        for (CandyStoreItem item : this.cart){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public double calculateChangeAmount(){

        return currentCustomerBalance;
    }

    public Map<String, Integer> calculateChangeBillsAndCoins(){
        double changeToGive = calculateChangeAmount();
        // Let's split this at the decimal point so we only have to deal with integers. Multiply by 100.
        int changeInteger = ((int)Math.round((changeToGive * 100)));
        //changeInteger += 1;
        int twentyCount = 0;
        int tenCount = 0;
        int fiveCount = 0;
        int oneCount = 0;
        int quarterCount = 0;
        int dimeCount = 0;
        int nickelCount = 0;

        // instantiate a map to put the count of our units next to the units.
        Map<String, Integer> billsAndCoins = new LinkedHashMap<String, Integer>();

        // Integer division should be able to give us the number we're looking for.
        if (changeInteger >= TWENTY){
            // figure out how many twenties to give back
            twentyCount = changeInteger / TWENTY;
            changeInteger = changeInteger % TWENTY;
            if (twentyCount > 1){
                billsAndCoins.put("Twenties", twentyCount);
            } else {
                billsAndCoins.put("Twenty", twentyCount);
            }
        }
        if (changeInteger >= TEN){
            tenCount = changeInteger / TEN;
            changeInteger = changeInteger % TEN;
            if (tenCount > 1){
                billsAndCoins.put("Tens", tenCount);
            } else {
                billsAndCoins.put("Ten", tenCount);
            }
        }
        if (changeInteger >= FIVE){
            fiveCount = changeInteger / FIVE;
            changeInteger = changeInteger % FIVE;
            if (fiveCount > 1){
                billsAndCoins.put("Fives", fiveCount);
            } else {
                billsAndCoins.put("Five", fiveCount);
            }
        }
        if (changeInteger >= ONE){
            oneCount = changeInteger / ONE;
            changeInteger = changeInteger % ONE;
            if (oneCount > 1){
                billsAndCoins.put("Ones", oneCount);
            } else {
                billsAndCoins.put("One", oneCount);
            }
        }
        if (changeInteger >= QUARTER){
            quarterCount = changeInteger / QUARTER;
            changeInteger = changeInteger % QUARTER;
            if(quarterCount > 1){
                billsAndCoins.put("Quarters", quarterCount);
            } else {
                billsAndCoins.put("Quarter", quarterCount);
            }
        }
        if (changeInteger >= DIME){
            dimeCount = changeInteger / DIME;
            changeInteger = changeInteger % DIME;
            if (dimeCount > 1){
                billsAndCoins.put("Dimes", dimeCount);
            } else {
                billsAndCoins.put("Dime", dimeCount);
            }
        }
        if (changeInteger >= NICKEL ){
            nickelCount = changeInteger / NICKEL;
            changeInteger = changeInteger % NICKEL;
            if (nickelCount > 1){
                billsAndCoins.put("Nickels", nickelCount);
            } else {
                billsAndCoins.put("Nickel", nickelCount );
            }
        }
        return billsAndCoins;
    }

    public CandyStoreItem buildItem(CandyStoreItem item){
        CandyStoreItem newItem = null;

        String type = item.getType();

        if (type.equals(CHOCOLATE_TYPE_STRING)) {
            newItem = new Chocolate((Chocolate) item);
        } else if (type.equals(SOUR_TYPE_STRING)) {
            newItem = new Sour((Sour) item);
        } else if (type.equals(HARD_CANDY_TYPE_STRING)) {
            newItem = new HardCandy((HardCandy) item);
        } else if (type.equals(LICORICE_TYPE_STRING)) {
            newItem = new Licorice((Licorice) item);
        }

        return newItem;

    }

}
