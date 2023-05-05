package MyBooking.SearchTools;

import MyBooking.Accomodation.Accomodation;
import java.util.ArrayList;
import java.util.stream.Collectors;


/**
 * A class that is responsible for the implementation of all the needed searches for filtering the ArrayList of Accommodations
 * to specific criteria. Filters used are: Maximum Price, Minimum Price, Name of the Accommodation, Wi-Fi, Breakfast,
 * Parking and Long Term Lease.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */
public class SearchModeAccommodations {
    private ArrayList<Accomodation> accommodations;


    /**
     * The only public method of the class. It implements the proper filtering to an ArrayList of Accommodations according
     * to the parameters given. The implementation is partially based on the Decorator Pattern, as every search is stacked
     * to provide the final output.
     * @param accommodations the ArrayList of Accommodations to be searched
     * @param accommodationName the name of the accommodation
     * @param maxPrice the maximum price for all Accommodations
     * @param minPrice the minimum price for all Accommodations
     * @param longterm true for filtering all Long Term Lease Accommodations
     * @param wifi true for filtering all Accommodations providing Wi-Fi
     * @param parking true for filtering all Accommodations providing parking
     * @param breakfast true for filtering all Accommodations providing breakfast
     * @return the filtered ArrayList of Accommodations
     */
    public ArrayList<Accomodation> search(ArrayList<Accomodation> accommodations,String accommodationName, int maxPrice, int minPrice, boolean longterm, boolean wifi, boolean parking, boolean breakfast){
        this.accommodations = accommodations;
        if(maxPrice!=0){
            maxPrice(maxPrice);
        }
        if(minPrice!=0){
            minPrice(minPrice);
        }
        if(!accommodationName.equals("")){
            accommodationName(accommodationName);
        }
        if(longterm){
            longTerm();
        }
        if(wifi){
            wifi();
        }
        if(parking){
            parking();
        }
        if(breakfast){
            breakfast();
        }
        return this.accommodations;
    }

    /**
     * A private method to filter the ArrayList of Accommodations to those that their maximum price is lower or equal to the specific price
     * @param maxPrice the maximum price
     */
    private void maxPrice(int maxPrice){
        accommodations = accommodations.stream().filter(accommodation -> accommodation.getPrice() <= maxPrice).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method to filter the ArrayList of Accommodations to those that their minimum price is lower or equal to the specific price
     * @param minPrice the minimum price
     */
    private void minPrice(int minPrice){
        accommodations = accommodations.stream().filter(accommodation -> accommodation.getPrice() >= minPrice).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Accommodations to those that provide breakfast
     */
    private void breakfast(){
        accommodations = accommodations.stream().filter(Accomodation::getBreakfast).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Accommodations to those that provide Wi-Fi
     */
    private void wifi(){
        accommodations = accommodations.stream().filter(Accomodation::getWifi).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Accommodations to those that are available for long term lease
     */
    private void longTerm(){
        accommodations = accommodations.stream().filter(Accomodation::isLongTerm).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Accommodations to those that their name contains the String parameter (name)
     * @param accommodationName the name of the Accommodation
     */
    private void accommodationName(String accommodationName){
        accommodations = accommodations.stream().filter(accommodation -> accommodation.getName().contains(accommodationName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A private method that filters the ArrayList of Accommodations to those that provide parking
     */
    private void parking(){
        accommodations = accommodations.stream().filter(Accomodation::getParking).collect(Collectors.toCollection(ArrayList::new));
    }


}
