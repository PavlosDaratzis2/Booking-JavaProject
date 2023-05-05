package MyBooking.UserInterface;

import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Graphics.AdminView;
import MyBooking.Messaging.Message;
import MyBooking.SearchTools.SearchModeReservations;
import MyBooking.SearchTools.SearchModeUsers;
import MyBooking.Users.Users;
import java.util.ArrayList;

/**
 * AdminInterface class implements the the methods needed for the action Listeners of the Admin User in AdminView.
 * It is initiated using the Factory Pattern. Admin User can view all the Reservations currently stored, view all Users
 * of the program, check Pending Users and get and send Messages to other Users.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class AdminInterface extends UserInterface {
    private final AdminView adminview;


    /**
     * The constructor of the Admin Class. Sets up all the needed data from the DataLoader object. The current user variable
     * is set and the two ArrayLists of Reservations and Users are set. Also, the AdminView object is initiated.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public AdminInterface(DataLoader dataLoader){
        super(dataLoader);
        getData(dataLoader);
        this.currentUser = usersLogistics.getCurrentUser();
        this.users = usersLogistics.getUsers();
        this.reservations = bookingLogistics.getReservations();

        adminview = new AdminView(this);
    }

    /**
     * A method to get the String with all the user's information in order to make the label of the bar
     * @return the String containing this user's information
     */
    public String currentUserStringForLabel(){
        return currentUser.getUserLabelString();
    }

    /**
     * A method to get the appropriate ArrayList of Users needed. Types: Active (all active users including Admin)
     * Pending (All currently pending users), NoAdmin (All active users without the Admin), search (the result of the
     * search)
     * @param type the type of the list needed String
     * @return the ArrayList of Users needed
     */
    public ArrayList<Users> getUsers(String type){
        return switch (type) {
            case "Active" -> usersLogistics.getActiveUsers();
            case "Pending" -> usersLogistics.getPendingUsers();
            case "NoAdmin" -> usersLogistics.getActiveUsersNoAdmin();
            case "search" -> users;
            default -> usersLogistics.getActiveUsers();
        };
    }

    /**
     * A method to get the ArrayList of Messages this admin has sent
     * @return the ArrayList of Messages this admin has sent
     */
    public ArrayList<Message> getMessages(){
        return messagingLogistics.getMessagesAdmin(currentUser.getId());
    }

    /**
     * A method that implements the search procedure to the ArrayList of the Users. The criteria used are the
     * name of the User and the address of the User
     * @param name the name of the user
     * @param address the address of the user
     */
    public void searchUsers(String name, String address){
        users = usersLogistics.getActiveUsers();
        if(!name.isEmpty()){
            users = new SearchModeUsers().searchName(name, users);
        }
        if (!address.isEmpty()){
            users = new SearchModeUsers().searchAddress(address,users);
        }

    }

    /**
     * A method to get the appropriate ArrayList of reservations. Type: Reservations (all the reservations) Cancellations
     * (all the cancellations), search (the result of the search)
     * @param type the type of list to get
     * @return the appropriate ArrayList of Reservation
     */
    public ArrayList<Reservation> getReservations2(String type){
        return switch (type) {
            case "Reservations" -> bookingLogistics.getReservations();
            case "Cancellations" -> bookingLogistics.getCancelations();
            case "search" -> reservations;
            default -> bookingLogistics.getReservations();
        };
    }

    /**
     * A method to make the search of all the reservations registered in the program.
     * @param providerName      the name of the Provider of the Accommodation
     * @param clientName        the Client name that made the reservation
     * @param accommodationName the name of the Accommodation
     * @param checkIn           the oldest check in date
     * @param checkOut          the oldest check out date
     */
    public void searchReservations(String providerName, String clientName, String accommodationName, String checkIn, String checkOut){
        if(!new SearchModeReservations().dateChecker(checkIn) || !new SearchModeReservations().dateChecker(checkOut)){
            adminview.optionsDialog("Date");
            //return;
        }else{
            reservations = bookingLogistics.getReservations();
            reservations = new SearchModeReservations().searchReservations(reservations, providerName, clientName,accommodationName,checkIn,checkOut);
        }
        //adminview.updateReservationsViewPanel(new ReservationsViewBuilder().getReservationsScrollPane(reservations));
    }

    /**
     * A method to accept a pending user by its ID
     * @param id the ID of the user to accept
     */
    public void acceptPendingUser(String id){
        usersLogistics.getPendingUsers().stream().filter(user -> user.getId().equals(id)).forEach(user -> user.setPending(false));
        //adminview.updateUserViewPanel(new UsersViewBuilder().getUsersScrollPane(usersLogistics.getPendingUsers()));
    }

    /**
     * A method to decline a pending user by its ID
     * @param id the ID of the user to decline
     */
    public void deletePendingUser(String id){
        usersLogistics.getPendingUsers().stream().filter(user -> user.getId().equals(id)).forEach(user -> user.setDeleted(true));
        //adminview.updateUserViewPanel(new UsersViewBuilder().getUsersScrollPane(usersLogistics.getPendingUsers()));
    }

    /**
     * A method that checks if there are any pending users
     * @return true if there are pending users false if there are no more pending users
     */
    public boolean noPendingUsers(){
        return usersLogistics.getPendingUsers().size()==0;
    }

    /**
     * A method to send a message to a User. If the User can not be found or the Text message contains the symbol | the
     * method returns false. If the message is sent the method returns true.
     * @param userId the ID of the user to send the message as String
     * @param message the message to be sent as a String
     * @return true if the message could not be sent false if the message is sent.
     */
    public boolean sendMessage(String userId, String message){
        if(usersLogistics.getActiveUsers().stream().noneMatch(user -> user.getId().equals(userId))){
            adminview.optionsDialog("User");
            return false;
            //System.out.println("No such User");
            //return;
        }
        else if(message.contains("|")){
            adminview.optionsDialog("Symbol");
            System.out.println("The symbol | is not allowed");
            return false;
            //return;
        }
        else {
            messagingLogistics.sendMessage(currentUser.getId(),userId, message, false);
            return true;
        }
    }
}