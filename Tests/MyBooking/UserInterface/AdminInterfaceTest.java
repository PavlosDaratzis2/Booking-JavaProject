package MyBooking.UserInterface;

import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Users.Admin;
import MyBooking.Users.Users;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminInterfaceTest {
    DataLoader dataLoader;
    AdminInterface adminInterface;

    @Before
    public void setUp() throws Exception {
        dataLoader = new DataLoader();
        dataLoader.getUsersLogistics().setCurrentUser(new Admin("Mike","1234"));
        adminInterface = new AdminInterface(dataLoader);

    }

    @Test
    public void getData() {
        assertNotNull(adminInterface.bookingLogistics);
        assertNotNull(adminInterface.usersLogistics);
        assertNotNull(adminInterface.accommodationLogistics);
        assertNotNull(adminInterface.messagingLogistics);
    }

    @Test
    public void currentUserStringForLabel() {
        assertEquals(adminInterface.currentUserStringForLabel(), "Name: Mike");
    }

    @Test
    public void getUsers() {
        ArrayList<Users> users = adminInterface.getUsers("Active");
        for (Users user : users){
            if (user.isDeleted() || user.isPending()){
                fail("Not all users active");
            }
        }
        users = adminInterface.getUsers("Pending");
        for (Users user : users){
            if (!user.isPending()){
                fail("Not all users Pending");
            }
        }
        users = adminInterface.getUsers("NoAdmin");
        for (Users user : users){
            if (user instanceof Admin){
                fail("An Admin among us");
            }
        }
    }

    @Test
    public void getSendMessages() {
        ArrayList<Users> users = adminInterface.getUsers("Active");
        String randomUserId = users.get(2).getId();
        adminInterface.sendMessage(randomUserId, "hello");
        if(adminInterface.getMessages().size()==0){
            fail("Message ArrayList empty message not send");
        }
    }

    @Test
    public void getReservations2() {
        ArrayList<Reservation> reservations = adminInterface.getReservations2("Reservations");
        for(Reservation reservation : reservations){
            if(reservation.getCanceled()){
                fail("A cancellation among Reservations");
            }
        }
        reservations = adminInterface.getReservations2("Cancellations");
        for(Reservation reservation : reservations){
            if(!reservation.getCanceled()){
                fail("A reservation among cancellations");
            }
        }
    }

    @Test
    public void noPendingUsers() {
        ArrayList<Users> users = adminInterface.getUsers("Pending");
        if(users.isEmpty()){
            assertTrue(adminInterface.noPendingUsers());
        }
        else{
            assertFalse(adminInterface.noPendingUsers());
        }
    }

    @Test
    public void acceptDeleteUser(){
        ArrayList<Users> users = adminInterface.getUsers("Active");
        Users user1 = users.get(2);
        Users user2 = users.get(3);
        user1.setPending(true);
        user2.setPending(true);
        adminInterface.acceptPendingUser(user1.getId());
        adminInterface.deletePendingUser(user2.getId());
        if(user1.isPending()){
            fail();
        }
        if(user2.isDeleted()){
            return;
        }

    }
}