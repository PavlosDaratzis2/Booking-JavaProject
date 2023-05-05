package MyBooking.Users;

import MyBooking.DataLoader;
import MyBooking.UserInterface.ClientInterface;

/**
 * A concrete subclass of the superClass Users that creates a Client User.
 * It inherits all the fields(unique ID, Login Name and password) and methods of the superclass.
 * The methods are implemented within.
 * The Client also has the field address as the address of the Client.
 * The Client has a state of pending when first created through the sign-up process.
 * The Client can be deleted by an Admin.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class Client extends Users{
    protected String address;

    /**
     * The constructor of the Provider class.
     * It creates an object Provider user.
     * The unique ID is determined by getting the next integer of the static field code
     * @param name the Login Name of the Client as a String
     * @param password the Password of the Client as a String
     */
    public Client(String name, String password){
        super();
        this.id = ++code;
        this.loginName = name;
        this.password = password;
    }

    /**
     * A public method to create the Client's Interface.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public void getInterface(DataLoader dataLoader){
        new ClientInterface(dataLoader);
    }

    /**
     * A method to set the address of the Client.
     * It shall be used upon creation of the Client user.
     * @param address the address of the Client as String
     */
    public void setAddress(String address){
        this.address = address;
    }


    /**
     * A method to get the address of the Client.
     * @return the address of the Provider as String
     */
    public String getAddress() {
        return address;
    }


    /**
     * A method for getting the string needed for the implementation of the file managing class.
     * The string consists has the data for the type of user, the login name, the password, whether the user is deleted,
     * whether the user is pending and the address of the user.
     * @return the String that should be written in the text file.
     */
    public String fieldsAsString() {
        return "CLIENT"+"|" + loginName+"|"+password+"|"+deleted+"|"+pending+"|"+ address;
    }

    @Override
    public String getUserLabelString() {
        return "Name: "+this.getLoginName()+" Address: "+this.getAddress();
    }


    @Override
    public String getType(){
        return "Client";
    }
}
