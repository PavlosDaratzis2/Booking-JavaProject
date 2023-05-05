package MyBooking.Accomodation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTest {
    House house;
    House house1;

    @Before
    public void setUp() throws Exception {
        house = new House("Paul's house",74,30,true,false,true,false,"1");
        house1 = new House("family house",60,50,true,false,true,false,"2");
    }

    @After
    public void tearDown() throws Exception {
        House.code=0;
    }


    @Test
    public void getName() {
        assertEquals("Paul's house",house.getName());
        assertEquals("family house",house1.getName());
    }

    @Test
    public void getId() {
        assertEquals("1",house.getProvider());
        assertEquals("2",house1.getProvider());
    }

    @Test
    public void getPrice() {
        assertEquals(74,house.getPrice());
        assertEquals(60,house1.getPrice());
    }

    @Test
    public void getMeter2() {
        assertEquals(30,house.getMeter2());
        assertEquals(50,house1.getMeter2());
    }

    @Test
    public void getWifi() {
        assertTrue(house.getWifi());
        assertTrue(house1.getWifi());
    }

    @Test
    public void getParking() {
        assertFalse(house.getParking());
        assertFalse(house1.getParking());
    }

    @Test
    public void getBreakfast() {
        assertTrue(house.breakfast);
        assertTrue(house1.breakfast);
    }

    @Test
    public void getProvider() {
        assertEquals("1",house.getProvider());
        assertEquals("2",house1.getProvider());
    }

    @Test
    public void setPrice() {
        house.setPrice(80);
        assertEquals(80,house.getPrice());
        house1.setPrice(100);
        assertEquals(100,house1.getPrice());
    }

    @Test
    public void setMeter2() {
        house.setMeter2(35);
        assertEquals(35,house.getMeter2());
        house1.setMeter2(55);
        assertEquals(55,house1.getMeter2());
    }

    @Test
    public void setWifi() {
        house.setWifi(false);
        house1.setWifi(false);
        assertFalse(house.getWifi());
        assertFalse(house1.getWifi());
    }

    @Test
    public void setParking() {
        house.setParking(true);
        house1.setParking(true);
        assertTrue(house.getParking());
        assertTrue(house1.getParking());

    }

    @Test
    public void setBreakfast() {
        house.setBreakfast(false);
        house1.setBreakfast(false);
        assertFalse(house.getBreakfast());
        assertFalse(house1.getBreakfast());
    }

    @Test
    public void setName() {
        house.setName("Pavlos house");
        assertEquals("Pavlos house",house.getName());
        house1.setName("Family house");
        assertEquals("Family house",house1.getName());
    }

    @Test
    public void isDeleted() {
        house.setDeleted(true);
        assertTrue(house.deleted);
    }

    @Test
    public void setDeleted() {
        house.setDeleted(true);
        assertTrue(house.deleted);
    }

    @Test
    public void isLongTerm() {
        assertFalse(house.isLongTerm());
        assertFalse(house1.isLongTerm());
    }

    @Test
    public void setLongTerm() {
        house.setLongTerm(true);
        house1.setLongTerm(true);
        assertTrue(house.isLongTerm());
        assertTrue(house1.isLongTerm());
    }

    @Test
    public void accommodationsToString() {
        assertEquals("HOUSE|Paul's house|74|30|true|false|true|false|false|1",house.accommodationsToString());
        assertEquals("HOUSE|family house|60|50|true|false|true|false|false|2",house1.accommodationsToString());
    }
}