package MyBooking.Accomodation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class HotelTest {
    Hotel hotel;
    Hotel hotel1;

    @Before
    public void setUp() throws Exception {
        hotel = new Hotel("Paul's Hotel",74,30,true,false,false,false,"5");
        hotel1=new Hotel("Macedonia Palace",150,40,true,true,true,false,"6");

    }

    @After
    public void tearDown() throws Exception {
        Accomodation.code = 0;
    }


    @Test
    public void getName() {
        assertEquals("Paul's Hotel", hotel.getName());
        assertEquals("Macedonia Palace", hotel1.getName());
    }

    @Test
    public void getPrice() {
        assertEquals(74,hotel.getPrice());
        assertEquals(150,hotel1.getPrice());
    }

    @Test
    public void getMeter2() {
        assertEquals(30,hotel.getMeter2());
        assertEquals(40,hotel1.getMeter2());
    }

    @Test
    public void getWifi() {
        assertTrue(hotel.getWifi());
        assertTrue(hotel1.getWifi());
    }

    @Test
    public void getParking() {
        assertFalse(hotel.getParking());
        assertTrue(hotel1.getParking());
    }

    @Test
    public void getProvider() {
        assertEquals("5",hotel.getProvider());
        assertEquals("6",hotel1.getProvider());
    }

    @Test
    public void setPrice() {
        hotel.setPrice(93);
        assertEquals(93,hotel.getPrice());
        hotel1.setPrice(135);
        assertEquals(135,hotel1.getPrice());

    }

    @Test
    public void setMeter2() {
        hotel.setMeter2(39);
        assertEquals(39,hotel.getMeter2());
        hotel1.setMeter2(44);
        assertEquals(44,hotel1.getMeter2());
    }

    @Test
    public void setParking() {
        hotel.setParking(true);
        assertTrue(hotel.getParking());
        hotel1.setParking(false);
        assertFalse(hotel1.getParking());
    }

    @Test
    public void setBreakfast() {
        hotel.setBreakfast(true);
        assertTrue(hotel.getBreakfast());
        hotel1.setBreakfast(false);
        assertFalse(hotel1.getBreakfast());

    }

    @Test
    public void setName() {
        hotel.setName("Petro's");
        assertEquals("Petro's",hotel.getName());
        hotel1.setName("Nikopoli Hotel");
        assertEquals("Nikopoli Hotel",hotel1.getName());
    }

    @Test
    public void isDeleted() {
        hotel.setDeleted(true);
        assertTrue(hotel.deleted);
    }

    @Test
    public void setDeleted() {
        hotel.setDeleted(true);
        assertTrue(hotel.deleted);
    }
    @Test
    public void isLongTerm() {
        assertFalse(hotel.isLongTerm());
        assertFalse(hotel1.isLongTerm());
    }

    @Test
    public void setLongTerm() {
        hotel.setLongTerm(true);
        assertTrue(hotel.isLongTerm());
        hotel1.setLongTerm(true);
        assertTrue(hotel1.isLongTerm());
    }

    @Test
    public void accommodationsToString() {
        assertEquals("HOTEL|Paul's Hotel|74|30|true|false|false|false|false|5",hotel.accommodationsToString());
        assertEquals("HOTEL|Macedonia Palace|150|40|true|true|true|false|false|6",hotel1.accommodationsToString());
    }
}