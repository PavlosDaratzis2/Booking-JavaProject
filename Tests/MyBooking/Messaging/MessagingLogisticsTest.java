package MyBooking.Messaging;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessagingLogisticsTest {
    MessagingLogistics messagingLogistics;
    Message message;


    @Before
    public void setUp() throws Exception {
        messagingLogistics=new MessagingLogistics();
        message=new Message("2","8","good morning");

    }


    @Test
    public void sendMessage() {
        messagingLogistics.sendMessage("4","7","hello",false);
        messagingLogistics.sendMessage("5","8","good night",false);
        assertEquals(2,messagingLogistics.getMessages().size());
    }

    @Test
    public void addMessage() {
        messagingLogistics.addMessage(message);
        assertEquals(1,messagingLogistics.getMessages().size());

    }

    @Test
    public void getMessagesAdmin() {
        messagingLogistics.sendMessage("2","7","hello",false);
        messagingLogistics.sendMessage("9","8","good night",false);
        messagingLogistics.sendMessage("2","7","good morning",false);
        assertEquals(2,messagingLogistics.getMessagesAdmin("2").size());
    }

    @Test
    public void getMessagesOtherUser() {
        messagingLogistics.sendMessage("2","6","hello",false);
        messagingLogistics.sendMessage("9","6","good night",false);
        messagingLogistics.sendMessage("4","9","good morning",false);
        assertEquals(2,messagingLogistics.getMessagesOtherUser("6").size());

    }

    @Test
    public void deleteMessage() {
        messagingLogistics.sendMessage("2","7","hello",false);
        messagingLogistics.sendMessage("9","8","good night",false);
        messagingLogistics.sendMessage("2","7","good morning",false);
        messagingLogistics.deleteMessage(messagingLogistics.getMessages().get(0).getId());
        assertEquals(2,messagingLogistics.getMessages().stream().filter(message1 -> !message1.isDeleted()).count());

    }

    @Test
    public void getMessages() {
        messagingLogistics.sendMessage("2","7","hello",false);
        messagingLogistics.sendMessage("9","8","good night",false);
        messagingLogistics.sendMessage("2","7","good morning",false);
        assertEquals(3,messagingLogistics.getMessages().size());

    }
}