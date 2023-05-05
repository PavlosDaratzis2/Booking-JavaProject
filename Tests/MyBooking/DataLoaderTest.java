package MyBooking;

import MyBooking.Accomodation.AccommodationLogistics;
import MyBooking.Booking.BookingListings;
import MyBooking.Messaging.MessagingLogistics;
import MyBooking.Users.UsersLogistics;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class DataLoaderTest {
    DataLoader dataLoader;
    @Before
    public void setUp() throws Exception {
        dataLoader = new DataLoader();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getUsersLogistics() {
        UsersLogistics users = dataLoader.getUsersLogistics();
        assertEquals(users.getUsers().get(0).getLoginName(),"Kostas");
    }

    @Test
    public void getAccommodations() {
        AccommodationLogistics accommodationLogistics = dataLoader.getAccommodations();
        assertEquals(accommodationLogistics.getAllAccommodations().get(0).getName(),"Hotel Esperia");
    }

    @Test
    public void getBookingListings() {
        BookingListings listings = dataLoader.getBookingListings();
        assertEquals(listings.getAllReservations().get(0).getAccommodationName(),"Hotel Esperia");
    }

    @Test
    public void getMessagingLogistics() {
        MessagingLogistics messagingLogistics = dataLoader.getMessagingLogistics();
        assertEquals(messagingLogistics.getMessages().get(0).getMessage(),"Hello");
    }
}
