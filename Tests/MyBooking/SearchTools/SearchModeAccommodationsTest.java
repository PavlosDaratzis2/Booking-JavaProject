package MyBooking.SearchTools;

import MyBooking.Accomodation.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SearchModeAccommodationsTest {
    Hotel hotel, hotel1;
    Room room, room1;
    House house, house1;
    ArrayList<Accomodation> accommodations;

    @Before
    public void setUp() throws Exception {
        accommodations = new ArrayList<>();
        hotel = new Hotel("Mariana Hotel",77,25,true,false,true,false,"2");
        accommodations.add(hotel);
        hotel1 = new Hotel("GoodLord Palace",150,65,true,true,true,false,"1");
        accommodations.add(hotel1);
        room = new Room("No room",110,30,false,false,false,false,"2");
        accommodations.add(room);
        room1 = new Room("Welcome rooms",60,50,true,true,true,false,"3");
        accommodations.add(room1);
        house = new House("Terrible house",85,30,true,true,true,true,"2");
        accommodations.add(house);
        house1 = new House("Welcome house",100,110,true,true,true,true,"1");
        accommodations.add(house1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void search() {
        accommodations = new SearchModeAccommodations().search(accommodations,"house",100, 80, true, true, true, true);
        assertEquals(accommodations.size(),2);
        assertEquals(accommodations.get(0).getName(), "Terrible house");
        assertEquals(accommodations.get(1).getName(), "Welcome house");
    }
}