/**
* Project: A00884836Lab1
* File: Lab1.java
* Date: Sep 21, 2016
* Time: 9:24:15 PM
*/
package a00884836Lab1;

import java.util.Arrays;
import a00884836Lab1.data.Player;


/**
* @author Voicu Chirtes, A00884836
*
*/
public class Lab1 {
	private Player[] players;
	
	/**
	 * default constructor
	 */
	public Lab1() {}
	
	/**
	 * @param count the number of players to create
	 */
	public Lab1(int count) {
		players = new Player[count];
		
		//for this lab, for simplicity, we use initialize with direct values here. 
		//this is not to be use in a real world application, 
		//where we would implement proper input methods
		for(int i=0; i<count;i++) { 
			Player player = new Player();
			player.setId(i);
			player.setFirstName("John");
			player.setLastName("Smith");
			player.setEmailAddress("john.smith@gmail.com");
			player.setGamerTag("JohnZ");
			players[i] = player;
		}

	}
	
	/**
	 * Display all players in the object
	 */
	public void displayPlayers() {
		System.out.println(Arrays.toString(players));
	}
	
	/**
	 * Main entry point of the program
	 * @param args command line arguments, first one must be an integer
	 */
	public static void main(String[] args) {
		if(args.length <= 0) { // we must have at least one argument to run the program
			System.out.println("An integer value must be passed to the program");
			System.exit(-1);
		}
		
		try {
			int count = Integer.parseInt(args[0]);
			Lab1 lab1 = new Lab1(count);
			lab1.displayPlayers();
		}
		catch (Exception e) {
			System.out.println("An integer value must be passed to the program");
			System.exit(-1);
		}
	}
}

