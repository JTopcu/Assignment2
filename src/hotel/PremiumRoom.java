package hotel;

public class PremiumRoom extends Room
{
	int freeNights;
	double discountRate;
	double nextBookingDiscountVoucher;
	
	public PremiumRoom(String roomId, String description, double dailyRate, int freeNights, double discountRate,
			double nextBookingDiscountVoucher)
	{
		super(roomId, description, dailyRate);
		this.freeNights = freeNights;
		this.discountRate = discountRate;
		this.nextBookingDiscountVoucher = nextBookingDiscountVoucher;
	}
	
	public boolean bookRoom(String customerId, int nightsRequired, int Voucher)
	{
		
	}
}
