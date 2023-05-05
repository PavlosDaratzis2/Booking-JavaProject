package MyBooking.Graphics;

import MyBooking.Accomodation.Accomodation;
import MyBooking.Graphics.Builders.AccommodationsViewBuilder;
import MyBooking.Graphics.Builders.MessageViewBuilder;
import MyBooking.Graphics.Builders.ReservationsViewBuilder;
import MyBooking.Graphics.Components.AccommodationOptions;
import MyBooking.Graphics.Components.AccommodationPanelForEditing;
import MyBooking.Graphics.Components.ReservationOptions;
import MyBooking.Graphics.Components.Tab;
import MyBooking.ImageLoader;
import MyBooking.UserInterface.ProviderInterface;import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * The class creates the GUI interface for the User type Provider. It uses the components (buttons, labels  etc.) and view
 * builders from the packages inside the package Graphics. Specifically AccommodationOptions, ReservationOptions,
 * AccommodationViewBuilder, ReservationsViewBuilder and MessageViewBuilder.
 * It handles the GUI of the program and creates all the action Listeners needed.
 * It also uses the class ProviderInterface to get the methods attached to the action Listeners.
 * The GUI has two Tabs and each Tab is separated in two parts. One with buttons and labels made from the package components
 * and one with a JScrollPane that is responsible for the presentation of the contents of each ArrayList of objects
 * (Reservations, Accommodations and Messages)
 * AccommodationOptions is used for importing the components of the Accommodation Tab.
 * ReservationOptions is used for importing the components of the Reservations Tab.
 * MessageView is placed inside the Reservation Tab since it consists of only one button (GetMessages)
 * @author Kostas Stafylidis - Paul Daratzis
 * @version 1.0
 */
public class ProviderView extends UserView{
    private final ProviderInterface providerInterface;
    private Tab accommodationsTab, reservationsTab;
    private ReservationOptions reservationOptions;
    private AccommodationOptions accommodationOptions;
    private JComboBox comboBox;
    private JFrame addAccommodation, editAccommodation;
    private AccommodationPanelForEditing panel;

    /**
     * The constructor of the class. The GUI is created using the common components from the superClass and continues
     * the building. The tabs Reservations and Accommodations is created and the appropriate components are set in place. Also, the
     * ActionListeners for each active component is created. Methods from the class ProviderInterface are used in the
     * ActionListeners.
     * @param providerInterface the class containing the Provider methods for the Action Listeners of the GUI
     */
    public ProviderView(ProviderInterface providerInterface){
        super();
        this.providerInterface = providerInterface;

        createAccommodationTab();
        createReservationsTab();
        reservationOptions.setProviderReservationOptions();
        accommodationOptions.getProviderButtons();

        tabbedPane.addTab("Accommodations", null, accommodationsTab);
        tabbedPane.addTab("Reservations", null, reservationsTab);

        userDetails.setText(this.providerInterface.currentUserStringForLabel());
        logOut.addActionListener(e -> {
            frame.dispose();
            providerInterface.logOut();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     *  private method to create the Reservations Tab of the GUI. It inserts the Action Listeners to each component.
     */
    private void createReservationsTab(){
        reservationsTab = new Tab();
        reservationOptions = new ReservationOptions();
        reservationOptions.setProviderReservationOptions();
        addActionListenersReservationsOptions(reservationOptions.getButtonsReservationOptions(), reservationOptions.getTextFieldsUserOptions());
        //reservationsTab.addViewPanel(scrollPane);
        updateReservationsView("Reservations");
        reservationsTab.addOptionsPanel(reservationOptions);
        tabbedPane.repaint();

    }

    /**A private method to update the Reservations currently presented in the GUI. Type: Reservations (all provider's accommodations reservations)
     * Cancellations (all provider's accommodations cancellations), search (the result of the search)
     * @param type the type of the ArrayList of Reservations to be presented
     */
    public void updateReservationsView(String type){
        reservationsTab.addViewPanel(new ReservationsViewBuilder().getReservationsScrollPane(providerInterface.getReservations(type)));
        tabbedPane.repaint();
    }

    /**
     * A private method that creates the ActionListeners needed for the program to run properly. It takes as a parameter
     * an ArrayList of all the buttons the Reservation Tab contains and an ArrayList of TextFields according to the
     * reservation data (Provider name, Accommodation name etc.)
     * @param list ArrayList of the buttons that need to have an Action Listener
     * @param listFields an ArrayList of Text Fields containing the data of the Reservation
     */
    private void addActionListenersReservationsOptions(ArrayList<JButton> list, ArrayList<JTextField> listFields){
        list.get(0).addActionListener(e -> {

            if(listFields.get(3).getText().trim().equals("yyyy-mm-dd")){
                listFields.get(3).setText(null);
            }
            if(listFields.get(4).getText().trim().equals("yyyy-mm-dd")){
                listFields.get(4).setText(null);
            }
            providerInterface.searchReservations(listFields.get(1).getText(), listFields.get(2).getText(),listFields.get(0).getText(),listFields.get(3).getText().trim(),listFields.get(4).getText().trim());
            updateReservationsView("search");
        });
        list.get(1).addActionListener(e -> {
            reservationOptions.clearJTextFields();
            updateReservationsView("Reservations");
        });
        list.get(2).addActionListener(e -> JOptionPane.showMessageDialog(frame,"Total Earnings from Reservations "+ providerInterface.getStatistics() +" Euro", "My Earnings", JOptionPane.INFORMATION_MESSAGE));
        list.get(4).addActionListener(e -> {
            reservationOptions.clearJTextFields();
            updateReservationsView("Cancellations");
        });
        list.get(5).addActionListener(e -> {
            reservationOptions.clearJTextFields();
            updateReservationsView("Reservations");
        });
        list.get(6).addActionListener(e -> {
            reservationsTab.addViewPanel(new MessageViewBuilder().getMessagesScrollPane(providerInterface.getMessages()));
            tabbedPane.repaint();
        });
    }

    /**
     * A private method to create the Accommodations Tab of the GUI. It inserts the Action Listeners to each component.
     */
    private void createAccommodationTab(){
        accommodationsTab = new Tab();
        accommodationOptions = new AccommodationOptions();
        addActionListenersAccommodationOptions(accommodationOptions.getButtons(),accommodationOptions.getJTextText());
        updateAccommodationView("All");
        accommodationsTab.addOptionsPanel(accommodationOptions);
        tabbedPane.repaint();
    }

    /**A private method that creates the ActionListeners needed for the program to run properly. It takes as a parameter
     * an ArrayList of all the buttons the Accommodation Tab contains and an ArrayList of TextFields according to the
     * accommodation data (Provider name, Accommodation name etc.)
     * @param buttons ArrayList of the buttons that need to have an Action Listener
     * @param listFields an ArrayList of Text Fields containing the data of the Accommodation
     */
    private void addActionListenersAccommodationOptions(ArrayList<JButton> buttons, ArrayList<String> listFields){
        buttons.get(0).addActionListener(e -> {
            providerInterface.searchAccommodation(accommodationOptions.getJTextText());
            updateAccommodationView("search");
        });
        buttons.get(1).addActionListener(e -> {
            accommodationOptions.clearFields();
            providerInterface.setDefaultList();
            updateAccommodationView("All");
        });

        buttons.get(3).addActionListener(e -> addAccommodation());
        buttons.get(4).addActionListener(e -> editAccommodation());

        buttons.get(5).addActionListener(e -> {
            deleteAccommodation();
            providerInterface.setDefaultList();
            updateAccommodationView("All");
        });
    }

    /**
     * A private method to update the Accommodations currently presented in the GUI. Types are "All" (All Accommodations)
     * "search" the result of the search
     * @param type the type of the ArrayList of Accommodation to be presented
     */
    public void updateAccommodationView(String type){
        accommodationsTab.addViewPanel(new AccommodationsViewBuilder().getAccommodationScrollPane(providerInterface.getAccommodations(type)));
        tabbedPane.repaint();
    }

    /**
     * A method to get the appropriate JOptionDialog for various uses in the program. The types of the OptionsDialog are:
     * PriceInt (Error Only Integer Numbers allowed), WrongImage (Warning not the right kind of image only jpg allowed),
     * InvalidSymbol (Warming when the symbol | is used), DateInvalid (Warning Check in date is in the past), noSelection (Warning no
     * Accommodation selected), Confirmed (Information Booking Complete), PlaceBooked (Warning The Accommodation is booked
     * for the time period selected)
     * @param type the type of OptionsDialog needed
     */
    public void optionsDialog(String type){
        switch (type) {
            case "PriceInt" -> JOptionPane.showMessageDialog(frame, "Only Integer", "Number Error", JOptionPane.ERROR_MESSAGE);
            case "WrongImage" -> JOptionPane.showMessageDialog(frame, "No image or wrong type of image selected", "Image Error", JOptionPane.WARNING_MESSAGE);
            case "InvalidSymbol" -> JOptionPane.showMessageDialog(frame, "The symbol | is not allowed", "Invalid Symbol", JOptionPane.WARNING_MESSAGE);
            case "DateInvalid" -> JOptionPane.showMessageDialog(frame, "Check in field is invalid", "Date Error", JOptionPane.WARNING_MESSAGE);
            case "noSelection" -> JOptionPane.showMessageDialog(frame, "Please select an Accommodation", "No Accommodation Selection", JOptionPane.WARNING_MESSAGE);
            case "Confirmed" -> JOptionPane.showMessageDialog(frame, "Booking Complete", "", JOptionPane.INFORMATION_MESSAGE);
            case "PlaceBooked" -> JOptionPane.showMessageDialog(frame, "Accommodation booked", "Sorry the accommodation is already booked", JOptionPane.WARNING_MESSAGE);
            case "ImageChanged" -> JOptionPane.showMessageDialog(frame, "Image updated. Accommodation changes saved", "Information", JOptionPane.INFORMATION_MESSAGE);
            case "NoImageChanged" -> JOptionPane.showMessageDialog(frame, "Image not updated. Accommodation changes saved", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * A private method that is called when the Provider wishes to add a new Accommodation for rent. A new Frame is made
     * and the Provider should insert the information and the image of the Accommodation.
     */
    private void addAccommodation(){
        final File[] image = new File[1];
        addAccommodation = new JFrame();
        panel = new AccommodationPanelForEditing();
        //panel.setComboBox(new AccommodationsViewBuilder().getAccommodationBox(providerInterface.getAccommodations("All")));
        panel.getAdd();

        panel.getButtons().get(0).addActionListener(e -> {
            String accName = panel.getTextFromTexts().get(0);
            if(accName.contains("|")){
                //error
                optionsDialog("InvalidSymbol");
                System.out.println("contains |");
                return;
            }
            int price, sqm;
            try{
                price = Integer.parseInt(panel.getTextFromTexts().get(1));
                sqm = Integer.parseInt(panel.getTextFromTexts().get(2));
            }catch (NumberFormatException el){
                optionsDialog("PriceInt");
                return;
            }
            boolean wifi = Boolean.parseBoolean(panel.getTextFromTexts().get(3));
            boolean parking = Boolean.parseBoolean(panel.getTextFromTexts().get(4));
            boolean breakfast = Boolean.parseBoolean(panel.getTextFromTexts().get(5));
            boolean longTerm = Boolean.parseBoolean((panel.getTextFromTexts().get(6)));

            providerInterface.createNewAccommodations(panel.getTypeSelected(), accName, price, sqm, wifi, parking, breakfast, longTerm);

            if(new ImageLoader().readFileFromUser( image[0], providerInterface.getIdFromLast())){
                providerInterface.getAccommodations("All").stream().filter(accommodation -> accommodation.getId().equals(providerInterface.getIdFromLast())).forEach(Accomodation::updateImage);
                //accommodation created
            }
            else{
                optionsDialog("WrongImage");
                //System.out.println("The File specified is not an accepted Image");
                //failed
            }
            addAccommodation.dispose();
            updateAccommodationView("All");
            frame.setVisible(true);
        });
        panel.getButtons().get(1).addActionListener(e -> {
            addAccommodation.dispose();
            frame.setVisible(true);
        });
        panel.getButtons().get(2).addActionListener(e -> {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg"));
            int result = imageChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                image[0] = imageChooser.getSelectedFile();
            }
        });

        addAccommodation.add(panel.getPanel());
        addAccommodation.setLocationRelativeTo(null);
        addAccommodation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addAccommodation.pack();
        frame.setVisible(false);
        addAccommodation.setVisible(true);
    }

    /**
     * A private method to edit existing Accommodation. A new frame is created and within the Provider can change the
     * information of the chosen Accommodation.
     */
    private void editAccommodation(){
        final File[] image = new File[1];
        editAccommodation = new JFrame();
        panel = new AccommodationPanelForEditing();
        //panel.setComboBox(new AccommodationsViewBuilder().getAccommodationBox(providerInterface.getAccommodations("All")));
        comboBox = new AccommodationsViewBuilder().getAccommodationBox(providerInterface.getAccommodations("All"));
        panel.getEdit(comboBox);
        comboBox.addActionListener(e -> {
            String accId = (String) comboBox.getSelectedItem();
            String temp1 = "";
            if (accId != null) {
                for(int i = 0; i<accId.length();i++){
                    if(!String.valueOf(accId.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(accId.charAt(i));
                    }
                    else break;
                }
            }
            panel.setJTexts(providerInterface.getAccommodation(temp1).accommodationsToString());
        });

        panel.getButtons().get(0).addActionListener(e -> {
            String accName = panel.getTextFromTexts().get(0);
            if(accName.contains("|")){
                optionsDialog("InvalidSymbol");
                System.out.println("contains |");
                return;
            }
            int price, sqm;
            try{
                price = Integer.parseInt(panel.getTextFromTexts().get(1));
                sqm = Integer.parseInt(panel.getTextFromTexts().get(2));
            }catch (NumberFormatException el){
                optionsDialog("PriceInt");
                return;
            }
            boolean wifi = Boolean.parseBoolean(panel.getTextFromTexts().get(3));
            boolean parking = Boolean.parseBoolean(panel.getTextFromTexts().get(4));
            boolean breakfast = Boolean.parseBoolean(panel.getTextFromTexts().get(5));
            boolean longTerm = Boolean.parseBoolean((panel.getTextFromTexts().get(6)));

            String accId = (String) comboBox.getSelectedItem();
            String temp1 = "";
            if (accId != null) {
                for(int i = 0; i<accId.length();i++){
                    if(!String.valueOf(accId.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(accId.charAt(i));
                    }
                    else break;
                }
            }


            if(new ImageLoader().readFileFromUser( image[0], providerInterface.getIdFromLast())){
                providerInterface.getAccommodation(temp1).updateImage();
                optionsDialog("ImageChanged");
                //System.out.println("Image changed");
                //accommodation created
            }
            else{
                optionsDialog("NoImageChanged");
                //System.out.println("The File specified is not an accepted Image");
                //failed
            }

            providerInterface.editAccommodation(temp1,accName,price,sqm,wifi,parking,breakfast,longTerm);

            editAccommodation.dispose();
            updateAccommodationView("All");
            frame.setVisible(true);
        });
        panel.getButtons().get(1).addActionListener(e -> {
            editAccommodation.dispose();
            frame.setVisible(true);
        });
        panel.getButtons().get(2).addActionListener(e -> {
            JFileChooser imageChooser = new JFileChooser();
            imageChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg"));
            int result = imageChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                image[0] = imageChooser.getSelectedFile();
            }
        });

        editAccommodation.add(panel.getPanel());
        editAccommodation.setLocationRelativeTo(null);
        editAccommodation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        editAccommodation.pack();
        frame.setVisible(false);
        editAccommodation.setVisible(true);
    }

    /**
     * A private method to delete existing Accommodation that this Provider holds.
     */
    private void deleteAccommodation(){
        JFrame deleteAccommodation = new JFrame();
        deleteAccommodation.setLocationRelativeTo(null);
        deleteAccommodation.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridy = 0;
        g.gridx = 0;
        g.gridwidth = 2;
        comboBox = new AccommodationsViewBuilder().getAccommodationBox(providerInterface.getAccommodations("All"));
        deleteAccommodation.add(comboBox, g);

        g.gridy = 1;
        g.gridwidth = 1;
        JButton delete = new JButton("Delete");
        delete.addActionListener(e -> {
            String temp = (String) comboBox.getSelectedItem();
            String temp1 = "";
            if (temp != null) {
                for(int i = 0; i<temp.length();i++){
                    if(!String.valueOf(temp.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(temp.charAt(i));
                    }
                    else break;
                }
            }
            providerInterface.getAccommodation(temp1).setDeleted(true);
            String finalTemp = temp1;
            providerInterface.getReservations("All").stream().filter(reservation -> reservation.getAccomodationId().equals(finalTemp)).forEach(reservation -> reservation.setCanceled(true));
            deleteAccommodation.dispose();
            updateAccommodationView("All");
            updateReservationsView("All");
            frame.repaint();
            frame.setVisible(true);
        });
        deleteAccommodation.add(delete, g);

        g.gridx = 1;
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(e -> {
            deleteAccommodation.dispose();
            frame.setVisible(true);
        });
        deleteAccommodation.add(cancel, g);

        frame.setVisible(false);
        deleteAccommodation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        deleteAccommodation.pack();
        deleteAccommodation.setVisible(true);

    }
}
