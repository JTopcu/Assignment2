package menu;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import utilities.DateTime;
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
	
	public void saveRooms(Room[] rooms) throws IOException
	{
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("data.txt")));

		for(int i = 0; i < rooms.length; i++)
		{
			pw.print(rooms[i].toString());
			if(rooms[i].getHirerId() != null)
			{
				pw.print(":" + rooms[i].getHirerId());
				
			}
			if((rooms[i].getId().equals("POOL0001") || rooms[i].getId().equals("POOL0002")) && ((PremiumRoom) rooms[i]).getDiscount() > 0)
			{
				pw.print(":" + ((PremiumRoom) rooms[i]).getDiscount());
			}
			pw.println();
		}
		pw.close();
	}
	
	public void loadRooms(Room[] rooms) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
		String inputLine = br.readLine();
		int roomNumber = 0;
		
		while(inputLine != null)
		{
			StringTokenizer eachRoom = new StringTokenizer(inputLine, ":");
			String room = eachRoom.nextToken();
			String description = eachRoom.nextToken();
			String stringStatus = eachRoom.nextToken();
			char status = stringStatus.charAt(0);
			double dailyRate = Double.parseDouble(eachRoom.nextToken());
			
			StringTokenizer sDate = new StringTokenizer(eachRoom.nextToken(), "-");
			int sYear = Integer.parseInt(sDate.nextToken());
			int sMonth = Integer.parseInt(sDate.nextToken());
			int sDay = Integer.parseInt(sDate.nextToken());
			DateTime startDate = new DateTime(sDay, sMonth, sYear);
			
			StringTokenizer eDate = new StringTokenizer(eachRoom.nextToken(), "-");
			int eYear = Integer.parseInt(eDate.nextToken());
			int eMonth = Integer.parseInt(eDate.nextToken());
			int eDay = Integer.parseInt(eDate.nextToken());
			DateTime endDate = new DateTime(eDay, eMonth, eYear);
			
			if(room.equals("POOL0001") || room.equals("POOL0002"))
			{
				int freeNights = Integer.parseInt(eachRoom.nextToken());
				double discountRate = Double.parseDouble(eachRoom.nextToken());
				rooms[roomNumber] = new PremiumRoom(room, description, dailyRate, freeNights, discountRate);				
			}
			else
			{
				rooms[roomNumber] = new Room(room, description, dailyRate);
			}
			
			rooms[roomNumber].setStatus(status);
			rooms[roomNumber].setDate(startDate, endDate);
			
			
			if(eachRoom.hasMoreTokens())
			{
				String hirerId = eachRoom.nextToken();
				rooms[roomNumber].setHirerId(hirerId);
			}
			
			inputLine = br.readLine();
			roomNumber++;
		}
	}
	
	public void displayMenu()
	{
		System.out.println("Welcome to the hotel menu");
		System.out.println("--------------------------");
		System.out.println("1. View available rooms");
		System.out.println("2. View available rooms (by price range)");
		System.out.println("3. Book room");
		System.out.println("4. Checkout");
		System.out.println("5. Clean room\n"
				+ "");
		System.out.println("0. Exit\n");
		
		System.out.print("Enter selection: ");
	}
	
	public void selectMenuItem(int response, Room[] rooms)
	{
		String roomId;
		boolean valid = false;
		switch(response)
		{
			case 0:
				System.out.println("Thank you for using our booking system.  Here is your receipt:");
				for(int i = 0; i < rooms.length; i++)
				{
					System.out.println(rooms[i].toString());
				}
				try
				{
					saveRooms(rooms);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				break;
				
			case 1:
				for(int i = 0; i < 6; i++)
				{
					rooms[i].displayRoom();
				}
				break;
			case 2:
				int lowestPrice = 0;
				int highestPrice = 0;
				int roomsDisplayed = 0;
				
				do 
				{
					try
					{
						System.out.print("Enter the lowest price: ");
						lowestPrice = scanner.nextInt();
						valid = true;
					}
					catch (InputMismatchException e)
					{
						System.out.println("Please enter only numbers.");
						scanner.nextLine();
					}
				} while(!valid);
				
				valid = false;
				do 
				{
					try
					{
						System.out.print("Enter the highest price: ");
						highestPrice = scanner.nextInt();
						valid = true;
					}
					catch (InputMismatchException e)
					{
						System.out.println("Please enter only numbers.");
						scanner.nextLine();
					}
				} while(!valid);
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
				
				System.out.println("To book a room we will need the room ID, "
									+ "your customer ID and the amount of nights of your stay.");
				
				System.out.print("Room ID: ");
				roomId = scanner.next();
				
				if (roomId.equals("POOL0001") || roomId.equals("POOL0002"))
				{
					valid = false;
					do {
					try
					{
						System.out.print("Please provide the voucher amount: ");
						voucher = scanner.nextInt();
						premium = true;
						valid = true;
					}
					catch (InputMismatchException e)
					{
						System.out.println("Please enter a valid number.");
						scanner.nextLine();
					}
					} while(!valid);
				}
				System.out.print("Customer ID: ");
				String customerId = "c" + scanner.next();
				
				valid = false;
				int nights = 0;
				do
				{
				try
				{
					System.out.print("Nights: ");
					nights = scanner.nextInt();
					while(nights <= 0)
					{
						System.out.print("Please enter a number above 0: ");
						scanner.nextLine();
						nights = scanner.nextInt();
					}
					valid = true;
				}
				catch(InputMismatchException e)
				{
					System.out.println("Please enter a valid number.");
					scanner.nextLine();
				}
				} while(!valid);
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
				System.out.print("Which room would you like cleaned?");
				String cleaner = scanner.next();
				boolean cleaned = false;
				
				for (int i = 0; i < rooms.length; i++)
				{
					if (cleaner.equals(rooms[i].getId()))
					{
						if (rooms[i].cleanRoom())
						{
							rooms[i].cleanRoom();
							System.out.println("The room has been set for cleaning.");
							cleaned = true;
						}
					}
				}
				
				if (!cleaned)
				{
					System.out.println("Sorry, that room can not be cleaned at this time.");
				}
		}
	}
}