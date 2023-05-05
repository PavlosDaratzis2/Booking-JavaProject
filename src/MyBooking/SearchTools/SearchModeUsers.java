package MyBooking.SearchTools;

import MyBooking.Users.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * A class that is responsible for the implementation of the searches needed to the ArrayList of Users. Search by:
 * Username and Address  Client name who booked the Accommodation,
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class SearchModeUsers {

    /**
     * A private method that filters the ArrayList of Users to those that their name contains the String parameter (name)
     * @param name the name of the User
     * @param users the ArrayList of Users to be searched
     * @return the filtered ArrayList of Users
     */
    public ArrayList<Users> searchName(String name, ArrayList<Users> users){
        return users.stream().filter(user -> user.getLoginName().contains(name)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Users to those that their address contains the String parameter (address)     *
     * @param address the address of the user
     * @param users the ArrayList of Users to be searched
     * @return the filtered ArrayList of Users
     */
    public ArrayList<Users> searchAddress(String address, ArrayList<Users> users){
        return users.stream().filter(user -> user.getAddress().contains(address)).collect(Collectors.toCollection(ArrayList::new));
    }




}
