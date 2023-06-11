package com.techelevator.filereader;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class LogFileWriterTests {

    @Test
    public void testWriteLoggedActivity(){
        // Arrange
        LogFileWriter logFileWriter = new LogFileWriter();
        String logFilePath = "Log.txt";

        // Set up test data
        String command = "MONEY RECEIVED";
        double initialCustomerBalance = 50.00;
        double finalCustomerBalance = 50.00;

        //Act
        logFileWriter.writeLoggedActivity(command, initialCustomerBalance, finalCustomerBalance);

        //Assert
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))){
            String logEntry = reader.readLine();
            assertNotNull(logEntry);
            assertEquals("06/10/2023 05:20:49 PM 10 Snuckers Bar C1: $20.00 $6.50", logEntry);
        } catch (IOException e){
            fail("Failed to read the log file.");
        }
    }
}
