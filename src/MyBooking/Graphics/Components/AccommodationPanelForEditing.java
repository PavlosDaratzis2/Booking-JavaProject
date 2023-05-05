package MyBooking.Graphics.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 *  Η κλαση AccommodationPanelForEditing δημιουργει γραφικη επαφη με το χρηστη.
 *  Σκοπος της ειναι να δημιουργησει την φορμα επεξεργασιας των καταληματων και μια
 *  φορμα προσθηκης καταληματων οταν ο χρηστης πατησει ενα απο τα κουμπια(edid Accommodation,
 *  Add new Accommodation).  Η κλαση εχει 15 πεδια.
 *  @author K.Stafylidis - P.Daratzis.
 *  @version 2.0
 */
public class AccommodationPanelForEditing {
    JPanel panel, types;
    JComboBox comboBox;
    JTextField name, priceInput, sqmInput;
    JCheckBox wifi, parking, breakfast, longterm;
    JButton ok, cancel, addImage;
    ButtonGroup type;
    GridBagConstraints g;
    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται καποιο ορισμα.Στον κατασκευαστη
     * της κλασης επιτυγχανεται η γραφικη διεπαφη με το χρηστη. Στον κατασκευαστη
     * της κλασης διμιουρηται ενα panel που ειναι ουσιαστικα το παραθυρο επικοινονιας με
     * τον χρηστη. Ακομη διμιουργονται Jlabel , JTextField ωστε ο χρηστης να μπορει να πληκτρολογησει
     * της απαντησεις του και JButton που ειναι τα κουμπια με τα οποια ο χρηστης χειριζεται το παραθυρο
     * και JCheckBox.
     */
    public AccommodationPanelForEditing(){
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridy = 1;
        g.gridx = 0;
        panel.add(new JLabel("Name"),g);

        g.gridx = 1;
        name = new JTextField();
        panel.add(name,g);

        g.gridy = 2;
        g.gridx = 0;
        panel.add(new JLabel("Price"),g);

        g.gridx = 1;
        priceInput = new JTextField();
        panel.add(priceInput, g);

        g.gridy = 3;
        g.gridx = 0;
        panel.add(new JLabel("Square Meters"),g);

        g.gridx = 1;
        sqmInput = new JTextField();
        panel.add(sqmInput, g);

        g.gridy = 4;
        g.gridx = 0;
        panel.add(new JLabel("Wifi"),g);

        g.gridx = 1;
        wifi = new JCheckBox();
        panel.add(wifi,g);

        g.gridy = 5;
        g.gridx = 0;
        panel.add(new JLabel("Parking"),g);

        g.gridx = 1;
        parking = new JCheckBox();
        panel.add(parking,g);

        g.gridy = 6;
        g.gridx = 0;
        panel.add(new JLabel("Breakfast"),g);

        g.gridx = 1;
        breakfast = new JCheckBox();
        panel.add(breakfast,g);

        g.gridy = 7;
        g.gridx = 0;
        panel.add(new JLabel("Long Term"),g);

        g.gridx = 1;
        longterm = new JCheckBox();
        panel.add(longterm,g);

        g.gridx = 0;
        g.gridy = 8;
        addImage = new JButton("Add image");
        panel.add(addImage,g);
        panel.repaint();

        g.gridy = 9;
        g.gridx = 0;
        ok = new JButton("OK");
        panel.add(ok,g);

        g.gridx = 1;
        cancel = new JButton("Cancel");
        panel.add(cancel,g);

    }
    /**
     * Η μεθοδος επιστεφει το αντικειμενο panel που ειναι τυπος
     * δεδομενων JPanel.
     * @return panel
     */
    public JPanel getPanel(){
        return panel;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να εισαγει ολες τις τιμες που πλκτρολογησε
     * ή που επελξε ο χρηστης σε μια List.
     * @return list που περιεχει τις επιλογες του χρηστη.
     */
    public ArrayList<String> getTextFromTexts(){
        ArrayList<String> list = new ArrayList<>();
        list.add(name.getText());
        list.add(priceInput.getText());
        list.add(sqmInput.getText());
        list.add(String.valueOf(wifi.isSelected()));
        list.add(String.valueOf(parking.isSelected()));
        list.add(String.valueOf(breakfast.isSelected()));
        list.add(String.valueOf(longterm.isSelected()));
        return list;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να εισαγει τα κουμπια επικοινιας με τον χρηστη
     * που χρειαζονται στο παραθυρο(ok,Add image,Cancel) σε μια List.
     * @return list
     */
    public ArrayList<JButton> getButtons(){
        ArrayList<JButton> list = new ArrayList<>();
        list.add(ok);
        list.add(cancel);
        list.add(addImage);
        return list;
    }
    /**
     * Ειναι μια μεθοδος που επιστρεφει ενα αντικειμενο JComboBox
     * @return comboBox
     */
    public JComboBox getComboBox(){
        return this.comboBox;

    }
    /**
     * Η μεθοδος getTypes ειναι μια ιδιωτικη μεθοδος και χρησιμοποιηται για
     * να δημιουργησει τα κουμπια για την γραγικη διεπαφη των τριων τυπων καταλυματων.
     * Η μεθοδος δημιουργει ενα ButtonGroup και πρωσθετει τα κουμπια εκει. Στο τελος επιστρεφει
     * ενα αντικειμενο JPanel
     * @return typeAcc που ειναι αντικειμενο JPanel.
     */
    private JPanel getTypes(){
        JPanel typeAcc = new JPanel();
        typeAcc.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        type = new ButtonGroup();
        g.gridy = 0;
        g.gridx = 0;
        JRadioButton hotel = new JRadioButton("Hotel");
        hotel.setSelected(true);
        type.add(hotel);
        typeAcc.add(hotel,g);

        g.gridx = 1;
        JRadioButton room = new JRadioButton("Room");
        type.add(room);
        typeAcc.add(room,g);

        g.gridx = 2;
        JRadioButton house = new JRadioButton("House");
        type.add(house);
        typeAcc.add(house,g);

        return typeAcc;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να προσθεσει για να προσθεσει στο παραθυρο επικοινονιας με το χρηστη
     * οπου επιτυγχανεται η γραφικηη διεπαφει τα radioButtons για την επιλογη καταλυματος.
     */
    public void getAdd(){
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.insets = new Insets(10,10,10,10);
        types = getTypes();
        panel.add(types,g);
        types.setVisible(true);


    }
    /**
     *
     * @param comboBox ειναι ενα ορισμα με τυπο δεδομενων JComboBox
     * Η μεθοδος χρησιμοποιηται για να προσθεσει ενα comboBox στο panel
     * επικοινονιας με το χρηστη.
     *
     */
    public void getEdit(JComboBox comboBox){
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10,10,10,10);
        panel.add(new Label("Accommodation"));
        this.comboBox = comboBox;
        g.gridx = 1;
        panel.add(this.comboBox,g);
        this.comboBox.setVisible(true);
        panel.repaint();
    }
    /**
     * Η μεθοδος χρησιμοποιηται για τον ελεγχο του τυπου (Hotel,House,Room) ενος accommodation
     * που προστιθεται στο προγραμμa και επιστρεφει το button απο τον τυπο καταλυματος που επιλεχθηκε
     * @return απο το πλαισιο των radioΒutton της επιλογης του καταληματος επιστρεφει αυτο που ειναι επιλεγμενο ή
     * null αν δεν εναι επιλεγμενο.
     */
    public String getTypeSelected(){
        for (Enumeration<AbstractButton> buttons = type.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }
    /**
     *
     * @param string ειναι ενα ορισμα τυπου String.
     * Η μεθοδος χρησιμοποιηται για να αλλαξει τις τιμες
     * που εχει δωσει ο χρηστης.
     */
    public void setJTexts(String string){
        String[] items = string.split("\\|");
        name.setText(items[1]);
        priceInput.setText(items[2]);
        sqmInput.setText(items[3]);
        wifi.setSelected(Boolean.parseBoolean(items[4]));
        parking.setSelected(Boolean.parseBoolean(items[5]));
        breakfast.setSelected(Boolean.parseBoolean(items[6]));
        longterm.setSelected(Boolean.parseBoolean(items[7]));
        panel.repaint();
    }
}