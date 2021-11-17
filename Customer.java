import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
	private String name;


	private final List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void clearRentals() {
		this.rentals.clear();
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public void doRental(Video foundVideo) {
		Rental rental = new Rental(foundVideo) ;
		foundVideo.setRented(true);
		rentals.add(rental);
	}

	public boolean returnVideo(String title) {
		for ( Rental rental: rentals ) {
			if ( rental.getVideo().getTitle().equals(title) && rental.getVideo().isRented() ) {
				rental.returnVideo();
				rental.getVideo().setRented(false);
				return true;
			}
		}
		return false;
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();

		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0 ;
			int daysRented = 0;
			daysRented = each.getDaysRented();

			switch (each.getVideo().getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			}

			eachPoint++;

			if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE) )
				eachPoint++;

			if ( daysRented > each.getDaysRentedLimit() )
				eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty()) ;

			result += "\t" + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;

			totalPoint += eachPoint ;
		}

		result += "Total charge: " + totalCharge + "\tTotal Point:" + totalPoint + "\n";

		printCouponEarned(totalPoint);

		return result ;
	}

	private void printCouponEarned(int point){
		if ( point >= 30 ) {
			Log.print("Congrat! You earned two free coupon");
		} else if ( point >= 10 ) {
			Log.print("Congrat! You earned one free coupon");
		}
	}

	public void printRentals() {
		Log.print("Rentals: " + rentals.size());
		for ( Rental rental: rentals ) {
			Log.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
			Log.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
		}
	}
}
