package MyBooking.UserInterface;

import MyBooking.Accomodation.Accomodation;
import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Graphics.ClientView;
import MyBooking.Messaging.Message;
import MyBooking.SearchTools.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

/**
 * ClientInterface class implements the methods needed for the action Listeners of the Client User in ClientView.
 * It is initiated using the Factory Pattern. Client User can view all the Accommodations currently stored, search these
 * Accommodations with preset criteria, book an Accommodation, View and cancel this User's Reservations and get Messages
 * from Admin Users.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class ClientInterface extends UserInterface {
    private final ClientView clientView;

    /**
     * The constructor of the Admin Class. Sets up all the needed data from the DataLoader object. The current user variable
     * is set and the two ArrayLists of Reservations and Accommodations are set. Also, the ClientView object is initiated.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public ClientInterface(DataLoader dataLoader) {
        super(dataLoader);
        getData(dataLoader);
        this.currentUser = usersLogistics.getCurrentUser();
        accommodations = accommodationLogistics.getAccommodations();
        reservations = bookingLogistics.getClientReservations(currentUser.getLoginName());

        clientView = new ClientView(this);
    }

    /**
     * A method to get the appropriate ArrayList of reservations. Type: Reservations (Client's reservations) Cancellations
     * (Client's cancellations), search (the result of the search)
     * @param type the type of list to get
     * @return the appropriate ArrayList of Reservation
     */
    public ArrayList<Reservation> getReservations(String type){
        return switch (type) {
            case "Reservations" -> bookingLogistics.getClientReservations(currentUser.getLoginName());
            case "Cancellations" -> bookingLogistics.getClientCancellations(currentUser.getLoginName());
            case "search" -> reservations;
            default -> bookingLogistics.getClientReservations(currentUser.getLoginName());
        };
    }


    /**
     * A method to make the search of all the reservations this client made. The final result is stored in the ArrayList
     * reservations. Search criteria are: Provider Name, Client Name, Accommodation Name, Reservations after a date,
     * Reservations before a date.
     * @param providerName      the name of the Provider of the Accommodation
     * @param clientName        the Client name that made the reservation
     * @param accommodationName the name of the Accommodation
     * @param checkIn           the oldest check in date
     * @param checkOut          the oldest check out date
     */
    public void searchReservations(String providerName, String clientName, String accommodationName, String checkIn, String checkOut){
        if(!new SearchModeReservations().dateChecker(checkIn) || !new SearchModeReservations().dateChecker(checkOut)){
            clientView.optionsDialog("DateBounds");
            //return;
        }else{
            reservations = bookingLogistics.getClientReservations(currentUser.getLoginName());
            reservations = new SearchModeReservations().searchReservations(reservations, providerName, clientName,accommodationName,checkIn,checkOut);
        }
    }

    /**
     * A method to book an Accommodation for a time period. After all inputs are checked for errors the booking process
     * is complete
     * @param accomodationId The Accommodation's ID that is chosen to book
     * @param checkInString The date of Check In as a String
     * @param checkOut The date of Check Out as a String
     * @return true if the process is completed successfully false if there were problems with the booking process.
     */
    public boolean makeReservation(String accomodationId, String checkInString, String checkOut){
        if(checkInString.isEmpty() || checkInString.equals("yyyy-mm-dd")){
            clientView.optionsDialog("DateBounds");
            return false;
        }
        if(checkOut.isEmpty()){
            clientView.optionsDialog("DateBounds");
            return false;
        }
        String[] date1 = checkInString.split("\\-");

        if(date1.length<3){
            clientView.optionsDialog("DateInvalid");
            return false;
        }

        Calendar checkInCal;
        Calendar checkOutCal;

        ArrayList<Accomodation> temp = accommodationLogistics.getAccommodations().stream().filter(accomodation -> accomodation.getId().equals(accomodationId)).collect(Collectors.toCollection(ArrayList::new));
        Accomodation selected = temp.get(0);

        int year, month, day;
        try {
            year = Integer.parseInt(date1[0]);

            month = Integer.parseInt(date1[1]);

            day = Integer.parseInt(date1[2]);
        }catch (NumberFormatException e){
            clientView.optionsDialog("ValueInt");
            return false;
        }
        if( month <1 | month >12 | year > 2023 | day < 1 | day >31){
            clientView.optionsDialog("DateBounds");
            return false;
        }
        checkInCal = new Calendar.Builder().setDate(year, month-1, day).build();
        if (checkInCal.before(Calendar.getInstance())){
            clientView.optionsDialog("DateInvalid");
            return false;
        }
        checkOutCal = new Calendar.Builder().setDate(year, month-1, day-1).build();
        if(temp.get(0).isLongTerm()){
            int months;
            try{
                months = Integer.parseInt(checkOut);
            }catch (NumberFormatException e){
                clientView.optionsDialog("PriceInt");
                return false;
            }

            checkOutCal.add(Calendar.MONTH, months);
        }
        else {
            //System.out.println("How many nights");
            int nights;
            try{
                nights = Integer.parseInt(checkOut);
            } catch(NumberFormatException e){
                clientView.optionsDialog("PriceInt");
                return false;
            }
            checkOutCal.add(Calendar.DAY_OF_MONTH, nights);
        }
        if (checkInCal.after(checkOutCal)){
            clientView.optionsDialog("DateInvalid");
        }
//        long difference = checkOut.getTimeInMillis() - checkIn.getTimeInMillis();
//        int days = (int) difference/(1000*60*60*24);
        String prvName = usersLogistics.getNameFromId(selected.getProvider());
//        String provName = usersLogistics.getUsers().stream().filter(user -> user.getId().equals(temp.get(0).getProvider())).collect(Collectors.toCollection(ArrayList::new)).get(0).getLoginName();
        if(bookingLogistics.createNewReservation(prvName, currentUser.getLoginName(), selected.getId(), checkInCal, checkOutCal, false, selected.getName())){
            clientView.optionsDialog("Confirmed");
            return true;
        }
        else{
            clientView.optionsDialog("PlaceBooked");
            return false;
        }
    }

    /**
     * A method to cancel a Client's Reservation based on the ID of the Reservation
     * @param id The ID of the Reservation to be canceled.
     */
    public void makeCancellation(String id){
        bookingLogistics.cancelReservation(id);
    }

    /**
     * A method to get the Messages that are sent to this Client
     * @return the Messages as an ArrayList
     */
    public ArrayList<Message> getMessages(){
        return messagingLogistics.getMessagesOtherUser(currentUser.getId());
    }

    /**
     * A method to get the String with all the user's information in order to make the label of the bar
     * @return the String containing this user's information
     */
    public String currentUserStringForLabel(){
        return currentUser.getUserLabelString();
    }

    /**
     * A method to get the appropriate ArrayList of Accommodations. Type: All (All Accommodations) search
     * (the result of the search)
     * @param type the type of list to get
     * @return the appropriate ArrayList of Accommodation
     */
    public ArrayList<Accomodation> getAccommodations(String type){
        return switch (type) {
            case "All" -> accommodationLogistics.getAccommodations();
            case "search" -> accommodations;
            default -> accommodationLogistics.getAccommodations();
        };
    }

    /**
     * The search method of the Accommodations. The imput of the method should be an Array of Strings corresponding to
     * the following: 0. Name, 1. MaxPrice, 2. MinPrice, 3. LongTerm, 4. Wifi, 5. Parking, 6. Breakfast
     * @param searchFields The criteria for the search as an ArrayList of Strings
     */
    public void searchAccommodation(ArrayList<String> searchFields){
        int maxPrice = 0, minPrice = 0;
        boolean longterm = false, wifi = false, parking = false, breakfast = false;

        if (!searchFields.get(1).isEmpty()) {
            try {
                maxPrice = Integer.parseInt(searchFields.get(1));
                if (maxPrice<0){
                    clientView.optionsDialog("PriceBounds");
                    return;
                }
            } catch (NumberFormatException e) {
                clientView.optionsDialog("PriceInt");
            }
        }

        if(!searchFields.get(2).isEmpty()) {
            try {
                minPrice = Integer.parseInt(searchFields.get(2));
                if(minPrice<0){
                    clientView.optionsDialog("PriceBounds");
                    return;
                }
            }catch (NumberFormatException e) {
                clientView.optionsDialog("PriceInt");
            }
        }

        if (searchFields.get(3).equals("true")) longterm = true;
        if (searchFields.get(4).equals("true")) wifi = true;
        if (searchFields.get(5).equals("true")) parking = true;
        if (searchFields.get(6).equals("true")) breakfast = true;
        this.accommodations = accommodationLogistics.getAccommodations();
        this.accommodations = new SearchModeAccommodations().search(accommodations,searchFields.get(0), maxPrice, minPrice, longterm, wifi, parking, breakfast);

    }

    /**
     * A method that returns whether the specific Accommodation is available for Long Term or Short Term Lease
     * @param id the ID of the Accommodation
     * @return true if is available for Long Term Lease, false if is available for Short Term Lease.
     */
    public boolean longTerm(String id){
        return accommodationLogistics.getLongTermForAccommodation(id);
    }

    /**
     * A method that resets the ArrayList of Accommodations to the stored values after it is filtered through a search
     */
    public void setDefaultList(){
        accommodations = accommodationLogistics.getAccommodations();
    }


}