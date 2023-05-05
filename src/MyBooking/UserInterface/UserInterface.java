package MyBooking.UserInterface;

import MyBooking.Accomodation.AccommodationLogistics;
import MyBooking.Accomodation.Accomodation;
import MyBooking.Authenticate.Authenticate;
import MyBooking.Booking.BookingListings;
import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Messaging.MessagingLogistics;
import MyBooking.Users.Users;
import MyBooking.Users.UsersLogistics;
import java.util.ArrayList;

/**
 * An abstract class defining the common fields of every kind of user interface. Its implementation
 * is loosely based on the Factory pattern.
 * UserInterface class initiates all the data needed for the current implementation of the Interface
 * for each type of user.
 * One abstract method is defined concerning the user view at startup for every type of user.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 **/
public abstract class UserInterface {
    protected BookingListings bookingLogistics;
    protected MessagingLogistics messagingLogistics;
    protected AccommodationLogistics accommodationLogistics;
    protected Users currentUser;
    protected ArrayList<Users> users;
    protected UsersLogistics usersLogistics;
    protected DataLoader dataLoader;
    protected ArrayList<Reservation> reservations;
    protected ArrayList<Accomodation> accommodations;

    /**
     * The common constructor for all subclasses. When initiated the dataLoader that contains all the needed data is
     * set to the variable used throughout the program.
     * @param dataLoader The DataLoader object that contains all the program's data.
     */
    public UserInterface(DataLoader dataLoader){this.dataLoader = dataLoader;}

    /**
     * A method to set up the data needed for implementation from the source DataLoader. Common for every type of User.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    protected void getData(DataLoader dataLoader){
        this.messagingLogistics = dataLoader.getMessagingLogistics();
        this.bookingLogistics = dataLoader.getBookingListings();
        this.accommodationLogistics = dataLoader.getAccommodations();
        this.usersLogistics = dataLoader.getUsersLogistics();
    }

    /**
     * A common for all subClasses method that logs the user from the user interface. It starts the Authenticate class
     * to start from the beginning of the program
     */
    public void logOut(){new Authenticate(dataLoader);}

    /**
     * An abstract method that implements the search of the reservations
     * @param providerName the name of the Provider of the Accommodation
     * @param clientName the Client name that made the reservation
     * @param accommodationName the name of the Accommodation
     * @param checkIn the oldest check in date
     * @param checkOut the oldest check out date
     */
    public abstract void searchReservations(String providerName, String clientName, String accommodationName, String checkIn, String checkOut);


}
