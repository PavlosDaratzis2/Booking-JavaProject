package MyBooking.Authenticate;

import MyBooking.DataLoader;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Η κλαση SignUp χρησιμοποιηται για την εγγραφη ενος χρηστη στη εφαρμογη του booking.
 *  Η κλαση εχει εξι πεδια.Η κλαση χρησιμοποιει γραφικες διεπαφες για την επικοινονια
 *  της με τον χρηστη.
 *  @author K.Stafylidis - P.Daratzis.
 *  @version 2.0
 */

public class SignUp {
    JFrame frame;
    JTextField name, address;
    JPasswordField password;
    JRadioButton client, provider;

    /**
     *
     *  @param dataLoader ειναι ορισμα τυπου dataLoader.
     *  Στον κατασκευαστη της κλασης επιτιγχανεται η γραφικη διεπαφη με τον χρηστη. Εδω δημιουργιται η φορμα ετσι ωστε ο
     *  να μπορεσει να κανει την εγγραφει του. Ο χρηστης πρεπει να συμπληρωσει τι τυπου χρηστης επιθυμει να ειναι πελατης
     *  ή προμηθευτης να συμπληρωσει το ονομα του τον κωδικο του και την διευθηνση κατοικιας του. Αν το ονομα χρησιμοποιηται
     *  απο αλλον χρηστη που υπηρχε στην εφαρμογη απο πιο πριν θα εμφανιστη καταλληλο μηνυμα ωστε να το αλλαξει. Αφου
     *  συμπληρωσει την φορμα εγραφης θα πρεπει να παρει εγριση απο τον admin ετσι ωστε να μπορει να συνδεθει.
     */

    public SignUp(DataLoader dataLoader){
        frame = new JFrame();
        frame.setSize(new Dimension(600,300));
        Container contentPane = frame.getContentPane();
        contentPane.setSize(new Dimension(300,300));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        int top = 10, left = 10, bottom = 10, right = 10;
        g.insets = new Insets(top, left, bottom, right);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridy = 0;
        g.gridx = 0;

        contentPane.add(new JLabel("Registration Form"), g);

        g.gridheight = 2;
        g.gridy = 1;
        g.gridx = 0;
        contentPane.add( new JLabel("Type :"), g);

        g.gridheight = 1;
        g.gridx = 1;
        ButtonGroup types = new ButtonGroup();
        client = new JRadioButton("Client");
        client.setSelected(true);
        provider = new JRadioButton("Provider");
        types.add(client);
        types.add(provider);
        g.insets = new Insets(1, 1, 1, 1);
        contentPane.add(client, g);
        g.gridy = 2;
        contentPane.add(provider, g);
        g.insets = new Insets(top, left, bottom, right);
        g.gridy = 3;
        g.gridx = 0;
        contentPane.add(new JLabel("Name"), g);

        g.gridx = 1;
        name = new JTextField();
        contentPane.add(name, g);

        g.gridy = 4;
        g.gridx = 0;
        contentPane.add(new JLabel("Password"), g);

        g.gridx = 1;
        password = new JPasswordField();
        contentPane.add(password, g);

        g.gridy = 5;
        g.gridx = 0;
        contentPane.add(new JLabel("Address"), g);

        g.gridx = 1;
        address = new JTextField();
        contentPane.add(address, g);

        g.gridwidth = 2;
        g.gridy = 6;
        g.gridx = 0;
        contentPane.add(new JLabel("The account must be approved by an Admin"), g);

        g.gridwidth = 1;
        g.gridy = 7;
        g.gridx = 0;
        JButton ok = new JButton("OK");
        ActionListener okAction = e -> check(dataLoader);
        ok.addActionListener(okAction);
        contentPane.add(ok, g);

        g.gridx = 1;
        JButton cancel = new JButton("Cancel");
        ActionListener cancelArction = e -> {
            frame.dispose();
            new Authenticate(dataLoader);
        };
        cancel.addActionListener(cancelArction);
        contentPane.add(cancel, g);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        System.out.println("Start");
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     *
     * @param dataLoader ειναι ορισμα τυπου dataLoader.
     * Η μεθοδος χρησιμοποιηται για να ελενξει αν το ονομα και ο κωδικος που πληκτρολογησε ο χρηστης ειναι εγκυρα αν ειναι
     * τοτε ελεγχει αν προυπαρχει αλλος χρηστης με το ιδιο ονομα με αυτον στο προγραμμα αν κατι απο αυτα συμβαινει εμφανιζεται
     * καταλληλο μηνυμα αλλιως η εγγραφη γινεται επιτυχως.
     */

    public void check(DataLoader dataLoader){
        if(name.getText().contains("|") | password.getText().contains("|") | address.getText().contains("|")){
            JOptionPane.showMessageDialog(frame, "The character | is not allowed");
            return;
        }
        long a = dataLoader.getUsersLogistics().getUsers().stream().
                filter(users1 -> !users1.isDeleted()).
                filter(users1 -> users1.getLoginName().equals(name.getText().trim())).
                count();
        if (a==0){
            if (client.isSelected()){
                dataLoader.getUsersLogistics().createNewClient(name.getText().trim(), password.getText().trim(), address.getText().trim(), false, true);
            }
            else{
                dataLoader.getUsersLogistics().createNewProvider(name.getText().trim(), password.getText().trim(), address.getText().trim(), false, true);
            }
            JOptionPane.showMessageDialog(frame, "User account created");
            frame.dispose();
            new Authenticate(dataLoader);
        }
        else{
            JOptionPane.showMessageDialog(frame, "User name already exits");
        }
    }
}