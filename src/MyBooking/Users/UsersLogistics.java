package MyBooking.Users;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * UsersLogistics is a class in charge of the management of the Users used in the application.
 * All users are stored inside an ArrayList which is  set as a field of the class.
 * A Scanner class is used for reading the necessary input from the User Interface.
 * Inside the class a record of the current User of the App is stored as a field.
 * A HashMap is used to store the name of every user for convenience.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class UsersLogistics {
    private final ArrayList<Users> users;
    private Users currentUser;
    private final HashMap<String, String> namesId;

    /**
     * The Constructor of the UsersLogistics class.
     * The ArrayList of the users is initiated.
     * The HashMap of the Users and their names is initiated
     */
    public UsersLogistics() {
        users = new ArrayList<>();
        namesId = new HashMap<>();
    }

    /**
     * A public method for creating a new Client User and adding him/her to the ArrayList of all Users.
     *
     * @param name     the Login Name of the Client as String
     * @param password the Password of the Client as String
     * @param address  the Address of the Client as String
     * @param deleted  Whether the Client is deleted(true) or not (false)
     * @param pending  Whether the Client is pending (true) or not (false)
     */
    public void createNewClient(String name, String password, String address, boolean deleted, boolean pending) {
        Client client = new Client(name, password);
        client.setAddress(address);
        client.setPending(pending);
        client.setDeleted(deleted);
        users.add(client);
        namesId.put(client.getId(), client.getLoginName());
    }

    /**
     * A public method for creating a new Provider User and adding him/her to the ArrayList of all Users.
     *
     * @param name     the Login Name of the Provider as String
     * @param password the Password of the Provider as String
     * @param address  the Address of the Provider as String
     * @param deleted  Whether the Provider is deleted(true) or not (false)
     * @param pending  Whether the Provider is pending (true) or not (false)
     */
    public void createNewProvider(String name, String password, String address, boolean deleted, boolean pending) {
        Provider provider = new Provider(name, password);
        provider.setAddress(address);
        provider.setPending(pending);
        provider.setDeleted(deleted);
        users.add(provider);
        namesId.put(provider.getId(), provider.getLoginName());
    }

    /**
     * A public method for creating a new Admin User and adding him/her to the ArrayList of all Users.
     * All admins are initiated without address and pending and deleted are set to false.
     *
     * @param name     the Login Name of the Provider as String
     * @param password the Password of the Provider as String
     */
    public void createNewAdmin(String name, String password) {
        Admin admin = new Admin(name, password);
        admin.setDeleted(false);
        admin.setPending(false);
        users.add(admin);
    }

    /**
     * A public method get the list with all the active users.
     * Active Users are all Users that are not deleted nor pending.
     *
     * @return the ArrayList containing all active users
     */
    public ArrayList<Users> getActiveUsers() {
        return users.stream().filter(users1 -> (!users1.isPending() & !users1.isDeleted())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to get the list with all the pending users.
     * Pending Users are all the Users that are not yet accepted by an Admin
     *
     * @return the ArrayList with users currently pending
     */
    public ArrayList<Users> getPendingUsers() {
        return users.stream().filter(users1 -> users1.isPending() && !users1.isDeleted()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to get the list of the Users without the Admin user
     *
     * @return the Arraylist of Users without the Admin user
     */
    public ArrayList<Users> getActiveUsersNoAdmin() {
        return getActiveUsers().stream().filter(users1 -> !(users1 instanceof Admin)).collect(Collectors.toCollection(ArrayList::new));
    }


    /**
     * A method to get the name of the User with a specified ID
     *
     * @param id the ID of the User
     * @return the login name of the User
     */
    public String getNameFromId(String id) {
        return namesId.get(id);
    }


    /**
     * A method to set the field of the current User in charge of the App. It shall be used after a successful login
     * process
     *
     * @param currentUser the current User of the App after a successful Login
     */
    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * A method that returns the current User of the App.
     *
     * @return the current User of the App as Users
     */
    public Users getCurrentUser() {
        return this.currentUser;
    }

    /**
     * A method to get all the Users currently registered in the App. Active, pending or deleted.
     *
     * @return ArrayList of all the Users registered in the App
     */
    public ArrayList<Users> getUsers() {
        return users;
    }

    /**
     * A method to get all the Active Users registered in the App. Only Active. Pending or deleted Users are excluded
     * @return ArrayList of all the active Users registered.
     */
    public ArrayList<Users> getActiveUsersArray() {
        return users.stream().filter(users1 -> (!users1.isPending() & !users1.isDeleted())).collect(Collectors.toCollection(ArrayList::new));
    }
}


