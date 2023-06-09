package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Scanner;

/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * This class should control the logical workflow of the application, but it should do no other
 * work.  It should communicate with the user (System.in and System.out) using the Menu class and ask
 * the CandyStore class to do any work and pass the results between those 2 classes.
 */
public class ApplicationCLI {

//	Menu callMenu = new Menu

	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private Menu menu;

	public ApplicationCLI(Menu menu) {
		this.menu = menu;
	}

	/*
	 * Your application starts here
	 */
	public void run() {
		// BRJ - 6/8, POST 5PM:
		// Creating the store before we do literally anything else.
		String path = menu.getInventoryFilePath();
		CandyStore store = new CandyStore(path);


		menu.showWelcomeMessage();
		while (true) {
			/*
			Display the Starting Menu and get the users choice.
			Remember all uses of System.out and System.in should be in the menu */
			menu.showMainMenu();
			String userChoice = menu.getUserCommand();


			/*
			IF the User Choice is Show Inventory,
				THEN show the candy store items for sale
				*/
			if(userChoice.equals("1")) {
				menu.printInventory(store);
			}

			/*
			ELSE IF the User's Choice is Make Sale,
				THEN go to the make sale menu
				*/
			else if(userChoice.equals("2")){

				while (true) {
					menu.showMakeSaleMenu(store);
					String subMenuChoice = menu.getUserCommand();
					if (subMenuChoice.equals("1")){
						menu.getAmountOfMoneyToAdd(store);
					} else if (subMenuChoice.equals("2")) {
						// select products/add to cart
						menu.addItemToCart(store);
					} else if (subMenuChoice.equals("3")){
						// complete sale
						System.out.println("Returning to main menu.");
						break;
					}
				}
			}
			/*
			ELSE IF the User's Choice is Quit
				THEN break the loop so the application stops
			*/
			else if(userChoice.equals("3")){
				// Quit
				System.out.println("Thank you for your business!");
				break;
			}
			else {
				System.out.println("Invalid input, please enter a valid choice");
				System.out.println(" ");

			}
		}
	}

	/*
	 * This starts the application, but you shouldn't need to change it.  
	 */
	public static void main(String[] args) {
		Menu menu = new Menu();
		ApplicationCLI cli = new ApplicationCLI(menu);
		cli.run();
	}
}
