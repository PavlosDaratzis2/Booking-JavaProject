package MyBooking.Booking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class ReservationTest {
    Calendar Checkin;
    Calendar Checkout;
    Reservation reservation;
    @Before
    public void setUp() throws Exception {
        Checkin=new Calendar.Builder().setDate(2022, 12-1, 19-1).build();
        Checkout=new Calendar.Builder().setDate(2022, 12-1, 25-1).build();
      reservation=new Reservation("petros","nikos","2",Checkin,Checkout,false);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setAccommodationName() {
        reservation.setAccommodationName("macedonia");
        assertEquals("macedonia",reservation.getAccommodationName());

    }

    @Test
    public void getCanceled() {
        assertFalse(reservation.getCanceled());
    }

    @Test
    public void setCanceled() {
        reservation.setCanceled(true);
        assertTrue(reservation.getCanceled());
    }

    @Test
    public void booked() {
        assertFalse(reservation.booked(Checkin, Checkout));
    }

    @Test
    public void getProviderName() {
        assertEquals("petros",reservation.getProviderName());
    }

    @Test
    public void getClientName() {
        assertEquals("nikos",reservation.getClientName());
    }


    @Test
    public void getAccomodationId() {
        assertEquals("2",reservation.getAccomodationId());
    }

    @Test
    public void getCheckIn() {
        assertEquals(Checkin,reservation.getCheckIn());
    }

    @Test
    public void getCheckOutPrint() {
        Calendar c=reservation.getCheckOutPrint();
        assertEquals(c,reservation.getCheckOutPrint());
    }

    @Test
    public void reservationToString() {
        reservation.setAccommodationName("macedonia");
        assertEquals("petros|nikos|2|"+Checkin.getTimeInMillis()+"|"+Checkout.getTimeInMillis()+"|"+"false|macedonia",reservation.reservationToString());
    }

    @Test
    public void getDateFromCalendar() {
        assertEquals("2022-12-18",reservation.getDateFromCalendar(Checkin));
    }

    @Test
    public void days() {
      assertEquals((Checkout.getTimeInMillis()-Checkin.getTimeInMillis())/(24*60*60*1000),reservation.days());
    }
}