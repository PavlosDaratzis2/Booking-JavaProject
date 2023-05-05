package MyBooking.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {
    Client client, client1;

    @Before
    public void setUp() throws Exception {
        client = new Client("Helen", "1111");
        client1 = new Client("George", "1345");
    }

    @After
    public void tearDown() throws Exception {
        Users.code = 0;
    }

    @Test
    public void getLoginName() {
        assertEquals("Helen", client.getLoginName());
        assertEquals("George", client1.getLoginName());
    }

    @Test
    public void getPassword() {
        assertEquals("1111", client.getPassword());
        assertEquals("1345", client1.getPassword());
    }

    @Test
    public void getId() {
        assertEquals("1", client.getId());
        assertEquals("2", client1.getId());
    }

    @Test
    public void isPending() {
        client.pending = true;
        assertTrue(client.isPending());
        client1.pending = false;
        assertFalse(client1.isPending());
    }

    @Test
    public void setPending() {
        client.setPending(true);
        assertTrue(client.pending);
        client1.setPending(false);
        assertFalse(client1.pending);
    }

    @Test
    public void isDeleted() {
        client.deleted = true;
        assertTrue(client.isDeleted());
        client1.deleted = false;
        assertFalse(client1.isDeleted());
    }

    @Test
    public void setDeleted() {
        client.setDeleted(true);
        assertTrue(client.deleted);
        client1.setDeleted(false);
        assertFalse(client1.deleted);
    }

    @Test
    public void setAddress() {
        client.setAddress("Around the block");
        assertEquals("Around the block", client.address);
        client1.setAddress("Karatasou 15");
        assertEquals("Karatasou 15", client1.address);
    }

    @Test
    public void getAddress() {
        client.address = "Around the block";
        assertEquals("Around the block", client.getAddress());
        client1.address = "Karatasou 15";
        assertEquals("Karatasou 15", client1.getAddress());
    }

    @Test
    public void fieldsAsString() {
        client.pending = true;
        client.deleted = false;
        client.address = "Around the block";
        assertEquals("CLIENT|Helen|1111|false|true|Around the block", client.fieldsAsString());
    }
}