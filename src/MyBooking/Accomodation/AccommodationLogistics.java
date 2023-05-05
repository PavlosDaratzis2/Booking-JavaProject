package MyBooking.Accomodation;

import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 *  Η κλαση AccommodationLogistics χρησιμοποιηται για την διαχειρησει ολων των καταληματων της εφαρμοφης. Στην κλαση υπαρχει
 *  ενα πεδιο το οποιο ειναι τυπου accommodation και ειναι ArrayList  στο οποιο αποθηκευονται ολα τα καταλυματα του προγραμματος.
 *  @author K.Stafylidis - P.Daratzis.
 *  @version 2.0
 */

public class AccommodationLogistics {
    private final ArrayList<Accomodation> accomodations;

    /**
     * Ο κατασκευαστης της κλασης δεν παιρνει ορισματα και το μονο που κανει ειναι να δημιουργει ενα Arraylist με το
     * ονομα accommodations.
     */
    public AccommodationLogistics(){
        accomodations = new ArrayList<>();
    }

    /**
     *  @param name Η παραμετρος name ειναι τυπου String και συμμβολιζει το ονομα του καταλυματος.
     *  @param price Η παραμετρος price ειναι τυπου int και  συμβολιζει την τιμη του καταλυματος.
     *  @param meter2 Η παραμετρος mater2 ειναι τυπου int και συμβολιζει τα τ.μ του καταλυματος.
     *  @param wifi Η παραμετρος wifi ειναι τυπου boolean και  συμβολιζει τo φιλτο που υπαρχει την αναζητη wifi του καταλυματος.
     *  @param parking Η παραμετρος parking ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη parking του καταλυματος.
     *  @param breakfast Η παραμετρος breakfast ειναι τυπου boolean καισυμβολιζει τo φιλτο που υπαρχει την αναζητη breakfast του καταλυματος.
     *  @param longTerm Η παραμετρος longTerm ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη longTerm του καταλυματος.
     *  @param deleted Η παραμετρος deleted ειναι τυπου boolean και αναπαριστα αναλογα με την τιμη του αν ειναι διαγραμενο το καταλυμα ή οχι.
     *  @param providerId Η παραμετρος providerId ειναι τυπου String και συμβολιζει το κωδικο του προμηθευτη.
     *  Η μεθοδος χρησιμοποιηται για την δημιουργια ενος καινουργιου καταλυματος Hotel το οποιο προστιθεται στο ArrayList.
     */

    public void createNewHotel(String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longTerm,boolean deleted, String providerId){
        Hotel temp = new Hotel(name,price, meter2, wifi, parking, breakfast, longTerm, providerId);
        temp.setDeleted(deleted);
        accomodations.add(temp);
    }
    /**
     *  @param name Η παραμετρος name ειναι τυπου String και συμμβολιζει το ονομα του καταλυματος.
     *  @param price Η παραμετρος price ειναι τυπου int και  συμβολιζει την τιμη του καταλυματος.
     *  @param meter2 Η παραμετρος mater2 ειναι τυπου int και συμβολιζει τα τ.μ του καταλυματος.
     *  @param wifi Η παραμετρος wifi ειναι τυπου boolean και  συμβολιζει τo φιλτο που υπαρχει την αναζητη wifi του καταλυματος.
     *  @param parking Η παραμετρος parking ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη parking του καταλυματος.
     *  @param breakfast Η παραμετρος breakfast ειναι τυπου boolean καισυμβολιζει τo φιλτο που υπαρχει την αναζητη breakfast του καταλυματος.
     *  @param longTerm Η παραμετρος longTerm ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη longTerm του καταλυματος.
     *  @param deleted Η παραμετρος deleted ειναι τυπου boolean και αναπαριστα αναλογα με την τιμη του αν ειναι διαγραμενο το καταλυμα ή οχι.
     *  @param providerId Η παραμετρος providerId ειναι τυπου String και συμβολιζει το κωδικο του προμηθευτη.
     *  Η μεθοδος χρησιμοποιηται για την δημιουργια ενος καινουργιου καταλυματος Room το οποιο προστιθεται στο ArrayList.
     */

    public void createNewRoom(String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longTerm,boolean deleted, String providerId ){
        Room temp = new Room(name, price, meter2, wifi, parking, breakfast, longTerm, providerId);
        temp.setDeleted(deleted);
        accomodations.add(temp);
    }
    /**
     *  @param name Η παραμετρος name ειναι τυπου String και συμμβολιζει το ονομα του καταλυματος.
     *  @param price Η παραμετρος price ειναι τυπου int και  συμβολιζει την τιμη του καταλυματος.
     *  @param meter2 Η παραμετρος mater2 ειναι τυπου int και συμβολιζει τα τ.μ του καταλυματος.
     *  @param wifi Η παραμετρος wifi ειναι τυπου boolean και  συμβολιζει τo φιλτο που υπαρχει την αναζητη wifi του καταλυματος.
     *  @param parking Η παραμετρος parking ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη parking του καταλυματος.
     *  @param breakfast Η παραμετρος breakfast ειναι τυπου boolean καισυμβολιζει τo φιλτο που υπαρχει την αναζητη breakfast του καταλυματος.
     *  @param longTerm Η παραμετρος longTerm ειναι τυπου boolean και συμβολιζει τo φιλτο που υπαρχει την αναζητη longTerm του καταλυματος.
     *  @param deleted Η παραμετρος deleted ειναι τυπου boolean και αναπαριστα αναλογα με την τιμη του αν ειναι διαγραμενο το καταλυμα ή οχι.
     *  @param providerId Η παραμετρος providerId ειναι τυπου String και συμβολιζει το κωδικο του προμηθευτη.
     *  Η μεθοδος χρησιμοποιηται για την δημιουργια ενος καινουργιου καταλυματος House το οποιο προστιθεται στο ArrayList.
     */

    public void createNewHouse(String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longTerm,boolean deleted, String providerId ){
        House temp = new House(name, price, meter2, wifi, parking, breakfast, longTerm, providerId);
        temp.setDeleted(deleted);
        accomodations.add(temp);
    }
    /**
     *  Η μεθοδος επιστρεφει ενα ArrayList με τα καταλυματα.
     *  @return επιστρεφεται ενα νεο  ArrayList που περιεχει τα καταλυματα του προγραμματος.
     */

    public ArrayList<Accomodation> getAccommodations(){
        return accomodations.stream().filter(accomodation -> !accomodation.isDeleted()).collect(Collectors.toCollection(ArrayList::new));
    }
    /**
     *  Η μεθοδος ειναι τυπου δεδομενων Accommodation και επιστρεφει το ArrayList που περιεχει τα καταλυματα.
     *  @return Επιστρεφει ολοκληρο το ArrayList που περιχει τα καταληματα.
     */

    public ArrayList<Accomodation> getAllAccommodations(){
        return accomodations;
    }

    /**
     *
     * @param id ειναι τυπου String.
     * Η μεθοδος ελεγχει αν το καταλημα που παιρνει ως ορισμα το id του ειναι βραχυχρονιας ή
     * μακροχρονιας μισθωσης. Η μεθοδος επιστρεφει true αν ειναι μακροχρονιας και false αν οχι.
     *
     * @return true ή false αναλογα με το αν το καταλημα που αντιροσοπευει το id που περναει σαν
     * ορισμα ειναι μακροχρονιας η βραχυχρονιας πιστωσεις.
     */

    public boolean getLongTermForAccommodation(String id){
        return getAccommodations().stream().filter(accomodation -> accomodation.getId().equals(id)).collect(Collectors.toCollection(ArrayList::new)).get(0).isLongTerm();
    }

    /**
     *
     * @param id ειναι τυπου  String.
     * Η δεχεται ως παραμετρο ενα ορισμα τυπου String και ελεγχει αν η τιμη του ορισματος υπαρχει στο  ArrayList.
     * @return επιστεφει ενα νεο ArrayList με τα καταλυματα που τεριαζουν με το ορισμα που δοθηκε στη συναρτηση (αν
     * υπαρχουν)
     */

    public ArrayList<Accomodation> getAccommodationsProvider(String id){
        return getAccommodations().stream().filter(accomodation -> accomodation.getProvider().equals(id)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *
     * @param accId ειναι τυπου String.
     * Η μεθοδος δεχεται ως παραμετρο μια μεταβλητη τυπου String και ελεγχει
     * αν καποιο καταλυμα που υυπαρχει μεσα στο ArrayList των καταλυματων τεριαζει
     * με αυτη την μεταβλητη αν τεριαζει επιστρεφει το καταλημα. Αν κανενα καταλημα δεν τεριαζει ειστρεφει null.
     * @return επιστρεφει accommodation ή null αναλογα με το αν βρει καταλημα που να τεριαζει με το ορισμα που
     * δοθηκε στη συναρτηση.
     */

    public Accomodation getAccommodation(String accId){
        for (Accomodation accomodation : getAccommodations()){
            if (accomodation.getId().equals(accId)) {
                return accomodation;
            }
        }
        return null;
    }



}