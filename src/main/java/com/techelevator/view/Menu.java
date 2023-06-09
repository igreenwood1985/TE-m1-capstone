package com.techelevator.view;

import com.techelevator.CandyStore;
import com.techelevator.items.CandyStoreItem;

import java.util.*;

/*
 * This is the only class that should have any usage of System.out or System.in
 *
 * Usage of System.in or System.out should not appear ANYWHERE else in your code outside of this class.
 *
 * Work to get input from the user or display output to the user should be done in this class, however, it
 * should include no "work" that is the job of the candy store.
 */
public class Menu {
	
	private static final Scanner in = new Scanner(System.in);

	public void showWelcomeMessage() {
		System.out.println("***************************");
		System.out.println("**     Silver Shamrock   **");
		System.out.println("**      Candy Company    **");
		System.out.println("***************************");
		System.out.println();
	}

	public void showMainMenu(){
		System.out.println("(1) Show Inventory");
		System.out.println("(2) Make Sale");
		System.out.println("(3) Quit");
	}
	public String getInventoryFilePath(){
		 System.out.println("Please enter a file path.");
		 String filePath = in.nextLine();
		 return filePath;
	}
	public String getUserCommand(){
		String userCommand = in.nextLine();
		return userCommand;
	}

	public void printInventory(CandyStore store) {
		Map<String, CandyStoreItem> inventory = store.getCandyStoreItemInventory();
//		System.out.printf("Id%n Name%n Wrapper%n Qty%n Price%n %n");
		System.out.printf("%-10s %-20s %-10s %-10s %-10s \n", "Id", "Name", "Wrapper", "Qty" , "Price");
		for (CandyStoreItem item : inventory.values()){
			String id = item.getId();
			String name = item.getName();
			String wrapper = "";
			String qty = "";
			//String price = String.valueOf(item.getPrice());
			if (item.getQuantity() == 0){
				qty = "SOLD OUT";
			} else {
				qty = String.valueOf(item.getQuantity());
			}
			if (item.isIndividuallyWrapped()){
				wrapper = "Y";
			} else {
				wrapper = "N";
			}
			// BRJ
			System.out.printf("%-10s %-20s %-10s %-10s $%1.2f \n",id, name, wrapper, qty, item.getPrice());


		}
		System.out.println("");
	}
}
