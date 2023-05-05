package MyBooking.SearchTools;

import MyBooking.Booking.Reservation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;

public class SearchModeReservationsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void searchReservations1() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        Reservation reservation1 = new Reservation("Mike", "Lola", "1", new Calendar.Builder().setDate(2022,11,1).build(),new Calendar.Builder().setDate(2022,11,5).build(),false);
        reservation1.setAccommodationName("Hello Rooms");
        Reservation reservation2 = new Reservation("Max", "lola","2", new Calendar.Builder().setDate(2022,12,1).build(), new Calendar.Builder().setDate(2022,12,5).build(),false);
        reservation2.setAccommodationName("Good Morning");
        Reservation reservation3 = new Reservation("Elen", "Dave", "1", new Calendar.Builder().setDate(2022,10,1).build(), new Calendar.Builder().setDate(2022,10,10).build(),false);
        reservation3.setAccommodationName("Hello Rooms");
        Reservation reservation4 = new Reservation("Elen", "Lola", "3", new Calendar.Builder().setDate(2022,10,1).build(), new Calendar.Builder().setDate(2022,11,1).build(),false);
        reservation4.setAccommodationName("Welcome");

        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);
        reservationList.add(reservation4);

        reservationList = new SearchModeReservations().searchReservations(reservationList, "El", "Da", "He", "2022-09-30","2022-11-11");
        assertEquals(reservationList.size(),1);
    }

    @Test
    public void searchReservations2() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        Reservation reservation1 = new Reservation("Mike", "Lola", "1", new Calendar.Builder().setDate(2022,11,1).build(),new Calendar.Builder().setDate(2022,11,5).build(),false);
        reservation1.setAccommodationName("Hello Rooms");
        Reservation reservation2 = new Reservation("Max", "lola","2", new Calendar.Builder().setDate(2022,12,1).build(), new Calendar.Builder().setDate(2022,12,5).build(),false);
        reservation2.setAccommodationName("Good Morning");
        Reservation reservation3 = new Reservation("Elen", "Dave", "1", new Calendar.Builder().setDate(2022,10,1).build(), new Calendar.Builder().setDate(2022,10,10).build(),false);
        reservation3.setAccommodationName("Hello Rooms");
        Reservation reservation4 = new Reservation("Elen", "Lola", "3", new Calendar.Builder().setDate(2022,10,1).build(), new Calendar.Builder().setDate(2022,11,1).build(),false);
        reservation4.setAccommodationName("Welcome");

        reservationList.add(reservation1);
        reservationList.add(reservation2);
        reservationList.add(reservation3);
        reservationList.add(reservation4);

        reservationList = new SearchModeReservations().searchReservations(reservationList, "", "", "", "2022-12-30","2023-1-11");
        assertEquals(reservationList.size(),1);
    }

    @Test
    public void dateChecker() {
        assertTrue(new SearchModeReservations().dateChecker("2022-05-10"));
        assertFalse(new SearchModeReservations().dateChecker("2022|05|10"));
        assertFalse(new SearchModeReservations().dateChecker("a"));
        assertFalse(new SearchModeReservations().dateChecker("2022/05/10"));
        assertTrue(new SearchModeReservations().dateChecker("2021-05-01"));
        assertFalse(new SearchModeReservations().dateChecker("2021-05"));
        assertFalse(new SearchModeReservations().dateChecker("2021-05-a"));
    }


}