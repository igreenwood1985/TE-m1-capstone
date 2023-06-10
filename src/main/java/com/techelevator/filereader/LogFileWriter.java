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

    private static String LOG_FILE_PATHWAY = "log.txt";
    public void writeLoggedActivity(String command, double initialCustomerBalance,
                                    double finalCustomerBalance){

        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String timestamp = timeNow.format(formatter);

        String logEntry = String.format("%s %s: $%.2f $%.2f", timestamp, command,
                initialCustomerBalance, finalCustomerBalance);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE_PATHWAY, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e){
            System.out.println("Failed to write to the log file");
        }
    }

}
