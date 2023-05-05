package MyBooking.UserInterface;

import MyBooking.Accomodation.Accomodation;
import MyBooking.Booking.Reservation;
import MyBooking.DataLoader;
import MyBooking.Graphics.ProviderView;
import MyBooking.Messaging.Message;
import MyBooking.SearchTools.SearchModeAccommodations;
import MyBooking.SearchTools.SearchModeReservations;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * ProviderInterface class implements the methods needed for the action Listeners of the Provider User in ProviderView.
 * It is initiated using the Factory Pattern. Provider User can view all the Accommodations belonginig to him currently
 * stored, search these Accommodations with preset criteria, edit an existing Accommodation, add a new Accommodation,
 * delete an existing Accommodation, View the Reservations of his Accommodations, Get Statistics for his Reservations
 * (Total earning from Reservations) and get Messages from Admin Users.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class ProviderInterface extends UserInterface {
    private final ProviderView providerView;

    /**
     * The constructor of the Admin Class. Sets up all the needed data from the DataLoader object. The current user variable
     * is set and the two ArrayLists of Reservations and Accommodations are set. Also, the ProviderView object is initiated.
     * @param dataLoader the DataLoader object that contains all information for the implementation of the Interface
     */
    public ProviderInterface(DataLoader dataLoader) {
        super(dataLoader);
        getData(dataLoader);
        this.currentUser = usersLogistics.getCurrentUser();
        this.reservations = bookingLogistics.getProviderCancellations(currentUser.getLoginName());
        this.accommodations = accommodationLogistics.getAccommodationsProvider(currentUser.getId());
        providerView = new ProviderView(this);
    }

    /**
     * A method to get the String with all the user's information in order to make the label of the bar
     * @return the String containing this user's information
     */
    public String currentUserStringForLabel(){
        return currentUser.getUserLabelString();
    }

    /**
     * A method to get the appropriate ArrayList of reservations. Type: Reservations (Provider's Accommodations reservations)
     * Cancellations (Provider's Accommodations cancellations), search (the result of the search)
     * @param type the type of list to get
     * @return the appropriate ArrayList of Reservation
     */
    public ArrayList<Reservation> getReservations(String type){
        return switch (type) {
            case "Reservations" -> bookingLogistics.getProviderReservation(currentUser.getLoginName());
            case "Cancellations" -> bookingLogistics.getProviderCancellations(currentUser.getLoginName());
            case "search" -> reservations;
            default -> bookingLogistics.getProviderReservation(currentUser.getLoginName());
        };
    }

    /**
     * A method to make the search of the reservations of the Accommodations belonging to the Provider
     * @param providerName      the name of the Provider of the Accommodation
     * @param clientName        the Client name that made the reservation
     * @param accommodationName the name of the Accommodation
     * @param checkIn           the oldest check in date
     * @param checkOut          the oldest check out date
     */
    public void searchReservations(String providerName, String clientName, String accommodationName, String checkIn, String checkOut){
        if(!new SearchModeReservations().dateChecker(checkIn) || !new SearchModeReservations().dateChecker(checkOut)){
            providerView.optionsDialog("DateInvalid");
        }else{
            reservations = bookingLogistics.getProviderReservation(currentUser.getLoginName());
            reservations = new SearchModeReservations().searchReservations(reservations, providerName, clientName,accommodationName,checkIn,checkOut);
        }

    }

    /**
     * A method to get the appropriate ArrayList of Accommodations. Type: All (All Provider's Accommodations) search
     * (the result of the search)
     * @param type the type of list to get
     * @return the appropriate ArrayList of Accommodation
     */
    public ArrayList<Accomodation> getAccommodations(String type){
        return switch (type) {
            case "All" -> accommodationLogistics.getAccommodationsProvider(currentUser.getId());
            case "search" -> accommodations;
            default -> accommodationLogistics.getAccommodationsProvider(currentUser.getId());
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
                    providerView.optionsDialog("PriceBounds");
                    return;
                }
            } catch (NumberFormatException e) {
                providerView.optionsDialog("PriceInt");
            }
        }

        if(!searchFields.get(2).isEmpty()) {
            try {
                minPrice = Integer.parseInt(searchFields.get(2));
                if(minPrice<0){
                    providerView.optionsDialog("PriceBounds");
                    return;
                }
            }catch (NumberFormatException e) {
                providerView.optionsDialog("PriceInt");
            }
        }

        if (searchFields.get(3).equals("true")) longterm = true;
        if (searchFields.get(4).equals("true")) wifi = true;
        if (searchFields.get(5).equals("true")) parking = true;
        if (searchFields.get(6).equals("true")) breakfast = true;
        this.accommodations = accommodationLogistics.getAccommodationsProvider(currentUser.getId());
        this.accommodations = new SearchModeAccommodations().search(accommodations,searchFields.get(0), maxPrice, minPrice, longterm, wifi, parking, breakfast);
    }

    /**
     * A method that resets the ArrayList of Accommodations to the stored values after it is filtered through a search
     */
    public void setDefaultList(){
        accommodations = accommodationLogistics.getAccommodations().stream().filter(accomodation -> accomodation.getProvider().equals(currentUser.getLoginName())).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to create a new Accommodation for this Provider.
     * @param type the type of Accommodation (Hotel, House, Room)
     * @param name the name of the Accommodation
     * @param price the cost of the Accommodation for the period (Long term Month, Short Term Day)
     * @param meter2 the square meters of the Accommodation
     * @param wifi true if Wi-Fi is provided
     * @param parking true if parking is provided
     * @param breakfast true if breakfast is provided
     * @param longTerm true if the Accommodation is available for Long Term Lease, false for short term
     */
    public void createNewAccommodations(String type, String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longTerm){
        switch (type) {
            case "Hotel" -> accommodationLogistics.createNewHotel(name, price, meter2, wifi, parking, breakfast, longTerm, false, currentUser.getId());
            case "Room" -> accommodationLogistics.createNewRoom(name, price, meter2, wifi, parking, breakfast, longTerm, false, currentUser.getId());
            case "House" -> accommodationLogistics.createNewHouse(name, price, meter2, wifi, parking, breakfast, longTerm, false, currentUser.getId());
            default -> System.out.println("Something went wrong with the switch type accommodation");
        }
    }

    /**
     * A method to get the ID of the last Accommodation in the Accommodation ArrayList
     * @return the ID of the Accommodation as a String
     */
    public String getIdFromLast(){
        return getAccommodations("All").get(getAccommodations("All").size()-1).getId();
    }

    /**
     * A method to edit an existing Accommodation
     * @param accommodationId the ID of the Accommodation
     * @param name the name of the Accommodation
     * @param price the cost of the Accommodation for the period (Long term Month, Short Term Day)
     * @param meter2 the square meters of the Accommodation
     * @param wifi true if Wi-Fi is provided
     * @param parking true if parking is provided
     * @param breakfast true if breakfast is provided
     * @param longterm true if the Accommodation is available for Long Term Lease, false for short term
     */
    public void editAccommodation(String accommodationId, String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longterm){
        //accommodationLogistics.getAccommodations().stream().filter(a -> a.getId().equals(accommodationId)).collect(Collectors.toCollection(ArrayList::new)).get(0).
        getAccommodation(accommodationId).setName(name);
        getAccommodation(accommodationId).setPrice(price);
        getAccommodation(accommodationId).setMeter2(meter2);
        getAccommodation(accommodationId).setWifi(wifi);
        getAccommodation(accommodationId).setParking(parking);
        getAccommodation(accommodationId).setBreakfast(breakfast);
        getAccommodation(accommodationId).setLongTerm(longterm);
    }

    /**
     * A method to get the Accommodation object from the ID of this Accommodation
     * @param accId the ID of the Accommodation
     * @return the Accommodation Object
     */
    public Accomodation getAccommodation(String accId){
        return accommodationLogistics.getAccommodation(accId);
    }

    /**
     * A method to get the Messages that are sent to this Client
     * @return the Messages as an ArrayList
     */
    public ArrayList<Message> getMessages(){
        return messagingLogistics.getMessagesOtherUser(currentUser.getId());
    }

    /**
     * A method that calculates the total earnings of the Provider through current Resrvations
     * @return the total Earnings of the Provider as a String
     */
    public String getStatistics(){
        int sum = 0;
        for(Reservation reservation :bookingLogistics.getProviderReservation(this.currentUser.getLoginName())){
            Accomodation temp = accommodationLogistics.getAccommodation(reservation.getAccomodationId());
            if(temp.isLongTerm()){
                sum+= temp.getPrice()*((int) (reservation.days()/32)+1);
            }
            else{
                sum += temp.getPrice() * reservation.days();
            }

        }
        return String.valueOf(sum);
    }
}