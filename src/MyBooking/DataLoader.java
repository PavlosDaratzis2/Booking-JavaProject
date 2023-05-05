package MyBooking;

import MyBooking.Accomodation.*;
import MyBooking.Booking.*;
import MyBooking.Messaging.*;
import MyBooking.Users.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 * The class DataLoader is responsible for the file management system of the App. It is based on 4 text files that hold
 * the data for the proper operation of the App(Users, Accommodations, Messages and Reservations). All data is created
 * using the public methods of the respective Logistics class e.g. (Users -> UsersLogistics) and the data is stored inside
 * each class' list.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class DataLoader {
    private final UsersLogistics usersLogistics;
    private final AccommodationLogistics accommodationLogistics;
    private final BookingListings bookingListings;
    private final MessagingLogistics messagingLogistics;

    /**
     * The constructor of the class. A new UserLogistics is created and then a method is used to read the data from the
     * text file "TextFiles/UsersFile.txt" and creates the Users stored in the text file. A new accommodationLogistics object is
     * created and then a method is used to read the data from the text file "TextFiles/AccommodationsFile.txt" and creates the
     * Accommodations stored in the text file.A new bookingListings object is created and then a method is used to read
     * the data from the text file "TextFiles/ReservationsFile.txt" and creates the Reservations stored in the text file.
     * A new MessagingLogistics object is created and then a method is used to read the data from the text file
     * "TextFiles/MessagesFile.txt" and creates the Messages stored in the text file.
     * @throws IOException If the file is not found an exception is thrown.
     */
    public DataLoader() throws IOException {
        usersLogistics = new UsersLogistics();
        readUsersFromFile();
        accommodationLogistics = new AccommodationLogistics();
        readAccommodationsFromFile();
        messagingLogistics = new MessagingLogistics();
        readMessagesFromFile();
        bookingListings = new BookingListings();
        readReservationsFromFile();
    }

    /**
     * A method to get the UsersLogistics object created by the constructor with all the needed data.
     * @return the UsersLogistics object
     */
    public UsersLogistics getUsersLogistics() {return usersLogistics;}

    /**A method to get the AccommodationLogistics object created by the constructor with all the needed data.
     * @return the AccommodationLogistics object
     */
    public AccommodationLogistics getAccommodations(){return accommodationLogistics;}

    /**A method to get the MessagingLogistics object created by the constructor with all the needed data.
     * @return the MessagingLogistics Object
     */
    public MessagingLogistics getMessagingLogistics(){return messagingLogistics;}

    /**
     * A method to get the BookingListings object created by the constructor with all the needed data.
     * @return the MessagingLogistics Object
     */
    public BookingListings getBookingListings() {return bookingListings;}

    /**
     * A private method that reads the Accommodation Data from a specified file and creates the respective objects
     * through the accommodationLogistics object
     * @throws FileNotFoundException If the file is not found an exception is thrown.
     */
    private void readAccommodationsFromFile() throws FileNotFoundException {
        File file = new File("TextFiles/AccommodationsFile.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String input = scanner.nextLine();

            String[] items = input.split("\\|");

            switch (items[0]) {
                case "HOTEL" -> accommodationLogistics.createNewHotel(items[1],Integer.parseInt(items[2]),Integer.parseInt(items[3]),items[4].equals("true"),items[5].equals("true"),items[6].equals("true"),items[7].equals("true"),items[8].equals("true"),items[9]);
                case "ROOM" -> accommodationLogistics.createNewRoom(items[1],Integer.parseInt(items[2]),Integer.parseInt(items[3]),items[4].equals("true"),items[5].equals("true"),items[6].equals("true"),items[7].equals("true"),items[8].equals("true"),items[9]);
                case "HOUSE" -> accommodationLogistics.createNewHouse(items[1],Integer.parseInt(items[2]),Integer.parseInt(items[3]),items[4].equals("true"),items[5].equals("true"),items[6].equals("true"),items[7].equals("true"),items[8].equals("true"),items[9]);
            }
        }
    }

    /**
     * A private method that reads the Users' Data from a specified file and creates the respective objects through the
     * usersLogistics object.
     * @throws IOException If the file specified is not found an exception is thrown
     */
    private void readUsersFromFile() throws IOException {
        File file = new File("TextFiles/UsersFile.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){

            String input = scanner.nextLine();

            String[] items = input.split("\\|");

            switch (items[0]) {
                case "ADMIN" -> usersLogistics.createNewAdmin(items[1], items[2]);
                case "PROV" -> usersLogistics.createNewProvider(items[1], items[2], items[5], items[3].equals("true"), items[4].equals("true"));
                case "CLIENT" -> usersLogistics.createNewClient(items[1], items[2], items[5], items[3].equals("true"), items[4].equals("true"));
            }
        }
    }

    /**
     * A private method that reads the Messages' Data from a specified file and creates the respective objects through the
     * messagingLogistics object.
     * @throws FileNotFoundException If the file specified is not found an exception is thrown
     */
    private void readMessagesFromFile() throws FileNotFoundException {


        File file = new File("TextFiles/MessagesFile.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {

            String input = scanner.nextLine();

            String[] items = input.split("\\|");


            messagingLogistics.sendMessage(items[0], items[1], items[2], Boolean.getBoolean(items[3]));

        }
    }

    /**
     * A private method that reads the Reservations' Data from a specified file and creates the respective objects through the
     * bookingListings object.
     * @throws FileNotFoundException If the file specified is not found an exception is thrown
     */
    private void readReservationsFromFile() throws FileNotFoundException {
        File file = new File("TextFiles/ReservationsFile.txt");
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()) {

            String input = scanner.nextLine();

            String[] items = input.split("\\|");
            Calendar checkIn = Calendar.getInstance();
            checkIn.setTimeInMillis(Long.parseLong(items[3]));
            Calendar checkOut = Calendar.getInstance();
            checkOut.setTimeInMillis(Long.parseLong(items[4]));
            bookingListings.createNewReservation(items[0], items[1],items[2],checkIn,checkOut, Boolean.parseBoolean(items[5]),items[6]);
        }
    }

    /**
     * A private method that writes the Users of the App to the UsersFile.txt file.
     * @throws IOException If the text file is not found an exception is thrown.
     */
    private void writeUsersToFile() throws IOException {
        int count = 0;
        boolean append = false;

        do{
            writeToFile("TextFiles/UsersFile.txt", usersLogistics.getUsers().get(count).fieldsAsString(), append);
            count++;
            append = true;
        }while(count<usersLogistics.getUsers().size());

    }

    /**
     * A private method that writes the Accommodations of the App to the AccommodationsFile.txt file.
     * @throws IOException If the text file is not found an exception is thrown.
     */
    private void writeAccommodationsToFile() throws IOException {
        int count = 0;
        boolean append = false;
        ArrayList<Accomodation> accomodations = accommodationLogistics.getAllAccommodations();

        do{
            writeToFile("TextFiles/AccommodationsFile.txt", accomodations.get(count).accommodationsToString(), append);
            count++;
            append = true;
        }while(count < accomodations.size());

    }

    /**
     *  private method that writes the Messages of the App to the MessagesFile.txt file.
     * @throws IOException If the text file is not found an exception is thrown.
     */
    private void writeMessagesToFile() throws IOException {
        int count = 0;
        boolean append = false;

        ArrayList<Message> messages = messagingLogistics.getMessages();

        do{writeToFile("TextFiles/MessagesFile.txt", messages.get(count).messageToString(), append);
            count++;
            append = true;

        }while (count < messages.size());
    }

    /**
     * private method that writes the Reservations of the App to the ReservationsFile.txt file.
     * @throws IOException If the text file is not found an exception is thrown.
     */
    private void writeReservationsToFile() throws IOException {
        int count = 0;
        boolean append = false;

        ArrayList<Reservation> reservations = bookingListings.getAllReservations();

        do{
            writeToFile("TextFiles/ReservationsFile.txt", reservations.get(count).reservationToString(), append);
            count++;
            append = true;
        }while (count < reservations.size());
    }

    /**
     * A private method responsible for writing a string to a specified text file.
     * @param filename the name of the text file
     * @param text the String that shall be written inside the text file
     * @param append whether to create a new file with the current String (false) or to append the string inside the
     *               text file
     * @throws IOException If the text file is not found an exception is thrown.
     */
    private void writeToFile(String filename, String text, boolean append) throws IOException {
        File file = new File(filename);

        FileWriter fw = new FileWriter(file, append);

        PrintWriter pw = new PrintWriter(fw);

        pw.println(text);

        pw.close();
    }

    /**
     * A public method to write all data to the respective text file e.g.(Users -> UsersLogistics)
     * @throws IOException If the file is not found an exception is thrown.
     */
    public void writeAllToFile() throws IOException {
        writeUsersToFile();
        writeAccommodationsToFile();
        writeReservationsToFile();
        writeMessagesToFile();
    }

}
