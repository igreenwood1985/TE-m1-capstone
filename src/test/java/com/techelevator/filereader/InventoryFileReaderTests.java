package com.techelevator.filereader;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryFileReaderTests {

    @Test
    public void testReadFile() {
        //Arrange
        String inventoryFileName = "inventory.csv";
        InventoryFileReader inventoryFileReader = new InventoryFileReader(inventoryFileName);

        // Act
        List<String> fileContentLines = null;

        try {
            fileContentLines = inventoryFileReader.readFile();
        } catch (FileNotFoundException e){
            fail("Inventory file not found.");
        }

        // Assert
        assertNotNull(fileContentLines);
        assertEquals(18, fileContentLines.size());
        assertEquals("CH|C1|Snuckers Bar|1.35|T", fileContentLines.get(0));
        assertEquals("CH|C2|Flat Top|.80|T", fileContentLines.get(1));
        assertEquals("CH|C4|Dipped Beans|6.95|F", fileContentLines.get(2));
    }
}
