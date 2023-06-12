package com.techelevator.filereader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    This class should contain any and all details of access to the log file
 */
public class LogFileWriter {


    // Declare a constant to call the file pathway for the log file
    private static String LOG_FILE_PATHWAY = "log.txt";


    // Declaration of variables using LocalDateTime and DateTimeFormatter Libraries
    private LocalDateTime timeNow = LocalDateTime.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

    // timeStamp instance variable declaration
    private String timeStamp;

    //Getter for time stamp
    public String getTimeStamp() {
        return timeStamp = timeNow.format(formatter);
    }

    // Method Constructor for LogFileWriter Class
    public void writeLoggedActivity(String command, double initialCustomerBalance,
                                    double finalCustomerBalance){

        // Declaration to create a string containing information to be written as a line in the log.txt file
        String logEntry = String.format("%s %s $%.2f $%.2f", getTimeStamp(), command,
                initialCustomerBalance, finalCustomerBalance);

        // try/catch to access the log.txt file and append it to the existing file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATHWAY, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e){
            System.out.println("Failed to write to the log file");
        }
    }

}
