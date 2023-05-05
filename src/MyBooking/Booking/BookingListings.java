package MyBooking.Booking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

/**
 *  Η κλαση χρησιμοποιηται για την αποθηκευση των κρατησεων. Η κλαση αποθηκευει της κρατησεις σε
 *  ενα Arraylist το οποιο ειναι το πεδιο της κλασης και ειναι τυπου δεδομενων Reservation.
 *  @author K.Stafylidis - P.Daratzis.
 *  @version 2.0
 */

public class BookingListings {
    ArrayList<Reservation> reservations;

    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται ορισματα. Το μονο που κανει ειναι να αρχικοποιει το ArrayList
     * οπου θα αποθηκευονται τα δεδομενα.
     */
    public BookingListings(){
        reservations = new ArrayList<>();
    }

    /**
     * @param providerName ειναι παραμετρος με τυπο δεδομενων String.
     * @param clientName ειναι παραμετρος με τυπο δεδομενων String.
     * @param accomodationId ειναι παραμετρος με τυπο δεδομενων String.
     * @param checkIn ειναι παραμετρος με τυπο δεδομενων Calendar
     * @param checkOut ειναι παραμετρος με τυπο δεδομενων Calendar
     * @param canceled ειναι παραμετρος με τυπο δεδομενων Calendar
     * @param accommodationName ειναι παραμετρος με τυπο δεδομενων String
     * Η μεθοδος δεχεται 7 ορισματα και χρησιμοποιηται για την δημιουργεια μια κρατησης.
     * Η μεθοδος ελεγχει αν τα ορισματα που δοθηκαν   ειναι εγκειρα και μπορει να δημιουργηθει κρατηση.
     * Αν ειναι η κρατηση δημιουργηται και η μεθοδος επιστρεφει true. Αλλιως επιστρεφει false.
     * @return επστρεφει true ή false αναλογα το αν τα ορισματα που δοθηκαν στην συναρτηση ειναι εγκειρα.
     */
    public boolean createNewReservation(String providerName, String clientName, String accomodationId, Calendar checkIn, Calendar checkOut, boolean canceled, String accommodationName){
        if(reservations.stream().filter(reservation -> reservation.getAccomodationId().equals(accomodationId)).filter(reservation -> !reservation.booked(checkIn,checkOut)).collect(Collectors.toCollection(ArrayList::new)).isEmpty()) {
            Reservation temp =  new Reservation(providerName, clientName, accomodationId, checkIn, checkOut, canceled);
            temp.setAccommodationName(accommodationName);
            reservations.add(temp);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     *
     * @param reservationId ειναι τυπου String και συμβολιζει το id της κρατησεις.
     *  Η μεθοδος χρησιμοποιειται για να πραγματοποιησει την ακυρωση μια κρατησης. Η μεθοδος δεχεται ενα ορισμα το οποιο
     *  αντιπροσοπευει το id του καταλυματος και ελεγχει αν στο ArrayList των καταλλυματων υπαρχει καταλυμα με το συγκεκριμενο
     *  id. Αν υπαρχει αλλαζει το πεδιο canceled σε true ωστε να πρααγματοποιηθει η ακηρωσει της κρατησεις.
     */

    public void cancelReservation(String reservationId){
        reservations.stream().filter(reservation -> reservation.getId().equals(reservationId)).collect(Collectors.toCollection(ArrayList::new)).get(0).setCanceled(true);
    }

    /**
     *  Η μεθοδος επιστρεφει ενα νεο ArrayList που περιεχει τα καταλυματα που δεν εχει πραγματοποηθει ακυρωση κρατησης.
     * @return επιστρεφει το νεο ArrayList με τα καταληματα που δεν εχει πραγματοποιηθει ακυρωση κρατησεις.
     */

    public ArrayList<Reservation> getReservations() {
        return reservations.stream().filter(reservation -> !reservation.getCanceled()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param clientName ειναι παραμετρος με τυπο δεδομενων String.
     * Η μεθοδος επιστεφει ενα νεο ArrayList οπουυ περιεχει τις κρατισεις που εχει κανει
     * ο πελατης με το συγκεκριμενο ονομα που δοθηκε ως παραμετρο.
     * @return επιστρεφει ενα νεο ArrayList με τις κρατισεις ενος συγκεκριμενου πελατη.
     */
    public ArrayList<Reservation> getClientReservations(String clientName){
        return getReservations().stream().filter(reservation -> reservation.getClientName().equals(clientName)).filter(reservation -> !reservation.getCanceled()).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *  @param clientName ειναι παραμετρος με τυπο δεδομενων String.
     *  Η μεθοδος επιστεφει ενα νεο ArrayList οπου περιχει τις ακυρωσεις που εχει κανει
     *  πελατης με το συγκεκριμενο ονομα που δοθηκε ως παραμετρο.
     *  @return επιστρεφει ενα νεο ArrayList με τις ακυρωσεις ενος συγκεκριμενου πελατη.
     */
    public ArrayList<Reservation> getClientCancellations(String clientName){
        return getCancelations().stream().filter(reservation -> reservation.getClientName().equals(clientName)).collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * @param providerName ειναι παραμετρος με τυπο δεδομενων String.
     * Η μεθοδος επιστεφει ενα νεο ArrayList οπου περιχει τις κρατισεις που εχουν γινει στα καταλυματα
     * ενος συγκεκριμενου προμηθευτη που το ονομα του δοθηκε ως ορισμα στη συναρτηση.
     * @return επιστρεφει ενα νεο ArrayList οπου περιχει τιε κρατισεις που εχουν γινει στα καταλυματα ενος προμηθευτη.
     */
    public ArrayList<Reservation> getProviderReservation(String providerName){
        return getReservations().stream().filter(reservation -> reservation.getProviderName().equals(providerName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * @param providerName ειναι παραμετρος με τυπο δεδομενων String.
     * Η μεθοδος επιστεφει ενα νεο ArrayList οπου περιχει τις ακυρωσεις που εχουν γινει στα καταλυματα
     * ενος συγκεκριμενου προμηθευτη που το ονομα του δοθηκε ως ορισμα στη συναρτηση.
     * @return επιστρεφει ενα νεο ArrayList οπου περιχει τις ακυρωσεις που εχουν γινει στα καταλυματα ενος προμηθευτη.
     */
    public ArrayList<Reservation> getProviderCancellations(String providerName){
        return getCancelations().stream().filter(reservation -> reservation.getProviderName().equals(providerName)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *  Η μεθοδος επιστρεφει ενα ArrayList με τα καταλυματα που εχει γινει ακυρωση κρατησεις.
     * @return το νεο ArrayList με τις ακυρωσεις κρατησεις.
     */

    public ArrayList<Reservation> getCancelations() {return reservations.stream().filter(Reservation::getCanceled).collect(Collectors.toCollection(ArrayList::new));}

    /**
     * Η μεθοδος επιστρεφει ενα ArrayList με ολα τα καταλυματα.
     * @return επιστρεφεται ενα ArrayList με ολα τα καταλυματα.
     */
    public ArrayList<Reservation> getAllReservations(){return reservations;}

}