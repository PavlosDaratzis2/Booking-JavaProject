package MyBooking.Graphics.Builders;

import MyBooking.Messaging.Message;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση εχει ενα πεδιο το οποιο ειναι τυπου GridBagConstraints. Η κλαση διμιουργει
 * συνδεση με τον χρηστη μεσω γραφικων διεπαφων. Σκοπος της κλασης ειναι η εμφανιση η
 * δημιουργια και η επεξεργασια των μηνυματων μεταξυ των χρηστων.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class MessageViewBuilder {
    GridBagConstraints g;
    /**
     *
     * @param messages ειναι τυπου δεδομενων Message και ειναι ArrayList.
     * H μεθοδος εμφανιζει στη οθονη τα μηνυμτα που εχει δεχτει ή που  εχει στειλε
     * ο χρηστης.Η εμφανισει των μηνυματων στην οθονη επιτιγχανεται μεσω γραφικων διεπαφων
     * που δημιουργουνται στη μεθοδο. Η μεθοδος δεχεται ως ορισμα το ArrayList οπου περιεχει
     * τα μηνυματα και με την χρηση γραφικων διεπαφων αλλα και την συναρτηση getMessageLabel
     * φωρτονει την λιστα των μυνηματων σε γραφικη μορφη στον παραθυρο επικοινινιας του χρηστη.
     * @return scrollPane που ειναι τυπου δεδομενων JScrollPane.
     */
    public JScrollPane getMessagesScrollPane(ArrayList<Message> messages){
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.insets = new Insets(5,0,5,0);
        g.gridwidth = 10;
        g.gridheight = 5;
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;

        messages.forEach(message -> messagesPanel.add(getMessageLabel(message),g));

        JScrollPane scrollPane = new JScrollPane(messagesPanel);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        return scrollPane;
    }
    /**
     *
     * @param message ειναι ορισμα με τυπο δεδομενων Message
     * Σκοπος της μεθοδου ειναι η δημιουργια της προβολης ενος μηνυματος
     * με τα απαρετητα χαρακτηριστικα του οπως(απο ποιον στελνεται,σε ποιον πηγαινει,
     * και τον περιεχομενο του μηνυματος). Η μεθοδος επιτιγχανει την προβολη του μηνυματος
     * μεσω γραφικων διεπαφων οπου εμφανιζονται στο παραθυρο επικοινονιας με τον χρηστη.
     * @return messagePanel που ειναι τυπου δεδομενων  JPanel.
     */
    public JPanel getMessageLabel(Message message){
        JPanel messagePanel = new JPanel();

        messagePanel.setName(message.getId());
        messagePanel.setLayout(new GridLayout(3,0));

        JLabel adminName = new JLabel("From: "+message.getAdminId());
        messagePanel.add(adminName);
        JLabel userName = new JLabel("To: "+ message.getOtherUserId());
        messagePanel.add(userName);
        JLabel messageLabel = new JLabel("Message: "+ message.getMessage());
        messagePanel.add(messageLabel);
        messagePanel.setPreferredSize(new Dimension(260,75));
        messagePanel.setBackground(new Color(189, 230, 227));
        messagePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED),"ID: "+message.getId()));
        g.gridy += 5;
        return messagePanel;
    }

}