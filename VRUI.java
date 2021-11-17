import java.util.List;
import java.util.Scanner;

public class VRUI {
	private static final Scanner scanner = new Scanner(System.in) ;

	private final VRPresenter presenter = new VRPresenter();

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
	public void clearRentals() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Customer foundCustomer = presenter.clearRentals(customerName);

		if ( foundCustomer == null ) {
			Log.print("No customer found") ;
		} else {
			// query , modifier
			Log.print("Name: " + foundCustomer.getName());
			foundCustomer.printRentals();
		}
	}

	public void returnVideo() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;
		Log.print("Enter video title to return: ") ;
		String videoTitle = scanner.next() ;

		presenter.returnVideo(videoTitle, videoTitle);
	}

	private void init() {
		presenter.init();
	}

	public void listVideos() {
		Log.print("List of videos");

		List<Video> videos = presenter.getVideos();
		for ( Video video: videos ) {
			Log.print("Price code: " + video.getPriceCode() +"\tTitle: " + video.getTitle()) ;
		}
		Log.print("End of list");
	}

	public void listCustomers() {
		Log.print("List of customers");
		List<Customer> customers = presenter.getCustomers();
		for ( Customer customer: customers ) {
			Log.print("Name: " + customer.getName());
			customer.printRentals();
		}
		Log.print("End of list");
	}

	public void getCustomerReport() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;

		String report = presenter.getCustomerReport(customerName) ;

		if ( report == null ) {
			Log.print("No customer found") ;
		} else {
			Log.print(report);
		}
	}

	public void rentVideo() {
		Log.print("Enter customer name: ") ;
		String customerName = scanner.next() ;

		Log.print("Enter video title to rent: ") ;
		String videoTitle = scanner.next() ;

		presenter.rentVideo(customerName, videoTitle);
	}


	public void register(String object) {
		if ( object.equals("customer") ) {
			Log.print("Enter customer name: ") ;
			String name = scanner.next();
			presenter.registerCustomer(name);
		}
		else {
			Log.print("Enter video title to register: ") ;
			String title = scanner.next() ;

			Log.print("Enter video type( 1 for VHD, 2 for CD, 3 for DVD ):") ;
			int videoType = scanner.nextInt();

			Log.print("Enter price code( 1 for Regular, 2 for New Release ):") ;
			int priceCode = scanner.nextInt();

			presenter.registerVideo(title, videoType, priceCode);
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
}
