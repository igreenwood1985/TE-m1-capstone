package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.*;

import java.io.FileNotFoundException;
import java.util.*;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {
    private static final String CHOCOLATE_CODE = "CH";
    private static final String SOUR_CODE = "SR";
    private static final String HARD_CANDY_CODE = "HC";
    private static final String LICORICE_CODE = "LI";
    private double currentCustomerBalance = 0.0;
    /*
    BRJ - 6/8, POST 5PM:
    This constant represents the number of delimiter characters (pipes) that are found in a valid inventory file item line.
    If the number of pipes in a given line is not equal to this number, then we'll know the line has something wrong with it.
    I use this constant to validate file lines in our parsing method below - added it as a second condition in addition to our null check.
    I thought about adding another constant to represent our delimiter character, so that in case we started getting like, comma-delimited files instead of pipe delimited ones, we could change it from a central location, but didn't cuz I'm lazy.
     */
    private static final int VALID_LINE_DELIM_COUNT = 4;
    private Map<String, CandyStoreItem> candyStoreItemInventory = new TreeMap<String, CandyStoreItem>();

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
                item = new Chocolate(iD, name, price, isWrapped);
            } else if (itemPieces[0].equals(SOUR_CODE)) {
                item = new Sour(iD, name, price, isWrapped);
            } else if (itemPieces[0].equals(HARD_CANDY_CODE)) {
                item = new HardCandy(iD, name, price, isWrapped);
            } else if (itemPieces[0].equals(LICORICE_CODE)) {
                item = new Licorice(iD, name, price, isWrapped);
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
    private void buildInventory (List<String> inventoryStrings) throws IllegalArgumentException {
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

    public void addMoneyToCustomerBalance(int dollarsToAdd) throws IllegalArgumentException {
        if (dollarsToAdd + currentCustomerBalance > 1000){
           IllegalArgumentException overBalance = new IllegalArgumentException("Balance may not exceed $1000, try again. \n");
            throw overBalance;
        } else if ( dollarsToAdd > 100){
            IllegalArgumentException overAddLimit = new IllegalArgumentException("Only 100 dollars may be added at a time. Try again. \n");
            throw overAddLimit;
        } else if ( dollarsToAdd < 0 ) {
            IllegalArgumentException negativeAddAmount = new IllegalArgumentException("Enter a positive number. \n");
            throw negativeAddAmount;
        }
        else {
            this.currentCustomerBalance += dollarsToAdd;
        }
    }
}
