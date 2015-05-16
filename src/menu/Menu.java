package menu;

import java.util.Scanner;
import hotel.*;

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
		String roomId;
		
		switch(response)
		{
			case 0:
				System.out.println("Thank you for using our booking system.  Here is your receipt:");
				for(int i = 0; i < rooms.length; i++)
				{
					System.out.println(rooms[i].toString());
				}
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
				boolean booked = false;
				boolean premium = false;
				int voucher = 0;
				
				System.out.println("To book a room we will need the last digit of the room ID, "
									+ "your customer ID and the amount of nights of your stay.");
				
				System.out.print("Room ID: ");
				roomId = scanner.next();
				
				if (roomId.equals("POOL0001") || roomId.equals("POOL0002"))
				{
					System.out.print("Please provide the voucher amount: ");
					voucher = scanner.nextInt();
					premium = true;
				}
				System.out.print("Customer ID: ");
				String customerId = "c" + scanner.next();
				
				System.out.print("Nights: ");
				int nights = scanner.nextInt();
				
				for (int i = 0; i < rooms.length; i++)
				{
					if (roomId.equals(rooms[i].getId()) && rooms[i].getStatus() == 'A' && premium)
					{
						((PremiumRoom) rooms[i]).bookRoom(customerId, nights, voucher);
						System.out.println("You have successfully booked this room.");
						booked = true;
					}
					
					else if (roomId.equals(rooms[i].getId()) && rooms[i].getStatus() == 'A')
					{
						rooms[i].bookRoom(customerId, nights);
						System.out.println("You have successfully booked this room.");
						booked = true;
					}
				}
				
				if (!booked)
				{
					System.out.println("Sorry, that room is unavailable.");
				}
				
				break;
				
			case 4:
				System.out.print("Which room are you checking out of?: ");
				roomId = scanner.next();
				boolean checkedOut = false;
				
				for (int i = 0; i < rooms.length; i++)
				{
					if (roomId.equals(rooms[i].getId()) && rooms[i].getStatus() == 'B')
					{
						rooms[i].checkout();
						System.out.println("You have successfully checked out. The total cost is $" +
								rooms[i].checkout());
						checkedOut = true;
					}
				}
				if (!checkedOut)
				{
					System.out.println("Sorry, that is an invalid room or the room is currently not booked.\n");
				}
				
				break;
				
			case 5:
				
		}
	}
}