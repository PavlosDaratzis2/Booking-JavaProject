package MyBooking.Users;

import MyBooking.DataLoader;

/**
 * An abstract class describing the main fields of the superClass Users. Common attributes of all users are
 * defined as Fields within. id the unique number of each user as Integer, loginName the login name of the user as String,
 * password the password of the user account as a String, address the address of the user as a String, pending whether
 * the user account is pending as Boolean and deleted whether the user account is deleted as Boolean.
 * Common methods for all users are defined within the superClass.
 * An id is given based on a static integer, it is unique for every user.
 * No constructor is present, because only the subclasses (Admin, Client, Provider) are concrete.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public abstract class  Users {
    protected static int code = 0;
    protected int id;
    protected String loginName, password, address;
    protected boolean pending, deleted;


    /**
     * A method to return the value of the field loginName.
     * @return the loginName Field as a String
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * An abstract method responsible for starting the User's Interface. For each type of user the appropriate Interface
     * is implemented.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public abstract void getInterface(DataLoader dataLoader);

    /**
     * A method to return the value of the password.
     * @return the password field as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * A method to return the value of the current user's ID
     * @return the value of the id as a String
     */
    public String getId(){return String.valueOf(id);}

    /**
     * A method that returns the state of the User. If the  user is pending for authorization from an Admin
     * or the User is accepted or not by an Admin. If the User is deleted, then the User is not pending.
     * @return true if the user is pending or false if the user is not pending
     */
    public boolean isPending() {
        return pending;
    }

    /**
     * A method to set the state of pending for the User.
     * @param pending boolean. Whether the user is pending(true) or not(false).
     */
    public void setPending(boolean pending) {
        this.pending = pending;
    }

    /**
     * A method that returns the state of the User. If the  user is deleted
     * or the User is not deleted by an Admin. If the User is pending, then the User is yet deleted.
     * @return true if the user is deleted or false if the user is not deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * A method to set the state of deleted for the User.
     * @param deleted true if the User is deleted and false if the User is not deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * An abstract method for the creation of a String needed for the implementation of the file management system.
     * @return a String composed of the fields needed for the file management of the Users
     */
    public abstract String fieldsAsString();

    /**
     * An abstract method to get the String to be used as a label with details of the user, such as Name, Address etc.
     * @return the String with all the user details
     */
    public abstract String getUserLabelString();

    /**
     * An abstract method that should be used for returning the address of the User
     * @return the address of the user as a String
     */
    public abstract String getAddress();

    /**
     * An abstract method that should be used for getting the type of the user's subclass
     * @return the type of the user
     */
    public abstract String getType();

}

