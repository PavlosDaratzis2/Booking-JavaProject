
package MyBooking.Messaging;
/**
 *  H κλαση χρησιμοποιηται για την δημιοιυργια ενος μηνυματος που εμφανιζεται στην οθονη.
 *  Η κλαση εχει 6 πεδια.
 *  @author K.Stafylidis - P.Daratzis
 *  @version 2.0
 */

public class Message {
    private final String adminId;
    private final String otherUserId;
    private final String message;
    private static int code = 0;
    private final int id;
    private boolean deleted;

    /**
     *
     *  @param adminId ειναι τυπου String και συμβολιζει το id του admin.
     *  @param otherUserId ειναι τυπου String και συμβολιζει το id ενος χρηστη.
     *  @param message ειναι τυπου String και περιεχει το μηνυμα που θα εκτυπωθει στη οθονη.
     *   Ο κατασκευαστης δεχεται τρια ορισματα τα οποια τοποθετει στα πεδια της κλασης. Ενω
     *   τα υπολοιπα πεδια δεν αρχικοποιονται απο καποιο ορισμα.
     */

    public Message(String adminId, String otherUserId, String message){
        id = ++code;
        this.adminId = adminId;
        this.otherUserId = otherUserId;
        this.message = message;
        this.deleted = false;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου adminId
     *  @return adminId
     */

    public String getAdminId() {
        return adminId;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου otherUserId
     *  @return otherUserId
     */

    public String getOtherUserId() {
        return otherUserId;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου message
     *  @return message
     */

    public String getMessage() {
        return message;
    }

    /**
     *  Η μεθοδος επιστρεφει την τιμη του πεδιου id σε μορφη συμβολοσειρας.
     *  @return id
     */

    public String getId(){
        return String.valueOf(id);
    }

    /**
     *  H Η μεθοδος επιστρεφει την τιμη του πεδιου deleted
     *  @return deleted
     */

    public boolean isDeleted() {
        return deleted;
    }

    /**
     *
     *  @param deleted ειναι τυπου boolean
     *  Η μεθοδος δεχεται ως ορισμα μια μεταβλητη τυπου boolean  και την εισαγει στο πεδιο deleted.
     */

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     *  H μεθοδος επιστεφει μια συμβολοσειρα η οποια εισαγετε σε ενα αρχειο κειμενου.
     *  @return Η συμβολοσειρα που εισαγετε στο αρχειο κειμενου.
     */

    public String messageToString(){
        return adminId + "|" + otherUserId + "|" + message + "|" + deleted;
    }
}
