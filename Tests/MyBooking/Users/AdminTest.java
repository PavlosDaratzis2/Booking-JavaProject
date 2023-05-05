package MyBooking.Users;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminTest {
    Admin admin, admin1;

    @Before
    public void setUp() throws Exception {
        admin = new Admin("Kostas", "1111");
        admin1 = new Admin("Mike", "5555");
    }

    @After
    public void tearDown() throws Exception {
        Users.code = 0;
    }

    @Test
    public void getLoginName() {

        assertEquals("Kostas", admin.getLoginName());
    }

    @Test
    public void getPassword() {

        assertEquals("1111", admin.getPassword());
    }

    @Test
    public void getId() {
        assertEquals("1", admin.getId());
        assertEquals("2", admin1.getId());
    }

    @Test
    public void isPending() {
        assertFalse(admin.isPending());
    }

    @Test
    public void setPending() {
        admin.setPending(true);
        assertTrue(admin.pending);
    }

    @Test
    public void isDeleted() {
        assertFalse(admin.isDeleted());
    }

    @Test
    public void setDeleted() {
        admin.setDeleted(true);
        assertTrue(admin.deleted);
    }

    @Test
    public void fieldsAsString() {
        assertEquals("ADMIN|Kostas|1111|false|false", admin.fieldsAsString());
        assertEquals("ADMIN|Mike|5555|false|false", admin1.fieldsAsString());
    }
}