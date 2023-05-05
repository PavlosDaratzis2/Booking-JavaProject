package MyBooking.Booking;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookingListingsTest {
    BookingListings bookingListings;
    Calendar CheckIn;
    Calendar CheckOut;
    Calendar CheckIn2;
    Calendar CheckOut2;
    Calendar CheckIn3;
    Calendar CheckOut3;

    @Before
    public void setUp() throws Exception {
        bookingListings=new BookingListings();
        CheckIn=new Calendar.Builder().setDate(2022, 12-1, 19-1).build();
        CheckOut=new Calendar.Builder().setDate(2022, 12-1, 25-1).build();
        CheckIn2=new Calendar.Builder().setDate(2022, 9-1, 19-1).build();
        CheckOut2=new Calendar.Builder().setDate(2022, 9-1, 25-1).build();
        CheckIn3=new Calendar.Builder().setDate(2022, 7-1, 12-1).build();
        CheckOut3=new Calendar.Builder().setDate(2022, 7-1, 19-1).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createNewReservation() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        assertEquals(1,bookingListings.getReservations().size());
        bookingListings.createNewReservation("pavlos","petros","6",CheckIn2,CheckOut2,false,"macedonia");
        assertEquals(2,bookingListings.getReservations().size());
    }

    @Test
    public void cancelReservation() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("pavlos","petros","6",CheckIn2,CheckOut2,false,"macedonia");
        bookingListings.cancelReservation(bookingListings.getReservations().get(0).getId());
        assertEquals(1,bookingListings.getReservations().size());

    }

    @Test
    public void getReservations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("pavlos","petros","6",CheckIn2,CheckOut2,true,"macedonia");
        assertEquals(1,bookingListings.getReservations().size());
    }

    @Test
    public void getClientReservations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("pavlos","kostas","6",CheckIn2,CheckOut2,false,"macedonia");
        assertEquals(1,bookingListings.getClientReservations("kostas").size());
    }

    @Test
    public void getClientCancellations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("pavlos","kostas","6",CheckIn2,CheckOut2,true,"macedonia");
        bookingListings.createNewReservation("pavlos","kostas","9",CheckIn3,CheckOut3,false,"nikopoli");
        assertEquals(1,bookingListings.getClientCancellations("kostas").size());

    }

    @Test
    public void getProviderReservation() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("filipos","kostas","6",CheckIn2,CheckOut2,false,"electra");
        assertEquals(1,bookingListings.getProviderReservation("pavlos").size());
    }

    @Test
    public void getProviderCancellations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,false,"macedonia");
        bookingListings.createNewReservation("filipos","kostas","6",CheckIn2,CheckOut2,true,"electra");
        bookingListings.createNewReservation("pavlos","kostas","9",CheckIn3,CheckOut3,true,"macedonia");
        assertEquals(1,bookingListings.getProviderCancellations("pavlos").size());


    }

    @Test
    public void getCancelations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,true,"macedonia");
        bookingListings.createNewReservation("nikos","kostas","6",CheckIn2,CheckOut2,false,"electra");
        bookingListings.createNewReservation("petros","kostas","9",CheckIn3,CheckOut3,true,"nikopoli");
        assertEquals(2,bookingListings.getCancelations().size());

    }

    @Test
    public void getAllReservations() {
        bookingListings.createNewReservation("pavlos","petros","5",CheckIn,CheckOut,true,"macedonia");
        bookingListings.createNewReservation("nikos","kostas","6",CheckIn2,CheckOut2,false,"electra");
        bookingListings.createNewReservation("petros","kostas","9",CheckIn3,CheckOut3,true,"nikopoli");
        assertEquals(3,bookingListings.getAllReservations().size());

    }
}