package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {

    private static final String CHOCOLATE_CODE = "CH";
    private static final String SOUR_CODE = "SR";
    private static final String HARD_CANDY_CODE = "HC";
    private static final String LICORICE_CODE = "LI";
    private Map<String, CandyStoreItem> candyStoreItemInventory = new HashMap<String, CandyStoreItem>();

    public Map<String, CandyStoreItem> getCandyStoreItemInventory() {
        return candyStoreItemInventory;
    }

    public CandyStoreItem parseCandyStoreItemFromInventoryString(String inventoryFileLine) throws IllegalArgumentException {
        if(inventoryFileLine == null) {
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
    public List<String> loadFile (String inventoryFileName) throws FileNotFoundException {
        InventoryFileReader inventoryFileScan = new InventoryFileReader(inventoryFileName);
        List<String> inventoryStrings = inventoryFileScan.readFile();
        return inventoryStrings;
    }



    public Map<String, CandyStoreItem> buildInventory (List<String> inventoryStrings) {

        Map<String, CandyStoreItem> candyListMap = new HashMap<String, CandyStoreItem>();
        // parser method goes here. it'll build the map.\
        for (String inventoryItem : inventoryStrings){
            // add some stuff 2 map
            CandyStoreItem item = parseCandyStoreItemFromInventoryString(inventoryItem);
            candyListMap.put(item.getId(), item);
        }
        return candyListMap;
    }
    public void setCandyStoreItemInventory(Map<String, CandyStoreItem> candyStoreItemInventory) {
        this.candyStoreItemInventory = candyStoreItemInventory;

    }
}
