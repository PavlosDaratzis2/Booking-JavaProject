package MyBooking.UserInterface;

import MyBooking.Accomodation.Accomodation;
import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Users.Client;
import MyBooking.Users.Provider;
import junit.framework.TestCase;
import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProviderInterfaceTest extends TestCase {
    DataLoader dataLoader;
    ProviderInterface providerInterface;
    Provider mike;

    @Before
    public void setUp() throws Exception {
        dataLoader = new DataLoader();
        mike = new Provider("Mike","1234");
        mike.setAddress("My Place");
        dataLoader.getUsersLogistics().setCurrentUser(mike);

        providerInterface = new ProviderInterface(dataLoader);
    }

    public void testGetData() {
        assertNotNull(providerInterface.bookingLogistics);
        assertNotNull(providerInterface.usersLogistics);
        assertNotNull(providerInterface.accommodationLogistics);
        assertNotNull(providerInterface.messagingLogistics);
    }

    public void testCurrentUserStringForLabel() {
        assertEquals(providerInterface.currentUserStringForLabel(), "Name: Mike Address: My Place");
    }

    public void testGetReservations() {
        ArrayList<Reservation> reservations = providerInterface.getReservations("Reservations");
        for(Reservation reservation : reservations){
            if(reservation.getCanceled()){
                fail("A cancellation among Reservations");
            }
        }
        reservations = providerInterface.getReservations("Cancellations");
        for(Reservation reservation : reservations){
            if(!reservation.getCanceled()){
                fail("A reservation among cancellations");
            }
        }
    }

    public void testGetAccommodations() {
        ArrayList<Accomodation> accomodations = providerInterface.accommodationLogistics.getAccommodationsProvider(mike.getId());
        ArrayList<Accomodation> accomodations1 = providerInterface.getAccommodations("All");
        assertEquals(accomodations1.size(), accomodations.size());
    }

    public void testSetDefaultListAndSearch() {
        ArrayList<Accomodation> accomodations = providerInterface.accommodationLogistics.getAccommodationsProvider(mike.getId());
        int size1 = accomodations.size();
        ArrayList<String> fields = new ArrayList<>();
        for( int i=0; i<7;i++){
            fields.add("");
        }
        fields.add(0,"Es");
        providerInterface.searchAccommodation(fields);
        assertTrue(providerInterface.getAccommodations("search").size()==size1);
        providerInterface.setDefaultList();
        assertTrue(providerInterface.getAccommodations("search").size()==size1);
    }

    public void testGetMessages() {
        assertEquals(providerInterface.getMessages().size(),0);
    }
}