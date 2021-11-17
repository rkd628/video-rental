import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Domain ~ Presentation - should divide
public class VRUI {
	private static final Scanner scanner = new Scanner(System.in) ;

	// Customer List Video List
	private final List<Customer> customers = new ArrayList<>() ;

	private final List<Video> videos = new ArrayList<>() ;

	public static void main(String[] args) {
		VRUI ui = new VRUI() ;

		boolean quit = false ;
		while ( ! quit ) {
			int command = ui.showCommand() ;
			switch ( command ) {
				case 0: quit = true ; break ;
				case 1: ui.listCustomers() ; break ;
				case 2: ui.listVideos() ; break ;
				case 3: ui.register("customer") ; break ;
				case 4: ui.register("video") ; break ;
				case 5: ui.rentVideo() ; break ;
				case 6: ui.returnVideo() ; break ;
				case 7: ui.getCustomerReport() ; break;
				case 8: ui.clearRentals() ; break ;
				case -1: ui.init() ; break ;
				default: break ;
			}
		}
		Log.print("Bye");
	}
	public Customer findCustomer(String customerName) {
		Customer foundCustomer = null ;
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				foundCustomer = customer ;
				break ;
			}
		}
		return foundCustomer;
	}
	public void clearRentals() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		// Duplication
		Customer foundCustomer = findCustomer(customerName);

		if ( foundCustomer == null ) {
			System.out.println("No customer found") ;
		} else {
			// query , modifier
			System.out.println("Name: " + foundCustomer.getName() +
					"\tRentals: " + foundCustomer.getRentals().size()) ;
			for ( Rental rental: foundCustomer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}

			List<Rental> rentals = new ArrayList<Rental>() ;
			foundCustomer.setRentals(rentals);
		}
	}

	public void returnVideo() {
		System.out.println("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = findCustomer(customerName);

		if ( foundCustomer == null ) return ;

		System.out.println("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		List<Rental> customerRentals = foundCustomer.getRentals() ;
		for ( Rental rental: customerRentals ) {
			if ( rental.getVideo().getTitle().equals(videoTitle) && rental.getVideo().isRented() ) {
				rental.returnVideo();
				rental.getVideo().setRented(false);
				break ;
			}
		}
	}

	private void init() {
		Customer james = new Customer("James") ;
		Customer brown = new Customer("Brown") ;
		customers.add(james) ;
		customers.add(brown) ;

		Video v1 = new Video.VideoBuilder().setTitle("v1").setVideoType(Video.CD)
				.setPriceCode(Video.REGULAR).build();
		Video v2 = new Video.VideoBuilder().setTitle("v2").setVideoType(Video.DVD)
				.setPriceCode(Video.NEW_RELEASE).build();
		videos.add(v1) ;
		videos.add(v2) ;

		Rental r1 = new Rental(v1) ;
		Rental r2 = new Rental(v2) ;

		james.addRental(r1) ;
		james.addRental(r2) ;
	}

	public void listVideos() {
		Log.print("List of videos");

		for ( Video video: videos ) {
			Log.print("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		Log.print("End of list");
	}

	public void listCustomers() {
		Log.print("List of customers");
		for ( Customer customer: customers ) {
			Log.print("Name: " + customer.getName() +
					"\tRentals: " + customer.getRentals().size()) ;
			for ( Rental rental: customer.getRentals() ) {
				System.out.print("\tTitle: " + rental.getVideo().getTitle() + " ") ;
				System.out.print("\tPrice Code: " + rental.getVideo().getPriceCode()) ;
			}
		}
		Log.print("End of list");
	}

	public void getCustomerReport() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = getCustomer(customerName) ;

		if ( foundCustomer == null ) {
			Log.print("No customer found") ;
		} else {
			String result = foundCustomer.getReport() ;
			Log.print(result);
		}
	}

	public void rentVideo() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = getCustomer(customerName) ;

		if ( foundCustomer == null ) return ;

		Log.print("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		Video foundVideo = findVideoByTitle(videoTitle);

		if (foundVideo == null) {
			return;
		}

		foundCustomer.doRental(foundVideo);
	}

	private Video findVideoByTitle(String title){
		for ( Video video: videos ) {
			if ( video.getTitle().equals(title) && video.isRented() == false ) {
				return video;
			}
		}

		return null;
	}

	public void register(String object) {
		if ( object.equals("customer") ) {
			Log.print("Enter customer name: ") ;
			String name = scanner.next();
			Customer customer = new Customer(name) ;
			customers.add(customer) ;
		}
		else {
			Log.print("Enter video title to register: ") ;
			String title = scanner.next() ;

			Log.print("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
			int videoType = scanner.nextInt();

			Log.print("Enter price code( 1 for Regular, 2 for New Release ):") ;
			int priceCode = scanner.nextInt();

			Date registeredDate = new Date();
			Video video = new Video(title, videoType, priceCode, registeredDate) ;
			videos.add(video) ;
		}
	}

	public int showCommand() {
		Log.print("\nSelect a command !");
		Log.print("\t 0. Quit");
		Log.print("\t 1. List customers");
		Log.print("\t 2. List videos");
		Log.print("\t 3. Register customer");
		Log.print("\t 4. Register video");
		Log.print("\t 5. Rent video");
		Log.print("\t 6. Return video");
		Log.print("\t 7. Show customer report");
		Log.print("\t 8. Show customer and clear rentals");

		int command = scanner.nextInt() ;

		return command ;

	}

	private Customer getCustomer(String customerName) {
		for ( Customer customer: customers ) {
			if ( customer.getName().equals(customerName)) {
				return customer;
			}
		}
		return null;
	}
}
