package MyBooking.Graphics.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση UserOptionsAdmin δημιουργει γραφικη επαφη με το χρηστη.Σκοπος της
 * κλασης ειναι να δημιουργησει μεσω γραφικων διεπαφων τη φορμα  διαχειρισης
 * για τον χρηστη admin. Η κλαση εχει 9 πεδια.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 *
 */
public class UserOptionsAdmin extends JPanel {
    /**
     * activeUsers εχει τυπο δεδομενω JButton,searchUsers εχει τυπο δεδομενω JButton,
     * resetUsers εχει τυπο δεδομενω JButton,pendingUsers εχει τυπο δεδομενω JButton,
     * checkPendingUsers εχει τυπο δεδομενω JButton,getMessages εχει τυπο δεδομενω JButton,
     * sendMessage εχει τυπο δεδομενω JButton.
     */
    JButton activeUsers, searchUsers, resetUsers, pendingUsers, checkPendingUsers, getMessages, sendMessage;
    /**
     * userName εχει τυπο δεδομενω JTextField,userAddress εχει τυπο δεδομενω JTextField.
     */
    JTextField userName, userAddress;
    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται ορισματα.Στον κατασκευαστη
     * της κλασης επιτυγχανεται η γραφικη διεπαφη με το χρηστη. Στον κατασκευαστη
     * διμιουργουνται αντικειμενα με τυπο δεδομενων GridBagConstraints,JButton,JLabel
     * ολα αυτα μαζι συνθετουν το παραθυρο επικοινονιας με τον χρηστη για την φορμα των Users.
     */
    public UserOptionsAdmin() {
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        activeUsers = new JButton("Show Active Users");
        add(activeUsers, g);

        g.gridwidth = 1;
        g.gridy = 1;
        add(new JLabel("Search Options"), g);

        g.gridy = 2;
        g.gridx = 0;
        add(new JLabel("Name:"), g);

        g.gridx = 1;
        userName = new JTextField();
        add(userName, g);

        g.gridy = 3;
        g.gridx = 0;
        add(new JLabel("User Address:"), g);

        g.gridx = 1;
        userAddress = new JTextField();
        add(userAddress, g);

        g.gridy = 4;
        g.gridx = 0;
        searchUsers = new JButton("Search");
        add(searchUsers, g);

        g.gridx = 1;
        resetUsers = new JButton("Reset");
        resetUsers.setSize(new Dimension(100, 20));
        add(resetUsers, g);

        g.gridy = 5;
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(20, 10, 10, 10);
        pendingUsers = new JButton("Show Pending Users");
        add(pendingUsers, g);

        g.gridx = 0;
        g.gridy = 6;
        g.insets = new Insets(0, 10, 10, 10);
        checkPendingUsers = new JButton("Check Pending Users");
        add(checkPendingUsers, g);

        g.gridx = 0;
        g.gridy = 7;
        g.insets = new Insets(30, 10, 10, 10);
        getMessages = new JButton("Get Messages");
        add(getMessages, g);

        g.gridy = 8;
        g.insets = new Insets(0, 10, 10, 10);
        sendMessage = new JButton("Send Message");
        add(sendMessage, g);
    }
    /**
     * Η μεθοδος χρησιμοποηται για να προσθεσει τα κουμπια
     * που υπαρχουν στο παραθυρο επικοινινιας με τον χρηστη σε
     * ενα ArrayList και επιστρεφει αυτο το ArrayList.
     * @return list που τυπου δεδομενων JButton.
     */
    public ArrayList<JButton> getButtonsUserOptions() {
        ArrayList<JButton> list = new ArrayList<>();
        list.add(activeUsers);
        list.add(searchUsers);
        list.add(resetUsers);
        list.add(pendingUsers);
        list.add(checkPendingUsers);
        list.add(getMessages);
        list.add(sendMessage);
        return list;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να προσθεσει σε ενα ArrayList
     * τις τιμες τις οποιες πληκτολογησε ο χρηστης στα πεδια του
     * παραθυρου (name,User Address) και επιστρεφει αυτο το ArrayList.
     * @return list που ειναι τυπου δεδομενων JTextField.
     */
    public ArrayList<JTextField> getTextFieldsUserOptions() {
        ArrayList<JTextField> list = new ArrayList<>();
        list.add(userName);
        list.add(userAddress);
        return list;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να διαγραψει τις τιμες
     * που πληκτολογησε ο χρηστης στα πεδια του παραθυρου με
     *  τον χρηστη (name,User Address)
     */
    public void clearJTextFields() {
        userName.setText(null);
        userAddress.setText(null);

    }

}