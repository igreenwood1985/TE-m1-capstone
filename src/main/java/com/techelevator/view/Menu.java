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

	public void showMakeSaleMenu(CandyStore store){
		System.out.println("(1) Take Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Sale");
		System.out.printf("Current Customer Balance: $%1.2f \n", store.getCurrentCustomerBalance()); //getCustomerBalance());
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
	public void getAmountOfMoneyToAdd(CandyStore store){
		System.out.println("Enter an amount of money to add between 1 and 100.");
		String amountString = getUserCommand();
		int amountInteger = 0;
		try {
			amountInteger = Integer.parseInt(amountString);
		} catch (NumberFormatException e1){
			System.out.println("Enter the dollar amount as a whole number.");
			return;
		}
		try {
			store.addMoneyToCustomerBalance(amountInteger);
		} catch (IllegalArgumentException e2){
			System.out.println(e2.getMessage());
			// For some reason, if we don't null out e2 after sending our message, we don't print subsequent exception messages.
			e2 = null;

			try {
				Thread.sleep(500);
			} catch (InterruptedException e3){
				System.out.println("Interrupted during pause! What the heck?! Quitting.");
			}
		}
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
			/*
			BRJ 6/8, POST 5PM:
			As far as I'm concerned, printf is straight-up witchcraft.
			I can tell you what it's doing, but I can't tell you how or why it works.
			We're feeding our item information to printf as strings, and specifying padding values - that's what those %-10s's do.
			The % sign tells printf, "put a variable value here."
			The 's' tells printf, "we're giving you a string."
			Strings can get padded - that's the -10, -20, and whatnot.
			The padding values are what allow us to print the inventory in neat columns.
			Where I get completely lost is trying to understand how the padding carries over for each string we print down the column.
			Cuz like, to pad properly, we need to know how long each string is; relative to each string in the column.
			And we're printing by row. So, like... what?!?!
			Those string lengths aren't stored in any obvious data structure; so HOW DOES IT KNOW ABOUT THEM, DUDE!??!??!?!
			IS IT SECRETLY SAVING EVERYTHING SOMEWHERE?
			IS IT PADDING AGAINST SOME ABSOLUTE POSITION AND I'M JUST TOO DUMB TO UNDERSTAND GEOMETRY?
			IS IT CONSULTING WITH DEMONS?!
			I DON'T KNOW
			THE DOCUMENTATION IS LIKE EIGHT MILES LONG
			I OPENED IT IT ONCE, AND BOSS MUSIC STARTED PLAYING
			 */
			System.out.printf("%-10s %-20s %-10s %-10s $%1.2f \n",id, name, wrapper, qty, item.getPrice());
		}
		System.out.println("");
	}

	public void addItemToCart(CandyStore store) {
		printInventory(store);
		System.out.println("Enter the ID of the Item you would like to purchase: ");
		String itemId = getUserCommand().toUpperCase();

		System.out.println("Enter the number of items you would like to purchase: ");
		int itemAmount = 0;
		try {
			itemAmount = Integer.parseInt(getUserCommand());
		} catch (NumberFormatException n){
			System.out.println("Enter a whole number.");
			return;
		}
		try {
			store.updateCart(itemId, itemAmount);
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
			e = null;
			return;
		}
	}

	public void printReceipt(List<CandyStoreItem> cart){

	}
}
