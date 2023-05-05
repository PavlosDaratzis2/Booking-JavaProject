package MyBooking.Users;


import MyBooking.DataLoader;
import MyBooking.UserInterface.AdminInterface;

/**
 * A concrete subclass of the superClass Users that creates an Admin User.
 * It inherits all the fields(unique ID, Login Name and password) and methods of the superclass.
 * The methods are implemented within.
 * The user does have the field address in contrast to the other types of Users.
 * Because the user Admin is never at a state of pending or deleted shall be always set to false.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class Admin extends Users{

    /**
     * The constructor of the Admin class.
     * It creates an object Admin user.
     * The unique ID is determined by getting the next integer of the static field code.
     * @param name the Login Name of the Admin as a String
     * @param password the password of the user as a String
     */
    public Admin(String name, String password){
        super();
        this.id = ++code;
        this.loginName = name;
        this.password = password;
        this.pending = false;
        this.deleted = false;
    }

    /**
     * A public method to create the Admin's Interface.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public void getInterface(DataLoader dataLoader){
        new AdminInterface(dataLoader);
    }

    /**
     * A method for getting the string needed for the implementation of the file managing class.
     * The string consists has the data for the type of user, the login name the password, whether the user is deleted
     * and whether the user is pending
     * @return the String that should be written in the text file.
     */
    public String fieldsAsString() {
        return "ADMIN"+"|" + loginName+"|"+password+"|"+deleted+"|"+pending;
    }

    @Override
    public String getUserLabelString() {
        return "Name: "+this.getLoginName();
    }

    @Override
    public String getAddress() {
        return "";
    }

    @Override
    public String getType() {
        return "Admin";
    }
}
