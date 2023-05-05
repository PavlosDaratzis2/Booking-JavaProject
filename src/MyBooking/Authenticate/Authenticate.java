package MyBooking.Authenticate;

import MyBooking.DataLoader;

import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 *  Η κλαση Authenticate ειναι η πρωτη κλαση που εκτελειται οταν εκκινει το προγραμμα.
 *  Η κλαση εμφανιζει στην οθονη της δυνατοτητες που εχει ο χρητης για εισοδο στην εφαρμογη(login,signup,terminate).
 *  H κλαση δεν εχει πεδια. Η κλαση εχει εναν κατασκευαστη. Η κλαση επιτιγχανει γραφικη διεπαφει με τον χρηστη.
 *  @author K.Stafylidis - P.Daratzis
 *  @version 2.0
 */
public class Authenticate {
    /**
     *
     * @param dataLoader ειναι ενα ορισμα τυπου DataLoader
     * Ο κατασκευαστης της κλασης δεχεται ενα ορισμα τυπου DataLoader και καλει την
     * συναρτηση start οπου αυτο το ορισμα το δινει ως παραμετρο στη συναρτηση start.
     */

    public Authenticate(DataLoader dataLoader){
        start(dataLoader);
    }

    /**
     *
     * @param dataLoader ειναι ενα ορισμα τυπου DataLoader.
     * Η συναρτηση start δεχεται ως παραμετρο ενα ορισμα τυπου dataLoader. Με την συναρτηση start
     * επιτιγχανεται η γραφικη διεπαφη με τον χρηστη. Μεσα στην συναρτηση διμιουργειται το πρωτο παραθυρο που βλεπει
     * ο χρηστης οπου ειναι και του παραθυρο επικοινονιας μαζι του οταν ανοιγει την εφαρμογη οπου τον δινεται η δυνατοτητα
     * να επιλεξει αναμεσα σε τρια κουπια (Login,Signup,Terminate). Αναλογα με το τι κουμπι θα επιλεξει ο χρηστης ακουλουθει
     * η καταλληλη ενεργεια.
     *
     */

    public void start(DataLoader dataLoader){

        JFrame frame = new JFrame("Login");
        frame.setLocationRelativeTo(null);

        frame.setSize(new Dimension(600,300));
        Container contentPane = frame.getContentPane();
        contentPane.setSize(new Dimension(600,300));
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        int top = 10, left = 10, bottom = 10, right = 10;
        g.insets = new Insets(top, left, bottom, right);

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 3;
        JLabel label = new JLabel("Welcome to the Awesome Booking App");
        label.setForeground(Color.red);
        label.setFont(new Font("Calibri", Font.ITALIC, 20));
        contentPane.add(label,g);

        g.gridwidth = 1;
        g.gridy = 1;
        JButton verify = new JButton("Login");
        ActionListener login = e -> {
            new Login(dataLoader);
            frame.dispose();
        };
        verify.addActionListener(login);
        contentPane.add(verify, g);
        g.gridx = 1;
        JButton cancel = new JButton("Sign Up");
        ActionListener cancelAction = e -> {
            frame.dispose();
            new SignUp(dataLoader);
        };
        cancel.addActionListener(cancelAction);

        contentPane.add(cancel, g);
        g.gridx =2;
        JButton terminate = new JButton("Terminate program");
        ActionListener terminateAction = e -> {
            try{
                dataLoader.writeAllToFile();
                frame.dispose();
            }
            catch(IOException ioException){
                System.out.println("Something went wrong");
            }
        };
        terminate.addActionListener(terminateAction);
        contentPane.add(terminate, g);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }



}
