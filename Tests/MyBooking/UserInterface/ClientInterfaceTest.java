package MyBooking.UserInterface;

import MyBooking.Accomodation.Accomodation;
import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Users.Admin;
import MyBooking.Users.Client;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ClientInterfaceTest {
    DataLoader dataLoader;
    ClientInterface clientInterface;

    @Before
    public void setUp() throws Exception {
        dataLoader = new DataLoader();
        Client mike = new Client("Mike","1234");
        mike.setAddress("My Place");
        dataLoader.getUsersLogistics().setCurrentUser(mike);

        clientInterface = new ClientInterface(dataLoader);
    }

    @Test
    public void getData() {
        assertNotNull(clientInterface.bookingLogistics);
        assertNotNull(clientInterface.usersLogistics);
        assertNotNull(clientInterface.accommodationLogistics);
        assertNotNull(clientInterface.messagingLogistics);
    }

    @Test
    public void getReservations() {
        ArrayList<Reservation> reservations = clientInterface.getReservations("Reservations");
        for(Reservation reservation : reservations){
            if(reservation.getCanceled()){
                fail("A cancellation among Reservations");
            }
        }
        reservations = clientInterface.getReservations("Cancellations");
        for(Reservation reservation : reservations){
            if(!reservation.getCanceled()){
                fail("A reservation among cancellations");
            }
        }
    }

    @Test
    public void getMessages() {
        assertEquals(clientInterface.getMessages().size(),0);
    }

    @Test
    public void currentUserStringForLabel() {
        assertEquals(clientInterface.currentUserStringForLabel(), "Name: Mike Address: My Place");
    }

    @Test
    public void getAccommodations() {
        ArrayList<Accomodation> accomodations = clientInterface.accommodationLogistics.getAccommodations();
        ArrayList<Accomodation> accomodations1 = clientInterface.getAccommodations("All");
        assertEquals(accomodations1.size(), accomodations.size());
    }

    @Test
    public void longTerm() {
        String id = "";
        ArrayList<Accomodation> accomodations = clientInterface.accommodationLogistics.getAccommodations();
        for(Accomodation accomodation : accomodations){
            if(accomodation.isLongTerm()){
                id = accomodation.getId();
            }
        }
        assertTrue(clientInterface.longTerm(id));
    }

    @Test
    public void setDefaultListAndSearch() {
        ArrayList<Accomodation> accomodations = clientInterface.accommodationLogistics.getAccommodations();
        int size1 = accomodations.size();
        ArrayList<String> fields = new ArrayList<>();
        for( int i=0; i<7;i++){
            fields.add("");
        }
        fields.add(0,"Es");
        clientInterface.searchAccommodation(fields);
        assertFalse(clientInterface.getAccommodations("search").size()==size1);
        clientInterface.setDefaultList();
        assertTrue(clientInterface.getAccommodations("search").size()==size1);
    }
}