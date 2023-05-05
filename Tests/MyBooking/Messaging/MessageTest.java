package MyBooking.Messaging;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    Message  message2;

    @Before
    public void setUp() throws Exception {
        message2=new Message("5","8","good morning");
    }


    @Test
    public void getAdminId() {
        assertEquals("5",message2.getAdminId());
    }

    @Test
    public void getOtherUserId() {
        assertEquals("8",message2.getOtherUserId());
    }

    @Test
    public void getMessage() {
        assertEquals("good morning",message2.getMessage());
    }


    @Test
    public void isDeleted() {
        assertFalse(message2.isDeleted());
    }

    @Test
    public void setDeleted() {
        message2.setDeleted(true);
        assertTrue(message2.isDeleted());
    }

    @Test
    public void messageToString() {
        assertEquals("5|8|good morning|false", message2.messageToString());
    }
}