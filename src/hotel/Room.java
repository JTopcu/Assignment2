package hotel;

import utilities.DateTime;

public class Room
{
	private String roomId, description, hirerId;
	private DateTime bookingStartDate, bookingEndDate;
	private char status;
	private double dailyRate;
	
	
	public Room(String roomId, String description, double dailyRate)
	{
		this.roomId = roomId;
		this.description = description;
		this.dailyRate = dailyRate;
		status = 'A';
	}
	
	
	public double getDailyRate()
	{
		return dailyRate;
	}
	
	public char getStatus()
	{
		return status;
	}
	
	public boolean bookRoom(String customerId, int nightsRequired)
	{
		if (this.status == 'A')
		{
			//set the booking start date to the current date and the booking end date to specified amount of days later
			bookingStartDate = new DateTime();
			DateTime.setAdvance(nightsRequired, 0, 0);
			bookingEndDate = new DateTime();
			
			//set the hirer for the room and change the room status to booked
			hirerId = customerId;
			status = 'B';
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	
	public double checkout()
	{
		int daysStayed = DateTime.diffDays(bookingEndDate, bookingStartDate);
		double totalCost = (daysStayed - 1) * dailyRate;
		status = 'U';
		return totalCost;
	}
	
	
	public boolean cleanRoom()
	{
		if (status == 'U')
		{
			status = 'A';
			return true;
		}
		
		else
		{
			return false;
		}
	}
	
	
	public void print()
	{
		if (status == 'B')
		{
			System.out.println("ROOM DETAILS" + "\n" +
								"--------------------------\n" +
								"Room number: " + roomId + "\n" + 
								"Description: " + description + "\n" + 
								"Daily Rate: $" + dailyRate + "\n" + 
								"Room Status: " + status + "\n" + 
								"Booked on: " + bookingStartDate + "\n" + 
								"Checkout date: " + bookingEndDate + "\n" + 
								"Customer ID: " + hirerId + "\n" +
								"--------------------------");
		}
		
		else
		{
			System.out.println("ROOM DETAILS" + "\n" +
								"--------------------------\n" +
								"Room number: " + roomId + "\n" + 
								"Description: " + description + "\n" + 
								"Daily Rate: $" + dailyRate + "\n" + 
								"Room Status: " + status + "\n" +  
								"--------------------------");
		}
	}
	
	
	public void displayRoom()
	{
		System.out.println("ID:\t" + roomId + "\n" +
							"DESC:\t" + description + "\n" +
							"RATE:\t" + dailyRate + "\n");
	}
	
	public String toString()
	{
		String details = roomId + ":" + description + ":" + String.valueOf(status) + ":" + String.valueOf(dailyRate)
						+ ":" + bookingStartDate.toString() + ":" + bookingEndDate.toString();
		
		return details;
	}
}