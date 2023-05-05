package MyBooking.Accomodation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {
    Room room;
    Room room1;

    @Before
    public void setUp() throws Exception {
        room = new Room("Paul's room",74,33,true,false,true,true,"5");
        room1 = new Room("Macedonia rooms",120,20,true,true,true,false,"2");
    }

    @After
    public void tearDown() throws Exception {
        Room.code=0;
    }


    @Test
    public void getName() {
        assertEquals("Paul's room", room.getName());
        assertEquals("Macedonia rooms", room1.getName());
    }

    @Test
    public void getId() {
        assertEquals("1", room.getId());
        assertEquals("2", room1.getId());
    }

    @Test
    public void getPrice() {
        assertEquals(74, room.getPrice());
        assertEquals(120, room1.getPrice());
    }

    @Test
    public void getMeter2() {
        assertEquals(33, room.getMeter2());
        assertEquals(20, room1.getMeter2());
    }

    @Test
    public void getWifi() {
        assertTrue(room.getWifi());
        assertTrue(room1.getWifi());
    }

    @Test
    public void getParking() {
        assertFalse(room.getParking());
        assertTrue(room1.getParking());
    }

    @Test
    public void getBreakfast() {
        assertTrue(room.getBreakfast());
        assertTrue(room1.getBreakfast());
    }

    @Test
    public void getProvider() {
        assertEquals("5", room.getProvider());
        assertEquals("2", room1.getProvider());
    }

    @Test
    public void setPrice() {
        room.setPrice(80);
        room1.setPrice(100);
        assertEquals(80,room.getPrice());
        assertEquals(100,room1.getPrice());

    }

    @Test
    public void setMeter2() {
        room.setMeter2(40);
        room1.setMeter2(50);
        assertEquals(40,room.getMeter2());
        assertEquals(50,room1.getMeter2());
    }

    @Test
    public void setWifi() {
        room.setWifi(false);
        assertFalse(room.getWifi());
        room1.setWifi(false);
        assertFalse(room1.getWifi());
    }

    @Test
    public void setParking() {
        room.setParking(true);
        room1.setParking(false);
        assertTrue(room.getParking());
        assertFalse(room1.getParking());
    }

    @Test
    public void setBreakfast() {
        room.setBreakfast(false);
        room1.setBreakfast(false);
        assertFalse(room.getBreakfast());
        assertFalse(room1.getBreakfast());
    }

    @Test
    public void setName() {
        room.setName("Pavlos room");
        room1.setName("family room");
        assertEquals("Pavlos room",room.getName());
        assertEquals("family room",room1.getName());
    }

    @Test
    public void isDeleted() {
        room.setDeleted(true);
        assertTrue(room.deleted);
        room1.setDeleted(true);
        assertTrue(room1.deleted);
    }

    @Test
    public void setDeleted() {
        room.setDeleted(true);
        assertTrue(room.deleted);
        room1.setDeleted(true);
        assertTrue(room1.deleted);
    }

    @Test
    public void isLongTerm() {
        assertTrue(room.isLongTerm());
        assertFalse(room1.isLongTerm());
    }

    @Test
    public void setLongTerm() {
        room.setLongTerm(false);
        room1.setLongTerm(true);
        assertFalse(room.isLongTerm());
        assertTrue(room1.isLongTerm());
    }

    @Test
    public void accommodationsToString() {
        assertEquals("ROOM|Paul's room|74|33|true|false|true|true|false|5",room.accommodationsToString());
        assertEquals("ROOM|Macedonia rooms|120|20|true|true|true|false|false|2",room1.accommodationsToString());
    }
}