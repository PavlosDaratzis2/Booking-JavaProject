package MyBooking.Graphics.Builders;

import MyBooking.Accomodation.Accomodation;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση εχει ενα πεδιο το οποιο ειναι τυπου GridBagConstraints. Η κλαση διμιουργει
 * συνδεση με τον χρηστη μεσω γραφικων διεπαφων. Σκοπος της κλασης ειναι η εμφανιση και αναζητηση
 * των καταληματων στην οθονη του υπολογιστη.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class AccommodationsViewBuilder {
    GridBagConstraints g;
    /**
     *
     * @param accommodations ειναι παραμετρος τυπου Accommodation και ειναι ArrayList.
     * Η μεθοδος εμφανιζει στην οθονη τα καταληματα που ο χρηστης επιθυμει. Η εμφανισει των
     * καταλληματων στην οθονη επιτιγχανεται μεσω γραφικων διεπαφων που δημιουργουνται στη μεθοδο.
     * Η μεθοδος δεχεται ως ορισμα το ArrayList των καταλυματων και με την χρηση γραφικων διεπαφων και
     * τις συναρτησης getAccommodationLabel φορτωνει αυτα τα καταληματα σε γραφικη μορφη στον παραθυρο
     * επικοινινιας του χρηστη.
     * @return scrollPane που ειναι τυπου δεδομενων JScrollPane
     */
    public JScrollPane getAccommodationScrollPane(ArrayList<Accomodation> accommodations){
        JPanel temp = new JPanel();
        temp.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.insets = new Insets(5,0,5,0);
        g.gridwidth = 10;
        g.gridheight = 5;
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        accommodations.forEach(accommodation -> temp.add(getAccommodationLabel(accommodation),g));
        temp.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        long count = accommodations.stream().filter(accomodation -> !accomodation.isDeleted()).count();

        if (count < 5) {
            for (int i = 0; i < 6; i++) {
                JPanel panel = new JPanel();
                temp.add(panel);
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
     * @param accommodation ειναι παραμετρος με τυπο δεδομενων Accommodation.
     * Η getAccommodationLabel ειναι μια ειδιωτικη μεθοδος οπου χρησιμοποιηται για να
     * δημιουργησει την προβολη ενος καταληματος με την φωτογραφια του αν υπαρχει και
     * τα χαρακτηριστικα του στο παραθυρο επικοινονιας με τον χρηστη μεσω γραφικων διεπαφων. Αυτη η μεθοδος
     * ειναι ιδιοτικη διοτι εχει πολυ συγκεκριμενη χρηση(Καλειται απο την getAccommodationScrollPane).
     * @return Επιστρεφει ενα αντικειμενο με τυπο δεδομενων JPanel οπου στην ουσια
     * αυτο μεσα του περιεχει την φωτογραφια και τα χαρακτηριστικα ενος καταλυματος.
     */
    private JPanel getAccommodationLabel(Accomodation accommodation){
        JPanel all = new JPanel();
        all.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 7;
        gbc.insets = new Insets(0,3,2,3);
        JLabel image = new JLabel(new ImageIcon(accommodation.getAccommodationImage()));
        all.add(image,gbc);

        JPanel accommodationPanel = new JPanel();

        accommodationPanel.setName(accommodation.getId());
        accommodationPanel.setLayout(new GridLayout(7,0));

        JLabel accommodationName = new JLabel(accommodation.getName());
        accommodationPanel.add(accommodationName);
        JLabel sqm = new JLabel("Surface: "+accommodation.getMeter2()+" Sqm");
        accommodationPanel.add(sqm);
        JLabel wifi = new JLabel("WiFi: "+ booleanToString(accommodation.getWifi()));
        accommodationPanel.add(wifi);
        JLabel parking = new JLabel("Parking: " + booleanToString(accommodation.getParking()));
        accommodationPanel.add(parking);
        JLabel breakfast = new JLabel("Breakfast: " + booleanToString(accommodation.getBreakfast()));
        accommodationPanel.add(breakfast);
        JLabel longterm = new JLabel("Longterm: " + booleanToString(accommodation.isLongTerm()));
        accommodationPanel.add(longterm);
        JLabel price = new JLabel("Price: "+ accommodation.getPrice() + " Euro");
        accommodationPanel.add(price);
        accommodationPanel.setPreferredSize(new Dimension(260,100));
        all.setBackground(new Color(153, 204, 255));
        accommodationPanel.setBackground(new Color(153, 204, 255));
        all.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED),"ID: "+accommodation.getId()));

        gbc.gridy = 0;
        gbc.gridx = 4;
        gbc.gridwidth = 5;
        gbc.gridheight = 7;
        all.add(accommodationPanel, gbc);
        g.gridy += 7;
        return all;
    }
    /**
     *
     * @param bool ειναι παραμετρος τυπου boolean.
     * Η μεθοδος δεχεται μια παραμετρο τυπου boolean και αναλογα με
     * το αν ειναι true ή false την μετατρεπη σε Yes ή No αντιστοιχα.
     * @return Επιστρεφει μια συμβολοσειρα String(Yes/No)
     */
    private String booleanToString(boolean bool){
        if(bool){return "Yes";}else{return "No";}
    }
    /**
     *
     * @param accommodations ειναι παραμετρος με τυπο δεδομενων Accommodation και ειναι ArrayList.
     * Η μεθοδος χρησιμοποιηται για τη δημιουργια ενος ComboBox οπου ο χρηστης θα μπορει να επιλεγει
     * τα χαρακτηριστικα του καταληματος του(Wifi,Parking,Breakfast,Long Term)οταν θα κανει αναζητηση καταλυματων.
     * @return επιστρεφει ενα αντικεμενο JComboBox.
     */
    public JComboBox getAccommodationBox(ArrayList<Accomodation> accommodations){
        String[] temp1 = new String[accommodations.size()];
        int count =0;
        for(Accomodation accomodation : accommodations){
            temp1[count] = accomodation.getId()+" "+accomodation.getName();
            count++;
        }
        return new JComboBox(temp1);
    }
}