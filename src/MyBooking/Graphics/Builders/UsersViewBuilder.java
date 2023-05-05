package MyBooking.Graphics.Builders;

import MyBooking.Users.Users;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση εχει ενα πεδιο το οποιο ειναι τυπου GridBagConstraints. Η κλαση διμιουργει
 * συνδεση με τον χρηστη μεσω γραφικων διεπαφων. Σκοπος της κλασης ειναι η εμφανιση και αναζητηση
 * και η επεξεργασια των χρηστων στην οθονη του υπολογιστη.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class UsersViewBuilder {
    GridBagConstraints g;
    /**
     *
     * @param currentUsers ειναι παραμετρος με τυπο δεδομενων Users και ειναι ArrayList.
     * H μεθοδος εμφανιζει στην οθονη μια λιστα με του users που που ειναι γραμενοι στην εφαρμογη.Η εμφανιση των
     * users στην οθονη επιτιγχανεται μεσω γραφικων διεπαφων που δημιουργουνται στη μεθοδο.Η μεθοδος δεχεται ως ορισμα
     * το ArrayList των users και με την χρηση γραφικων διεπαφων και τις συναρτησης getUserLabel φορτωνει
     * την λιστα με τους users σε γραφικη μορφη στο παραθυρο επικοινονιας του χρηστη.
     * @return scrollPane ειναι τυπου δεδομενων JScrollPane.
     */
    public JScrollPane getUsersScrollPane(ArrayList<Users> currentUsers){
        JPanel temp = new JPanel();
        temp.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.insets = new Insets(5,0,5,0);
        g.gridwidth = 10;
        g.gridheight = 3;
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        currentUsers.stream().filter(user -> !user.isDeleted()).forEach(user -> temp.add(getUserLabel(user),g));
        temp.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        long count = currentUsers.stream().filter(user -> !user.isDeleted()).count();

        if (count < 5) {
            for (int i = 0; i < 5-count; i++) {
                g.gridy+=3;
                JPanel panel = new JPanel();

                temp.add(panel,g);
            }
        }
        JScrollPane scrollPane = new JScrollPane(temp);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setPreferredSize(new Dimension(300, 400));
        return scrollPane;
    }
    /**
     *
     * @param pendingUsers ειναι τυπου δεδομενων Users και ειναι ArrayList.
     * Η μεθοδος χρησιμοποιηται για τη δημιουργια ενος ComboBox.
     * @return επιστρεφει ενα αντικειμενο JComboBox το οποιο θα προστεθει στο
     * παραθυρο επικοινονιας με τον χρηστη.
     */
    public JComboBox getPendingBox(ArrayList<Users> pendingUsers){
        String[] temp1 = new String[pendingUsers.size()];
        int count =0;
        for(Users user : pendingUsers){
            temp1[count] = user.getId()+" "+user.getLoginName();
            count++;
        }
        return new JComboBox(temp1);
    }
    /**
     *
     * @param user ειναι παραμετρος με τυπο δεδομενων Users.
     * Η getUserLabel ειναι μια ειδιωτικη μεθοδος οπου χρησιμοποιηται για να
     * δημιουργησει την προβολη ενος χρηστη μαζι με τα χαρακτηριστικα του(Name,Address,Type)
     * στο παραθυρο επικοινονιας με τον χρηστη μεσω γραφικων διεπαφων.Αυτη η μεθοδος
     * ειναι ιδιοτικη διοτι εχει πολυ συγκεκριμενη χρηση(Καλειται απο την getUsersScrollPane).
     * @return UserPanel ειναι τυπου δεδομενων JPanel.
     */
    private JPanel getUserLabel(Users user){
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(3,0));
        JLabel name = new JLabel(" Name: " + user.getLoginName());
        userPanel.add(name);
        JLabel address = new JLabel(" Address: "+user.getAddress());
        userPanel.add(address);
        userPanel.add(new JLabel(" Type: "+user.getType()));
        userPanel.setPreferredSize(new Dimension(260,75));
        userPanel.setBackground(new Color(214,189,230));
        userPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED),"ID: "+user.getId()));
        g.gridy += 3;
        return userPanel;
    }
}