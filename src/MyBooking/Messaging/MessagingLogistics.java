package MyBooking.Messaging;

import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * Η κλαση χρησιμοποιηται για την αποθηκευση των μηνυματων που εμφανιζονται στην οθονη.
 * Η κλαση εχει ενα πεδιο τυπου Message το οποιο ειναι το ArrayList οπου αποθηκευονται
 * τα μηνυματα.
 * @author K.Stafylidis - P.Daratzis
 * @version 2.0
 */

public class MessagingLogistics {
    private final ArrayList<Message> messages;

    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται ορισματα. Ο κατασκευαστης αρχικοποιει το
     * ArrayList οπου θα αποθηκεθονται τα μηνυματα.
     *
     */

    public MessagingLogistics(){
        messages = new ArrayList<>();
    }
    /**
     * @param adminId ειναι τυπου String και αναπαριστα το id του admin.
     * @param otherUserId ειναι τυπου String και αναπαριστα το id  ενος χρηστη(οχι του admin).
     * @param message ειναι τυπου String και περιεχει το μηνυμα που θα εκτυπωθει στη οθονη.
     * @param deleted ειναι τυπου boolean και δειχνει αν υπαρχει μηνυμα ή οχι.
     * Η μεθοδος χρησιμοποιηται για την αποστολη ενος μηνυματος. Η μεθοδος δεχεται 4 ορισματα.
     * Η μεθοδος στο εσωτερικο της δημιουργει ενα αντικειμενο τυπου message.
     */

    public void sendMessage(String adminId, String otherUserId, String message, boolean deleted){
        Message temp = new Message(adminId, otherUserId, message);
        temp.setDeleted(deleted);
        messages.add(temp);
    }

    /**
     *
     * @param message ειναι τυπου Message.
     * Η μεθοδος πρωσθετει ενα νεο μηνυμα στο ArrayList που αποθηκευονται τα μηνυματα.
     */


    public void addMessage(Message message){
        messages.add(message);
    }

    /**
     *
     * @param adminId ειναι τυπου String και συμβολιζει το id του admin.
     * Η μεθοδος επιστρεφει ενα νεο Arraylist με τα μηνυματα που εχει στειλει ο admin.
     * @return επιστρεφει το νεο ArrayList που περιεχει τα μηνυματα του admin.
     */


    public ArrayList<Message> getMessagesAdmin(String adminId){
        return getMessages().stream().filter(message -> message.getAdminId().equals(adminId)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *
     * @param otherUserId ειναι τυπου String και συμβολιζει το id ενος χρηστη(προμηθευτη,πελατη).
     * Η μεθοδος επιστρεφει ενα νεο ArrayList με τα μηνυματα  που εχουν στειλει οι αλλοι χρηστες(εκτος του Admin).
     * @return επιστρεφει το νεο ArrayList με τα μηνυματα που εστειλα ολοι οι χρηστες εκτος του Admin.
     */

    public ArrayList<Message> getMessagesOtherUser(String otherUserId){
        return getMessages().stream().filter(message -> message.getOtherUserId().equals(otherUserId)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     *
     * @param messageId ειναι τυπου String και συμβολιζει το id του μηνυματος.
     * Η μεθοδος χρησιμοποιειται για την διαγραφει ενος μηνυματος με συγκεκριμενο id.
     */

    public void deleteMessage(String messageId){
        getMessages().stream().filter(message -> message.getId().equals(messageId)).forEach(message -> message.setDeleted(true));
    }

    /**
     * Η μεθοδος επιστρεφει το ArrayList με τα μηνυματα.
     * @return επιστρεφεται ενα νεο ArrayList με μηνυματα που υπαρχουν.
     */

    public ArrayList<Message> getMessages() {
        return messages.stream().filter(message -> !message.isDeleted()).collect(Collectors.toCollection(ArrayList::new));
    }
}
