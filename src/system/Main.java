package system;

import java.io.IOException;

import hotel.*;
import menu.Menu;

public class Main
{

	public static void main(String[] args)
	{
		Room[] rooms = new Room[6];
		
		Menu menu = new Menu();
		try
		{
			menu.loadRooms(rooms);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		menu.runMenu(rooms);
	}
}
