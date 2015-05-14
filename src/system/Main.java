package system;

import hotel.*;
import menu.Menu;

public class Main
{

	public static void main(String[] args)
	{
		Room[] rooms = new Room[6];
		
		rooms[0] = new Room("GARDEN0001", "NorthWest Garden View", 45.00);
		rooms[1] = new PremiumRoom("POOL0001", "Poolside Terrace", 90.00, 1, 50.00);
		rooms[2] = new Room("GARDEN0003", "North Garden View", 35.00);
		rooms[3] = new Room("GARDEN0004", "South Garden View", 52.00);
		rooms[4] = new Room("GARDEN0005", "West Garden View", 35.00);
		rooms[5] = new PremiumRoom("POOL0002", "Poolside Private", 125.00, 2, 75.00);
		
		Menu menu = new Menu();
		menu.runMenu(rooms);
	}
}
