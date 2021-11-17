import java.util.Date;

public class Video {
	private String title ;

	private int priceCode ;
	public static final int REGULAR = 1 ;
	public static final int NEW_RELEASE =2 ;

	private int videoType ;
	public static final int VHS = 1 ;
	public static final int CD = 2 ;
	public static final int DVD = 3 ;

	private Date registeredDate ;
	private boolean rented ;

	public Video(String title, int videoType, int priceCode, Date registeredDate) {
		this.setTitle(title) ;
		this.setVideoType(videoType) ;
		this.setPriceCode(priceCode) ;
		this.registeredDate = registeredDate ;
	}

	public int getLateReturnPointPenalty() {
		int penalty = 0 ;
		switch ( videoType ) {
			case VHS: penalty = 1 ; break ;
			case CD: penalty = 2 ; break ;
			case DVD: penalty = 3 ; break ;
			default:
				break;
		}
		return penalty ;
	}
	public int getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(int priceCode) {
		this.priceCode = priceCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public int getVideoType() {
		return videoType;
	}

	public void setVideoType(int videoType) {
		this.videoType = videoType;
	}

	public static class VideoBuilder {
		private String title;
		private int videoType;
		private int priceCode ;
		private Date registeredDate ;

		public VideoBuilder() {
			registeredDate = new Date();
		}

		public VideoBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public VideoBuilder setVideoType(int videoType) {
			this.videoType = videoType;
			return this;
		}
		public VideoBuilder setPriceCode(int priceCode) {
			this.priceCode = priceCode;
			return this;
		}
		public VideoBuilder setRegisteredDate(Date date) {
			this.registeredDate = date;
			return this;
		}

		public Video build() {
			return new Video(title, videoType, priceCode, registeredDate);
		}
	}
}
