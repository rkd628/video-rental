import java.util.Date;

public class Rental {
	private Video video ;
	private RentalStatus status ; // 0 for Rented, 1 for Returned
	private Date rentDate ;
	private Date returnDate ;

	public Rental(Video video) {
		this.video = video ;
		status = RentalStatus.Rented ;
		rentDate = new Date() ;
	}

	public Video getVideo() {
		return video;
	}

	public RentalStatus getStatus() {
		return status;
	}

	public void returnVideo() {
		if ( status == RentalStatus.Returned ) {
			returnDate = new Date() ;
		}
	}

	public int getDaysRentedLimit() {
		int limit = 0 ;
		int daysRented = getDaysRented();

		if ( daysRented <= 2) return limit ;

		switch ( video.getVideoType() ) {
			case Video.VHS: limit = 5 ; break ;
			case Video.CD: limit = 3 ; break ;
			case Video.DVD: limit = 2 ; break ;
		}
		return limit ;
	}

	public long getDiffDate() {
		long diff = 0;
		if(getStatus() == RentalStatus.Returned){
			diff = returnDate.getTime() - rentDate.getTime();
		} else {
			diff = new Date().getTime() - rentDate.getTime();
		}
		return diff;
	}

	public int getDaysRented(){
		return (int) (getDiffDate() / (1000 * 60 * 60 * 24)) + 1;
	}
}
