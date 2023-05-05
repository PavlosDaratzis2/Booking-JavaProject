package MyBooking;

import MyBooking.Authenticate.Authenticate;
import javax.swing.*;
import java.io.IOException;
/**
 * This is the main class that starts the Awesome Booking App. The App is capable of sustaining as today three types of
 * Users (Admin, Provider, Client), three types of Accommodation (Hotel, Room, House), a full booking management system
 * and a messaging service. A search engine system is installed in order for the right kind of user to use it for Users,
 * Accommodations or Reservations.
 * An authentication procedure is used for logging in the App or signing up. After Signing up an Admin must verify the
 * user in order to use the App.
 * A file management system is also installed in order to keep all data stored for future use of the App.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class Main {
    /**
     * The main method that start the App. All is needed is a new object of Authenticate and within a new object of
     * DataLoader is initiated. The System Look and Feel is used.
     * @param args The command line arguments.
     * @throws IOException The DataLoader object throws the exception when the needed file is not found
     */
    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            System.out.println("Sorry your System Look & Feel is not supported");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Update your JDK");
        }
        catch (InstantiationException e) {
            System.out.println("Something went wrong with the instantiation of the Look An Feel");
        }
        catch (IllegalAccessException e) {
            System.out.println("Permission needed for the implementation of the System Look & Feel");
        }
        new Authenticate(new DataLoader());
    }
}
