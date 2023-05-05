package MyBooking.Accomodation;

import MyBooking.ImageLoader;

/**
 *  Η κλάση Room ειναι μια υποκλαση της υπερκλασης Accommodation που δημιουργει ενα καταλυμα(Room). Η κλαση κληρονομει ολα τα
 *  πεδια και της μεθοδους της Accommodation.
 *  @author K.Stafylidis - P.Daratzis
 *  @version 2.0
 *
 */

public class Room extends Accomodation {

    /**
     * @param name Το ορισμα ειναι τυπου String και συμβολιζει το ονομα του Hotel.
     * @param price Το ορισμα ειναι τυπου int και συμβολιζει την τιμη του Hotel.
     * @param meter2 Το ορισμα ειναι τυπου int και συμβολιζει τα τ.μ του Hotel.
     * @param wifi To ορισμα  ειναι τυπου boolean και συμβολιζει ενα φιλτρο του Hotel.
     * @param parking To ορισμα  ειναι τυπου boolean και συμβολιζει ενα φιλτρο του Hotel.
     * @param breakfast To ορισμα  ειναι τυπου boolean και συμβολιζει ενα φιλτρο του Hotel.
     * @param longTerm To ορισμα  ειναι τυπου boolean και συμβολιζει ενα φιλτρο του Hotel.
     * @param providerCode Το ορισμα ειναι τυπου String  και συμβολιζει το κωδικο τοου χρηστη.
     *  Στον κατασκευστη της κλασης δινονται ως παραμετροι οι παραπανω μεταβλητες οι οποιες εγχωρουνται στα πεδια που
     * κληρονομει η κλαση Room απο την υπερκλαση της(Accommodation).Επισης στον κατασκευαστη της κλασης διμιουργηται και ενα αντικειμενο
     * accommodationImage που ειναι τυπου ImageLoader οπου χρησιμοποιτηται για την "φορτωση" των εικονων των Room καταλυματων.Η χρηση της κληρονομικοτητας
     * επιτιγχανεται με της εντολη "super".
     */

    public Room(String name, int price, int meter2, boolean wifi, boolean parking, boolean breakfast, boolean longTerm, String providerCode){
        super();
        this.id = ++code;
        this.name = name;
        this.price = price;
        this.meter2 = meter2;
        this.wifi = wifi;
        this.parking = parking;
        this.breakfast = breakfast;
        this.providerCode = providerCode;
        this.longTerm = longTerm;
        this.deleted = false;
        accommodationImage = new ImageLoader().readImageFromFile(getId());
    }

    /**
     *   Η μεθοδος δημιουργει μια συμβολοσειρα. Η συμβολοσειρα αποτελειται απο δεδομενα για την κατασταση του καταλυματατος
     *   τα οποια εγραφονται στο αρχειο.
     *   @return επιστρεφει την συμβολοσειρα που γραφεται στο αρχειο.
     */

    public String accommodationsToString(){
        return "ROOM|" + name + "|" + price + "|"  +  meter2 + "|" + wifi + "|" + parking + "|" + breakfast + "|" + longTerm + "|" + deleted + "|" + providerCode;
    }
}