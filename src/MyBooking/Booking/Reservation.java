package MyBooking.Booking;


import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *  H Κλαση χρησιμοποηται για την δημιουργια μιας κρατησεις.
 *  Η κλαση περιεχει στο σωμα της 10 πεδια. Η κλαση δημιουγει αντικειμενα
 *  της κλασεις Calendar.
 *  @author K.Stafylidis - P.Daratzis.
 *  @version 2.0
 */


public class Reservation {
    private static int code = 0;
    private final int id;
    private final String providerName;
    private final String clientName;
    private final String accomodationId;
    private String accommodationName;
    private final Calendar checkIn;
    private final Calendar checkOut;
    private final Calendar checkOutPrint;
    private boolean canceled;

    /**
     *
     * @param providerName Ειναι τυπου String και συμβολιζει το ονομα του προμηθευτη.
     * @param clientName Ειναι τυπου String και συμβολιζει το ονομα του πελατη.
     * @param accomodationId Ειναι τυπου String και συμβολιζει τον κωδικο του καταλυματος.
     * @param checkIn Ειναι τυπου calendar και συμβολιζει την εναρξη της διαμονης.
     * @param checkOut Ειναι τυπου calendar και συμβολιζει το τελος της διαμονης.
     * @param canceled Ειναι τυπου boolean και δειχνει αν εχει γινει ακυρωσει της κρατησεις ή οχι.
     * Ο κατασκευαστης της κλασης δεχεται τα ορισματα και τα εισαγει στα πεδια της κλασης και δημιουργει
     * και ενα αντικειμενο τυπου Calendar με ονομα checkOutPrint.
     */

    public Reservation(String providerName, String clientName, String accomodationId, Calendar checkIn, Calendar checkOut, boolean canceled){
        this.id = ++code;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.accomodationId = accomodationId;
        this.clientName = clientName;
        this.providerName = providerName;
        this.checkOutPrint = new Calendar.Builder().setInstant(checkOut.getTimeInMillis()+(1000*60*60*24)).build();
        this.canceled = canceled;
    }

    /**
     *
     * @param accommodationName η παραμετρος ειναι τυπου String.
     * Η μεθοδος δεχεται ενα ορισμα τυπου String και το εισαγει
     * στην μεταβλητη accommodationName.
     */

    public void setAccommodationName(String accommodationName){
        this.accommodationName = accommodationName;
    }

    /**
     * Η μεθοδος επιστεφει την τιμη της μεταβλητης accommodationName.
     * @return accommodationName.
     */
    public String getAccommodationName(){
        return this.accommodationName;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου canceled που ειναι τυπου boolean.
     *  @return canceled.
     */

    public boolean getCanceled(){
        return canceled;
    }

    /**
     *
     * @param canceled ειναι τυπου boolean
     *  Η μεθοδος δεχεται ως ορισμα μια μεταβλητη τυπου boolean και την εισαγει
     *  στο πεδιο canceled.
     */

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }
    /**
     *
     *  @param dateIn ειναι ορισμα τυπου Calendar οπου συμβολιζει την ημερομηνια της αφιξης στο καταλυμα.
     *  @param dateOut ειναι ορισμα τυπου Calendar οπου συμβολιζει την ημερομηνια της αποχωρισης απο το καταλημα.
     *  Η μεθοδος μεθοδος δεχεται δυο ορισματα και επιστρεφει true ή false αναλογα με το αν οι τιμες των ημερομηνιων
     *  των δυο ορισματων ειναι αποδεκτες.
     *  @return επιστρεφει true ή false αναλογα με το αν η συνθηκη ειναι αληθης ή ψευδης.
     */

    public boolean booked(Calendar dateIn, Calendar dateOut){
        return dateIn.after(this.checkOut) | dateOut.before(this.checkIn);
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου providerName που ειναι τυπου String.
     *  @return providerName
     */

    public String getProviderName() {
        return providerName;
    }

    /**
     *  Η μεθοδος  επιστρεφει την τιμη του πεδιου clientName που ειναι τυπου String.
     *  @return clientName
     */

    public String getClientName() {
        return clientName;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου id το πεδιο id ειναι τυπου int
     *  αλλα γινεται casting και επιστρεφεται σε μορφη String.
     *  @return id
     */

    public String getId(){
        return String.valueOf(id);
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου accommodationId που ειναι τυπου String
     *  @return AccommodationId
     */

    public String getAccomodationId() {
        return accomodationId;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου checkIn που ειναι τυπου Calendar
     *  @return checkIn
     */

    public Calendar getCheckIn(){
        return checkIn;
    }

    /**
     * Η μεθοδος επιστρεφει την τιμη του πεδιου checkoutPrint που ειναι τυπου Calendar
     * @return checkoutPrint.
     */

    public Calendar getCheckOutPrint(){
        return this.checkOutPrint;
    }

    /**
     *  Η μεθοδος επιστρεφει μια συμβολοσειρα με τα στοιχεια της κρατησης η οποια γραφεται σε ενα αρχειο κειμενου.
     *  @return επιστρεφει τη συμβολοσειρα με τα στειχεια της κρατησεις.
     */

    public String reservationToString(){
        return providerName + "|" + clientName + "|" + accomodationId + "|" + checkIn.getTimeInMillis() + "|" + checkOut.getTimeInMillis() + "|" + canceled+"|"+accommodationName;
    }

    /**
     *  @param cal ειναι ορισμα τυπου Calendar.
     *  Η μεθοδος δεχεται ενα ορισμα τυπου Calendar που ειναι ενα αντικημενο που περιεχει μια ημερομηνια
     *  και το μετατρεπει στη συμβολοσειρα που θελουμε να εμφανιζεται στην οθονη η οποια εχει την εξης μορφη
     * ("yyyy-MM-dd").
     *  @return επιστρεφει την ημερομηνια σε μορφη συμολοσειρας.
     */
    public String getDateFromCalendar(Calendar cal){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(cal.getTime());
    }

    /**
     * Η μεθοδος χρησιμοποιηται για να υπολογισει τον αριθμο τον ημερων της κρατησης.
     * @return επιστρεφει τον αριθμο των ημερων της κρατησης.
     */

    public long days(){
        return (checkOut.getTimeInMillis()-checkIn.getTimeInMillis())/(24*60*60*1000);
    }

}