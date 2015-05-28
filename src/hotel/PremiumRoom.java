package hotel;

import utilities.DateTime;
import java.lang.Math;

public class PremiumRoom extends Room
{
	int freeNights;
	double discountRate;
	double nextBookingDiscountVoucher = 0.00;
	
	public PremiumRoom(String roomId, String description, double dailyRate, int freeNights, double discountRate)
	{
		super(roomId, description, dailyRate);
		this.freeNights = freeNights;
		this.discountRate = discountRate;
		status = 'A';
	}
	
	public double getDiscount()
	{
		return nextBookingDiscountVoucher;
	}
	
	public void setDiscount(double discount)
	{
		this.nextBookingDiscountVoucher = discount;
	}
	
	public boolean bookRoom(String customerId, int nightsRequired, int voucher)
	{
		if (this.status == 'A')
		{
			//set the booking start date to the current date and the booking end date to specified amount of days later
			bookingStartDate = new DateTime();
			DateTime.setAdvance(nightsRequired, 0, 0);
			bookingEndDate = new DateTime();
			
			nextBookingDiscountVoucher = voucher;
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
		double costNoVoucher;
		int daysStayed = DateTime.diffDays(bookingEndDate, bookingStartDate) - 1;
		if (daysStayed > 3)
		{
			costNoVoucher = super.checkout() - (freeNights * dailyRate);
		}
		else
		{
			costNoVoucher = super.checkout();
		}
		double costWithVoucher = Math.min(costNoVoucher - nextBookingDiscountVoucher, daysStayed * discountRate);
		
		if (costWithVoucher < 0)
		{
			costWithVoucher = 0.00;
		}
		
		hirerId = null;
		status = 'U';
		return costWithVoucher;
	}
	
	
	public void print()
	{
		super.print();
		
		System.out.println(
				"Discount Rate: $" + discountRate + "\n" +
				"Free Nights: " + freeNights + "\n" +
				"Voucher: $" + nextBookingDiscountVoucher);
	}
	
	
	public String toString()
	{
		String details = roomId + ":" + description + ":" + String.valueOf(status) + ":" + String.valueOf(dailyRate)
					+ ":" + bookingStartDate.toString() + ":" + bookingEndDate.toString() + ":" + String.valueOf(freeNights) + ":" +
					String.valueOf(discountRate);
		return details;
	}
}
