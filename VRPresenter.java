import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VRPresenter {

    // Customer List Video List
    private final List<Customer> customers = new ArrayList<>() ;

    private final List<Video> videos = new ArrayList<>() ;

    public Customer clearRentals(String customerName) {
        Customer foundCustomer = getCustomer(customerName) ;

        if ( foundCustomer != null ) {// query , modifier
            foundCustomer.clearRentals();
        }
        return foundCustomer;
    }

    public boolean returnVideo(String customerName, String videoTitle) {

        Customer foundCustomer = getCustomer(customerName) ;
        if ( foundCustomer == null ) return false;

        foundCustomer.returnVideo(videoTitle);
        return true;
    }

    void init() {
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

        james.doRental(v1); ;
        james.doRental(v2); ;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public String getCustomerReport(String customerName) {

        Customer foundCustomer = getCustomer(customerName) ;

        if ( foundCustomer != null ) {
            return foundCustomer.getReport();
        }
        return null;
    }

    public boolean rentVideo(String customerName, String videoTitle) {

        Customer foundCustomer = getCustomer(customerName) ;

        if ( foundCustomer == null ) return false;
        Video foundVideo = findVideoByTitle(videoTitle);
        if (foundVideo == null) {
            return false;
        }

        foundCustomer.doRental(foundVideo);
        return true;
    }

    private Video findVideoByTitle(String title){
        for ( Video video: videos ) {
            if ( video.getTitle().equals(title) && video.isRented() == false ) {
                return video;
            }
        }

        return null;
    }

    public void registerCustomer(String name) {
        customers.add(new Customer(name));
    }

    public void registerVideo(String title, int videoType, int priceCode) {
        Video video = new Video(title, videoType, priceCode, new Date()) ;
        videos.add(video) ;
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
