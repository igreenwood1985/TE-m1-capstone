package com.techelevator;

import com.techelevator.filereader.InventoryFileReader;
import com.techelevator.items.CandyStoreItem;

import java.io.FileNotFoundException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/*
    This class should encapsulate all the functionality of the Candy Store application, meaning it should
    contain all the "work"
 */
public class CandyStore {
    private Map<String, CandyStoreItem> candyStoreItemInventory = new HashMap<String, CandyStoreItem>();

    public Map<String, CandyStoreItem> getCandyStoreItemInventory() {
        return candyStoreItemInventory;
    }

    public CandyStoreItem parseCandyStoreItemFromInventoryString(String inventoryFileLine){
        CandyStoreItem item;
        // get item type from inventory string
        if(){

        }
        return item;
    }


    public Map<String, CandyStoreItem> buildInventory (String inventoryFileName) throws FileNotFoundException {
        InventoryFileReader inventoryFileScan = new InventoryFileReader(inventoryFileName);
        List<String> inventoryStrings = inventoryFileScan.readFile();
        Map<String, CandyStoreItem> candyListMap = new HashMap<String, CandyStoreItem>();
        // parser method goes here. it'll build the map.\
        for (String inventoryItem : inventoryStrings){
            // add some stuff 2 map
        }
        return candyListMap;
    }
    public void setCandyStoreItemInventory(Map<String, CandyStoreItem> candyStoreItemInventory) {
        this.candyStoreItemInventory = candyStoreItemInventory;

    }
}
