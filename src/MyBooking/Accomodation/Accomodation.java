package MyBooking.Accomodation;
import MyBooking.ImageLoader;
import java.awt.image.BufferedImage;

/**
 *   H κλαση Accomadation ειναι μια αφηρημενη κλαση που περιγραφει τις διαφορες κατηγοριες καταλυματων(Hotel,House,Room).
 * Η κλαση Accomadation θεωρειται οτι ειναι η υπερκλαση και οι κλασεις (Hotel,House,Room) ειναι οι υποκλασεις της. Η
 * κλαση περιεχει 11 πεδια (code,id,price,meter2,wifi,parking,breakfast,providercode,name,deleted,longterm και accomadationImage).
 * Ολα αυτα τα πεδια ειναι μοναδικα για καθε καταλυμα. Κατασκευαστης δεν υπαρχει στη κλαση επειδη ειναι αφηρημενη μονο οι κλασεις
 * (Hotel,House,Room) περιεχουν που δεν ειναι αφηρημενες.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 *
 */


public abstract class Accomodation{
    protected static int code = 0;
    protected int id;
    protected int price, meter2;
    protected boolean wifi, parking, breakfast;
    protected String providerCode, name;
    protected boolean deleted, longTerm;
    protected BufferedImage accommodationImage;

    /**
     * Η μεθοδος χρησιμοποιητι για να επιστρεψει τη φωτογραφια του κταλυματος.
     * @return accommodationImage.
     */
    public BufferedImage getAccommodationImage() {
        return accommodationImage;
    }

    /**
     * Η μεθοδος χρησιμοποιηται για να φωρτοσει μια εικονα απο το
     * αρχειο στο πεδιο accommodationImage.
     */

    public void updateImage(){
        this.accommodationImage = new ImageLoader().readImageFromFile(getId());
    }

    /**
     *  Η μέθοδος επιστρέφει την τίμη του πεδίου name.
     *  @return name
     */

    public String getName() {
        return name;
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου id.
     *  @return id
     */

    public String getId(){
        return String.valueOf(id);
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου price.
     *  @return price
     */

    public int getPrice() {
        return price;
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου meter2.
     *  @return meter2
     */

    public int getMeter2() {
        return meter2;
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου wifi.
     *  @return wifi
     */

    public boolean getWifi() {
        return wifi;
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου parking.
     *  @return parking
     */

    public boolean getParking() {
        return parking;
    }

    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου breakfast.
     *  @return breakfast.
     */

    public boolean getBreakfast() {
        return breakfast;
    }
    /**
     *  Η μέθοδος επιστρέφει την τιμή του πεδίου providerCode.
     *  @return providerCode
     */

    public String getProvider() {
        return providerCode;
    }
    /**
     *
     * @param price ειναι τυπου itn
     *  Η μέθοδος δέχεται ως παράμετρο μια ακέραια μεταβλητή και την εισαγει στο πεδιο price.
     */

    public void setPrice(int price) {
        this.price = price;
    }
    /**
     *
     * @param meter2 ειναι τυπου int
     *  Η μέθοδος δέχεται ως παράμετρο μια ακέραια μεταβλητή και την εισαγει στο πεδιο meter2.
     */

    public void setMeter2(int meter2) {
        this.meter2 = meter2;
    }
    /**
     *
     * @param wifi ειναι τυπου boolean
     *  Η μέθοδος δέχεται ως παράμετρο μια  μεταβλητή τυπου boolean και την εισαγει στο πεδιο wifi.
     */

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }
    /**
     *
     * @param parking ειναι τυπου boolean
     *  Η μέθοδος δέχεται ως παράμετρο μια  μεταβλητή τυπου boolean και την εισαγει στο πεδιο parking.
     */

    public void setParking(boolean parking) {
        this.parking = parking;
    }
    /**
     *
     * @param breakfast ειναι boolean
     *  Η μέθοδος δέχεται ως παράμετρο μια  μεταβλητή τυπου boolean και την εισαγει στο πεδιο breakfast.
     */

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }
    /**
     *
     * @param name ειναι τυπου String
     *  Η μεθοδος δεχεται μια μεταβλητη String και την εισαγει στο πεδιο name.
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     *  H μεθοδος επιστεφει την τιμη του πεδιου deleted.
     *  @return deleted.
     */
    public boolean isDeleted() {

        return deleted;
    }
    /**
     *
     *  @param deleted ειναι τυπου boolean
     *  Η μεθοδος δεχετε ως παραμετρο μια μεταβλητη deleted τυπου boolean και την εισαγει στο πεδιο deleted.
     */

    public void setDeleted(boolean deleted) {

        this.deleted = deleted;
    }
    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου longterm.
     *  @return longterm
     */

    public boolean isLongTerm() {
        return longTerm;
    }
    /**
     *
     * @param longTerm ειναι τυπου boolean.
     *  H μεθοδος δεχεται μια μεταβλητη longterm τυπου boolean και την εισαγει στο πεδιο longterm.
     */

    public void setLongTerm(boolean longTerm) {
        this.longTerm = longTerm;
    }
    /**
     *  Ειναι μια αφηρημενη μεθοδος που χρησιμοποιηται για την δημιουργια μιας συμβολοσειρας που εισαγεται σε ενα αρχειο.
     * @return επιστρεφει την συμβολοσειρα που γραφεται στο αρχειο.
     */

    public abstract String accommodationsToString();
}