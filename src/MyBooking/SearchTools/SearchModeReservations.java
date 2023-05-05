package MyBooking.SearchTools;

import MyBooking.Booking.Reservation;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;
/**
 * A class that is responsible for the implementation of the searches needed to the ArrayList of Reservations. Search by:
 * Provider name of the Accommodation, Client name who booked the Accommodation, the name of the Accommodation, reservations after
 * a date, reservations before a date. A method is used to check the dates validity.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class SearchModeReservations {

    /**
     * The main method that implements the proper filtering to an ArrayList of Reservation according
     * to the parameters given. The implementation is partially based on the Decorator Pattern, as every search is stacked
     * to provide the final output.
     * @param reservations the ArrayList of Reservation
     * @param providerName the Provider's name or keyword
     * @param clientName the Client's name or keyword
     * @param accommodationName the Accommodation's name or keyword
     * @param checkIn the date of the oldest check in date
     * @param checkOut the date of the last check in date
     * @return the filtered ArrayList of Reservation
     */
    public ArrayList<Reservation> searchReservations(ArrayList<Reservation> reservations, String providerName, String clientName, String accommodationName, String checkIn, String checkOut){
        if(!providerName.equals("")){
            reservations = searchProviderName(providerName, reservations);
        }
        if(!clientName.equals("")){
            reservations = searchClientName(clientName, reservations);
        }
        if(!accommodationName.equals("")){
            reservations = searchAccommodationName(accommodationName, reservations);
        }
        if(!checkIn.isEmpty()){
            reservations = searchReservationsAfter(checkIn, reservations);
        }
        if(!checkOut.isEmpty()){
            reservations = searchReservationsBefore(checkOut, reservations);
        }
        return reservations;
    }

    /**
     * A method to check whether the String parameter can be used for creating a Calendar object.
     * @param date the String that contains the date
     * @return true if the String is valid
     */
    public boolean dateChecker(String date){
        if(date.isEmpty()){return true;}
        int yearIn, monthIn, dayIn;
        String[] checkInItems = date.split("\\-");


        if(checkInItems.length<3){
            return false;
        }
        else if(checkInItems.length>2) {
            try {
                yearIn = Integer.parseInt(checkInItems[0]);
                monthIn = Integer.parseInt(checkInItems[1]);
                dayIn = Integer.parseInt(checkInItems[2]);
            } catch (NumberFormatException e) {
                return false;
            }
            return !(monthIn < 1 | monthIn > 12 | yearIn > 2023 | dayIn < 1 | dayIn > 31);
        }
        return true;
    }

    /**
     * A private method that filters the ArrayList of Reservation to those that the name of the Provider of the Accommodations
     * contains the String parameter (name)
     * @param providerName the name of the Provider
     * @param reservations the ArrayList of Reservation to be searched
     * @return the filtered ArrayList of Reservation
     */
    private ArrayList<Reservation> searchProviderName(String providerName, ArrayList<Reservation> reservations){
        return reservations.stream().filter(reservation -> reservation.getProviderName().contains(providerName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Reservation to those that the name of the Client
     * contains the String parameter (name)
     * @param clientName the name of the Client
     * @param reservations the ArrayList of Reservation to be searched
     * @return the filtered ArrayList of Reservation
     */
    private ArrayList<Reservation> searchClientName(String clientName, ArrayList<Reservation> reservations){
        return reservations.stream().filter(reservation -> reservation.getClientName().contains(clientName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Reservation to those that the name of the Accommodation
     * contains the String parameter (name)
     * @param accommodationName the name of the Accommodation
     * @param reservations the ArrayList of Reservation to be searched
     * @return the filtered ArrayList of Reservation
     */
    private ArrayList<Reservation> searchAccommodationName(String accommodationName, ArrayList<Reservation> reservations){
        return reservations.stream().filter(reservation -> reservation.getAccommodationName().contains(accommodationName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Reservation to those that their check in date is after a specific
     * date
     * @param date the oldest check in
     * @param reservations the ArrayList of Reservation to be searched
     * @return the filtered ArrayList of Reservation
     */
    private ArrayList<Reservation> searchReservationsAfter(String date, ArrayList<Reservation> reservations){
        String[] date1 = date.split("\\-");
        Calendar check = new Calendar.Builder().setDate(Integer.parseInt(date1[0]), Integer.parseInt(date1[1])-1, Integer.parseInt(date1[2])).build();
        return reservations.stream().filter(reservation -> reservation.getCheckIn().after(check)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Reservation to those that their check in date is before a specific
     * date
     * @param date the newest check in
     * @param reservations the ArrayList of Reservation to be searched
     * @return the filtered ArrayList of Reservation
     */
    private ArrayList<Reservation> searchReservationsBefore(String date, ArrayList<Reservation> reservations){
        String[] date1 = date.split("\\-");
        Calendar check = new Calendar.Builder().setDate(Integer.parseInt(date1[0]), Integer.parseInt(date1[1])-1, Integer.parseInt(date1[2])).build();
        return reservations.stream().filter(reservation -> reservation.getCheckIn().before(check)).collect(Collectors.toCollection(ArrayList::new));
    }



}
