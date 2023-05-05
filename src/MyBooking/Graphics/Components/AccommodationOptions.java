package MyBooking.Graphics.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση AccommodationOptions δημιουργει γραφικη επαφη με το χρηστη. Σκοπος της ειναι
 * να δημιουρησει το μενου αναζητητης των καταλυματων ετσι ωστε ο χρηστης να μπορει να κανει
 * αναζητηση καταλυματων. Η δημιουργια του μενου αναζητησης επιτιγχανεται μεσω γραφικων διεπαφων
 * Η κλαση εχει 13 πεδια.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class AccommodationOptions extends JPanel {
    /**
     * accommodationName εχει τυπο δεδομενων JTextField,maxPrice εχει τυπο δεδομενων JTextField,
     * minPrice εχει τυπο δεδομενων JTextField.
     */
    JTextField accommodationName, maxPrice, minPrice;
    /**
     *  parking εχει τυπο δεδομενων JCheckBox,wifi εχει τυπο δεδομενων,breakfast  εχει τυπο δεδομενων JCheckBox,
     *  longTerm εχει τυπο δεδομενων JCheckBox
     *
     */
    JCheckBox parking, wifi, breakfast, longTerm;
    /**
     * searchAccommodations εχει τυπο δεδομενων JButton,resetAccommodations εχει τυπο δεδομενων JButton
     * bookSelection εχει τυπο δεδομενων JButton,editAccommodation εχει τυπο δεδομενων JButton,addAccommodation
     * εχει τυπο δεδομενων JButton,deleteAccommodation εχει τυπο δεδομενων JButton.
     */
    JButton searchAccommodations, resetAccommodations, bookSelection, editAccommodation, addAccommodation, deleteAccommodation;
    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται καποιο ορισμα. Στον κατασκευαστη
     * της κλασης επιτυγχανεται η γραφικη διεπαφη με το χρηστη. Στον κατασκευαστη δημιουργουνται
     * τa JLabel, τα JCheckBox, τα JTextField, τα JButton και δημιουργηται το παραθυρο επικοινονιας
     * με τον χρηστη.
     *
     */
    public AccommodationOptions () {
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx = 0;
        g.gridy = 0;
        add(new JLabel("Search Options"), g);

        g.gridy = 1;
        g.gridx = 0;
        add(new JLabel("Accom. Name:"), g);

        g.gridx = 1;
        accommodationName = new JTextField();
        add(accommodationName, g);

        g.gridy = 2;
        g.gridx = 0;
        g.gridheight = 1;
        add(new JLabel("Wifi"), g);
        g.gridx = 1;
        g.gridheight = 1;
        wifi = new JCheckBox();
        add(wifi, g);
        g.gridy = 3;
        g.gridx = 0;
        add(new JLabel("Parking: "), g);
        g.gridx = 1;
        g.gridheight = 1;
        parking = new JCheckBox();
        add(parking, g);

        g.gridy = 4;
        g.gridx = 0;
        add(new JLabel("Breakfast:"), g);

        g.gridx = 1;
        breakfast = new JCheckBox();
        add(breakfast, g);

        g.gridy = 5;
        g.gridx = 0;
        add(new JLabel("Long Term"),g);

        g.gridx = 1;
        longTerm = new JCheckBox();
        add(longTerm, g);

        g.gridy = 6;
        g.gridx = 0;
        add(new JLabel("Max Price:"),g);

        g.gridx = 1;
        maxPrice = new JTextField();
        add(maxPrice,g);

        g.gridy = 7;
        g.gridx = 0;
        add(new JLabel("Min Price:"),g);

        g.gridx = 1;
        minPrice = new JTextField();
        add(minPrice,g);

        g.gridy = 8;
        g.gridx = 0;
        searchAccommodations = new JButton("Search");
        add(searchAccommodations, g);

        g.gridx = 1;
        resetAccommodations = new JButton("Reset");
        add(resetAccommodations, g);

        g.gridy = 9;
        g.gridx = 0;
        g.gridwidth = 2;
        g.insets = new Insets(30,0,0,0);
        bookSelection = new JButton("Book from search results");
        add(bookSelection,g);

        editAccommodation = new JButton("Edit Accommodation");
        editAccommodation.setVisible(false);
        add(editAccommodation, g);

        g.gridy = 10;
        addAccommodation = new JButton("Add new Accommodation");
        addAccommodation.setVisible(false);
        add(addAccommodation, g);

        g.gridy = 11;
        deleteAccommodation = new JButton("Delete Accommodation");
        deleteAccommodation.setVisible(false);
        add(deleteAccommodation,g);
    }
    /**
     * Η μεθοδος αυτη χρησιμοποιηται για να επιστρεψει μια List με τα δεδομενα που εχει δοσει ο χρηστης.
     * @return list ειναι μια λιστα που περιχει τα δεδομενα που εχει δωσει ο χρηστης.
     */
    public ArrayList<String> getJTextText(){
        ArrayList<String> list = new ArrayList<>();
        list.add(accommodationName.getText());
        list.add(maxPrice.getText());
        list.add(minPrice.getText());
        list.add(String.valueOf(longTerm.isSelected()));
        list.add(String.valueOf(wifi.isSelected()));
        list.add(String.valueOf(parking.isSelected()));
        list.add(String.valueOf(breakfast.isSelected()));
        return list;
    }
    /**
     * Ειναι μια μεθοδος που επιστρεφει ολα τα κουμπια σε μια List.
     * @return list ειναι μια λιστα που περιεχει τα κουμπια.
     *
     * */
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> list = new ArrayList<>();
        list.add(searchAccommodations);
        list.add(resetAccommodations);
        list.add(bookSelection);
        list.add(addAccommodation);
        list.add(editAccommodation);
        list.add(deleteAccommodation);
        return list;
    }
    /**
     * Ειναι μια μεθοδος που καθαριζει τη φορμα στην οποια
     * εχει γραψει ο χρηστης.
     */
    public void clearFields(){
        accommodationName.setText(null);
        maxPrice.setText(null);
        minPrice.setText(null);
        longTerm.setSelected(false);
        wifi.setSelected(false);
        parking.setSelected(false);
        breakfast.setSelected(false);

    }
    /**
     * ειναι μια μεθοδος που εχει σκοπο να διαχιριζεται
     * την οροτητα των κουμπιων στους χρηστες που εχουν
     * την ιδιοτητα Provider.
     */
    public void getProviderButtons(){
        bookSelection.setVisible(false);
        editAccommodation.setVisible(true);
        addAccommodation.setVisible(true);
        deleteAccommodation.setVisible(true);
    }
}