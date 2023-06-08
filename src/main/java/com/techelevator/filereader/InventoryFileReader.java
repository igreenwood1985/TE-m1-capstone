package com.techelevator.filereader;

import com.techelevator.items.CandyStoreItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

/*
    This class should contain any and all details of access to the inventory file
 */
public class InventoryFileReader {

    private String inventoryFileName;

    public InventoryFileReader(String inventoryFileName) {
        this.inventoryFileName = inventoryFileName;

    }

    public List<String> readFile() throws FileNotFoundException {

        List<String> fileContentLines = new ArrayList<>();
        File inventoryFile = new File(inventoryFileName);

        try(Scanner fileReader = new Scanner(inventoryFile)){
            while(fileReader.hasNextLine()){
                fileContentLines.add(fileReader.nextLine());
            }
        }
        return fileContentLines;
    }
}
