package MyBooking.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProviderTest {
    Provider provider, provider1;

    @Before
    public void setUp() throws Exception {
        provider = new Provider("Mike", "1234");
        provider1 = new Provider("Joe", "4321");
    }

    @After
    public void tearDown() throws Exception {
        Users.code = 0;
    }

    @Test
    public void getLoginName() {
        assertEquals("Mike", provider.getLoginName());
        assertEquals("Joe", provider1.getLoginName());
    }

    @Test
    public void getPassword() {
        assertEquals("1234", provider.getPassword());
        assertEquals("4321", provider1.getPassword());
    }

    @Test
    public void getId() {
        assertEquals("1", provider.getId());
        assertEquals("2", provider1.getId());
    }

    @Test
    public void isPending() {
        provider.pending = true;
        assertTrue(provider.isPending());
        provider1.pending = false;
        assertFalse(provider1.isPending());
    }

    @Test
    public void setPending() {
        provider.setPending(true);
        assertTrue(provider.pending);
        provider1.setPending(false);
        assertFalse(provider1.pending);
    }

    @Test
    public void isDeleted() {
        provider.deleted = true;
        assertTrue(provider.isDeleted());
        provider1.deleted = false;
        assertFalse(provider1.isDeleted());
    }

    @Test
    public void setDeleted() {
        provider.setDeleted(true);
        assertTrue(provider.deleted);
        provider1.setDeleted(false);
        assertFalse(provider1.deleted);
    }

    @Test
    public void getAddress() {
        provider.address = "Egnatia 63, Thessaloniki";
        assertEquals("Egnatia 63, Thessaloniki", provider.getAddress());
        provider1.address = "Somewhere Else";
        assertEquals("Somewhere Else", provider1.getAddress());
    }

    @Test
    public void setAddress() {
        provider.setAddress("Egnatia 63, Thessaloniki");
        assertEquals("Egnatia 63, Thessaloniki", provider.address);
        provider1.setAddress("Somewhere Else");
        assertEquals("Somewhere Else", provider1.address);
    }

    @Test
    public void fieldsAsString() {
        provider.pending = true;
        provider.deleted = false;
        provider.address = "Egnatia 63, Thessaloniki";
        assertEquals("PROV|Mike|1234|false|true|Egnatia 63, Thessaloniki", provider.fieldsAsString());
    }
}