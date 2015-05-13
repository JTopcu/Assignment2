package menu;

import java.util.Scanner;
import hotel.Room;

public class Menu
{
	
	Scanner scanner = new Scanner(System.in);
	
	
	public void runMenu(Room[] rooms)
	{
		int response;
		
		do
		{
			displayMenu();
			
			while(!scanner.hasNextInt())
			{
				displayMenu();
				System.out.print("Enter selection: ");
			}
			
			response = scanner.nextInt();
			selectMenuItem(response, rooms);
			
		} while (response != 0);
	}
	
	public void displayMenu()
	{
		System.out.println("Welcome to the hotel menu");
		System.out.println("--------------------------");
		System.out.println("1. View available rooms");
		System.out.println("2. View available rooms (by price range)");
		System.out.println("3. Book room");
		System.out.println("4. Checkout\n");
		System.out.println("0. Exit\n");
		
		System.out.print("Enter selection: ");
	}
	
	public void selectMenuItem(int response, Room[] rooms)
	{
		int roomId;
		
		switch(response)
		{
			case 0:
				System.out.println("Thank you for using our booking system");
				break;
				
			case 1:
				for(int i = 0; i < 6; i++)
				{
					rooms[i].displayRoom();
				}
				break;
			case 2:
				int lowestPrice;
				int highestPrice;
				int roomsDisplayed = 0;
				
				System.out.print("Enter the lowest price: ");
				lowestPrice = scanner.nextInt();
				
				System.out.print("Enter the highest price: ");
				highestPrice = scanner.nextInt();
				
				for(int i = 0; i < 6; i++)
				{
					if (rooms[i].getDailyRate() >= lowestPrice && rooms[i].getDailyRate() <= highestPrice)
					{
						rooms[i].displayRoom();
						roomsDisplayed++;
					}
				}
				
				if (roomsDisplayed == 0)
				{
					System.out.println("There are no rooms available in that price range.\n");
				}
				else
				{
					System.out.println("These are the rooms that fall in your price range.\n");
				}
				break;
			case 3:
				System.out.println("To book a room we will need the last digit of the room ID, "
									+ "your customer ID and the amount of nights of your stay.");
				
				System.out.print("Room ID: ");
				roomId = scanner.nextInt();
				
				System.out.print("Customer ID: ");
				String customerId = "c" + scanner.next();
				
				System.out.print("Nights: ");
				int nights = scanner.nextInt();
				
				rooms[roomId -1].bookRoom(customerId, nights);
				
				System.out.println("You have successfully booked this room.");
				break;
			case 4:
				System.out.print("Which room are you checking out of?: ");
				roomId = scanner.nextInt();
				if (roomId >= 1 && roomId <= 6 && rooms[roomId - 1].getStatus() == 'B')
				{
					rooms[roomId - 1].checkout();
					System.out.println("You have successfully checked out. The total cost is $" +
										rooms[roomId - 1].checkout());
				}
				
				else
				{
					System.out.println("Sorry, that is an invalid room or the room is currently not booked.\n");
				}
		}
	}
}