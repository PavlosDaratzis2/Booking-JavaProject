package MyBooking.Authenticate;


import MyBooking.DataLoader;
import MyBooking.Users.Users;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *  Η κλαση Login χρησιμοποιηται για  την συνδεση του χρηστη στην εφαρμογη booking.Ενω ο χρηστης εχει ειδη λογαριασμο
 *  Η κλαση εχει 3 πεδια. Η κλαση εχει εναν κατασκευαστη. Η κλαση χρησιμοποιει γραφικες διεπαφες για την επικοινονια
 *  της με τον χρηστη.
 *  @author K.Stafylidis - P.Daratzis
 *  @version 2.0
 */

public class Login {
    JTextField nameInput;
    JPasswordField passInput;
    Users user;

    /**
     *
     * @param dataLoader ειναι ορισμα τυπου dataLoader.
     * Ο κατασκευαστης της κλασης δεχεται ως ορισμα μια παραμετρο τυπου dataLoader
     * και καλει την συναρτηση getGUI οπου δινει αυτη την παραμετρο ως ορισμα την συναρτηση.
     */

    public Login(DataLoader dataLoader) {
        getGUI(dataLoader);
    }
    /**
     *  @param users ειναι τυπου User και ειναι το ArrayList που εχει αποθηκευμενους ολους τους χρηστες.
     *  Η μεθοδος χρησιμοποιηται για να επαληθευσει τα στοιχεια για την εισοδο του χρηστη. Αν τα στοιχεια
     *  εισοδου ειναι σωστα (login name,password) τοτε η συναρτηση επιστρεφει true αλλιως επιστρεφει false.
     *  @return επιστρεφει μια τιμη τυπου boolean(true,false) αναλογα με το αν υπαρχει ο χρηστης ή οχι.
     */

    public boolean authenticate(ArrayList<Users> users) {
        ArrayList<Users> check = users.stream().
                filter(users1 -> (!users1.isDeleted() & !users1.isPending())).
                filter(users1 -> users1.getLoginName().equals(nameInput.getText().trim())).
                filter(users1 -> users1.getPassword().equals(String.valueOf(passInput.getPassword()))).
                collect(Collectors.toCollection(ArrayList::new));
        if (check.size() == 0) {
            return false;
        } else {
            user = check.get(0);
            return true;
        }
    }

    /**
     *
     * @param dataLoader ειναι ορισμα τυπου dataLoader.
     * Η συναρτηση getGUI δεχεται ως παραμετρο ενα ορισμα τυπου dataLoader. Με την συναρτηση getGUI
     * επιτιγχανεται η γραφικη διεπαφη με τον χρηστη. Μεσα στην συναρτηση διμιουργειται το παραθυρο επικοινονιας με
     * τον χρηστη. Ο χρηστης πληκρολογη το Username και το Password αν τα στοιχεια ππου πληκτολογησε ειναι σωστα και
     * τοτε θα γινει αποδεκτος και θα συνδεθει στο συστημα αλλιως θα εμφανιστει καταλληλο μηνυμα στην οθονη οτι κατι
     * πληκτρολογησε λαθος. Η μεθοδος χρησιμοποιει γραφικες διεπαφες για την επικοινιανιας της με τον χρηστη.
     */

    public void getGUI(DataLoader dataLoader){
        JFrame frame = new JFrame("Login");
        frame.setSize(new Dimension(600,300));
        frame.setLocationRelativeTo(null);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        int top = 10, left = 10, bottom = 10, right = 10;
        gbc.insets = new Insets(top, left, bottom, right);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(new JLabel("Please Login"),gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(new JLabel("Name"), gbc);

        gbc.gridx = 1;
        nameInput = new JTextField();
        contentPane.add(nameInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(new JLabel("Password"), gbc);

        gbc.gridx = 1;
        passInput = new JPasswordField();
        contentPane.add(passInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton verify = new JButton("OK");
        ActionListener verifyAction = e -> {
            if(authenticate(dataLoader.getUsersLogistics().getActiveUsers())){
                dataLoader.getUsersLogistics().setCurrentUser(user);
                frame.dispose();
                user.getInterface(dataLoader);
            }
            else{
                JOptionPane.showMessageDialog(frame, "Invalid Username or Password");
            }
        };
        verify.addActionListener(verifyAction);
        contentPane.add(verify, gbc);

        gbc.gridx = 1;
        JButton cancel = new JButton("Cancel");
        ActionListener cancelAction = e -> {
            frame.dispose();
            System.out.println("frame disposed");
            new Authenticate(dataLoader);
        };
        cancel.addActionListener(cancelAction);
        contentPane.add(cancel, gbc);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
