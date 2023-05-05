package MyBooking.Graphics;

import MyBooking.Graphics.Builders.AccommodationsViewBuilder;
import MyBooking.Graphics.Builders.MessageViewBuilder;
import MyBooking.Graphics.Builders.ReservationsViewBuilder;
import MyBooking.Graphics.Components.AccommodationOptions;
import MyBooking.Graphics.Components.ReservationOptions;
import MyBooking.Graphics.Components.Tab;
import MyBooking.UserInterface.ClientInterface;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class creates the GUI interface for the User type Client. It uses the components (buttons, labels  etc.) and view
 * builders from the packages inside the package Graphics. Specifically AccommodationOptions, ReservationOptions,
 * AccommodationViewBuilder, ReservationsViewBuilder and MessageViewBuilder.
 * It handles the GUI of the program and creates all the action Listeners needed.
 * It also uses the class ClientInterface to get the methods attached to the action Listeners.
 * The GUI has two Tabs and each Tab is separated in two parts. One with buttons and labels made from the package components
 * and one with a JScrollPane that is responsible for the presentation of the contents of each ArrayList of objects
 * (Reservations, Accommodations and Messages)
 * AccommodationOptions is used for importing the components of the Accommodation Tab.
 * ReservationOptions is used for importing the components of the Reservations Tab.
 * MessageView is placed inside the Reservation Tab since it consists of only one button (GetMessages)
 * @author Kostas Stafylidis - Paul Daratzis
 * @version 1.0
 *
 */
public class ClientView extends UserView{
    private final ClientInterface clientInterface;
    private Tab accommodationsTab, reservationsTab;
    private ReservationOptions reservationOptions;
    private AccommodationOptions accommodationOptions;
    private JComboBox comboBox;
    private JFrame bookingFrame, cancelReservation;

    /**
     * The constructor of the class. The GUI is created using the common components from the superClass and continues
     * the building. The tabs Reservations and Accommodations is created and the appropriate components are set in place. Also, the
     * ActionListeners for each active component is created. Methods from the class ClientInterface are used in the
     * ActionListeners.
     * @param clientInterface the class containing the Client methods for the Action Listeners of the GUI
     */
    public ClientView(ClientInterface clientInterface){
        super();
        this.clientInterface = clientInterface;

        createReservationsTab();
        createAccommodationTab();

        tabbedPane.addTab("Accommodations", null, accommodationsTab);
        tabbedPane.addTab("Reservations", null, reservationsTab);

        userDetails.setText(clientInterface.currentUserStringForLabel());
        logOut.addActionListener(e -> {
            frame.dispose();
            clientInterface.logOut();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * A private method to create the Reservations Tab of the GUI. It inserts the Action Listeners to each component.
     */
    private void createReservationsTab(){
        reservationsTab = new Tab();
        reservationOptions = new ReservationOptions();
        reservationOptions.setClientReservationOptions();
        addActionListenersReservationsOptions(reservationOptions.getButtonsReservationOptions(), reservationOptions.getTextFieldsUserOptions());
        //reservationsTab.addViewPanel(scrollPane);
        updateReservationsView("Reservations");
        reservationsTab.addOptionsPanel(reservationOptions);
        tabbedPane.repaint();

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

    /**
     * A private method to update the Reservations currently presented in the GUI. Type: Reservations (all client reservations)
     * Cancellations (all client cancellations), search (the result of the search)
     * @param type the type of the ArrayList of Reservations to be presented
     */
    private void updateReservationsView(String type){
        reservationsTab.addViewPanel(new ReservationsViewBuilder().getReservationsScrollPane(clientInterface.getReservations(type)));
        tabbedPane.repaint();
    }

    /**
     * A private method to update the Accommodations currently presented in the GUI. Types are "All" (All Accommodations)
     * "search" the result of the search
     * @param type the type of the ArrayList of Accommodations to be presented
     */
    private void updateAccommodationView(String type){
        accommodationsTab.addViewPanel(new AccommodationsViewBuilder().getAccommodationScrollPane(clientInterface.getAccommodations(type)));
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
            listFields.get(1).setText(null);
            listFields.get(2).setText(null);
            clientInterface.searchReservations(listFields.get(1).getText(), listFields.get(2).getText(),listFields.get(0).getText(),listFields.get(3).getText().trim(),listFields.get(4).getText().trim());
            updateReservationsView("search");
        });
        list.get(1).addActionListener(e -> {
            reservationOptions.clearJTextFields();
            updateReservationsView("Reservations");
        });
        list.get(2).addActionListener(e -> {
            //calculate Statistics
        });
        list.get(3).addActionListener(e -> cancelReservation());list.get(6).addActionListener(e -> {
            reservationsTab.addViewPanel(new MessageViewBuilder().getMessagesScrollPane(clientInterface.getMessages()));
            tabbedPane.repaint();
        });



    }

    /**
     * A private method that creates the ActionListeners needed for the program to run properly. It takes as a parameter
     * an ArrayList of all the buttons the Accommodation Tab contains and an ArrayList of TextFields according to the
     * accommodation data (Provider name, Accommodation name etc.)
     * @param buttons ArrayList of the buttons that need to have an Action Listener
     * @param listFields an ArrayList of Text Fields containing the data of the Accommodation
     */
    private void addActionListenersAccommodationOptions(ArrayList<JButton> buttons, ArrayList<String> listFields){
        buttons.get(0).addActionListener(e -> {
            clientInterface.searchAccommodation(accommodationOptions.getJTextText());
            updateAccommodationView("search");
        });
        buttons.get(1).addActionListener(e -> {
            accommodationOptions.clearFields();
            clientInterface.setDefaultList();
            updateAccommodationView("All");
        });
        buttons.get(2).addActionListener(e -> {
            frame.setVisible(false);
            bookAccommodation();
        });

    }

    /**
     * A method to get the appropriate JOptionDialog for various uses in the program. The types of the OptionsDialog are:
     * PriceInt (Error Only Integer Numbers allowed), PriceBounds (Warning the number given is out of bounds),
     * DateBounds (Warning Invalid Input Date), DateInvalid (Warning Check in date is in the past), noSelection (Warning no
     * Accommodation selected), Confirmed (Information Booking Complete), PlaceBooked (Warning The Accommodation is booked
     * for the time period selected)
     * @param type the type of OptionsDialog needed
     */
    public void optionsDialog(String type){
        switch (type) {
            case "PriceInt" -> JOptionPane.showMessageDialog(frame, "Only Integer", "Number Error", JOptionPane.ERROR_MESSAGE);
            case "PriceBounds" -> JOptionPane.showMessageDialog(bookingFrame, "Input out of bounds", "Number Error", JOptionPane.WARNING_MESSAGE);
            case "DateBounds" -> JOptionPane.showMessageDialog(bookingFrame, "Wrong date and/or duration", "Date Error", JOptionPane.WARNING_MESSAGE);
            case "DateInvalid" -> JOptionPane.showMessageDialog(bookingFrame, "Check in field is invalid. Only future Reservations allowed", "Date Error", JOptionPane.WARNING_MESSAGE);
            case "noSelection" -> JOptionPane.showMessageDialog(bookingFrame, "Please select an Accommodation", "No Accommodation Selection", JOptionPane.WARNING_MESSAGE);
            case "Confirmed" -> JOptionPane.showMessageDialog(bookingFrame, "Booking Complete", "", JOptionPane.INFORMATION_MESSAGE);
            case "PlaceBooked" -> JOptionPane.showMessageDialog(bookingFrame, "Accommodation booked", "Sorry the accommodation is already booked", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * A private method that creates a new Frame for the implementation of the booking process of  Accommodation.
     */
    private void bookAccommodation(){
        bookingFrame = new JFrame("Booking");
        bookingFrame.setLocationRelativeTo(null);
        bookingFrame.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,10,5,10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        bookingFrame.add(new JLabel("Accommodation:"),g);

        g.gridx = 1;
        comboBox = new AccommodationsViewBuilder().getAccommodationBox(clientInterface.getAccommodations("search"));
        comboBox.setSelectedItem(null);
        JLabel duration = new JLabel("Duration");
        JLabel state = new JLabel();
        comboBox.addActionListener(e -> {
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
            if(clientInterface.longTerm(temp1)){
                duration.setText("Duration Months");
                state.setText("Long Term Lease");
            }
            else{
                duration.setText("Duration Nights");
                state.setText("Short Term Lease");
            }
            bookingFrame.pack();
            bookingFrame.repaint();
        });
        bookingFrame.add(comboBox,g);

        g.gridy = 1;
        g.gridx = 0;
        bookingFrame.add(new JLabel("Check in"),g);

        g.gridx = 1;
        JTextField checkIn = new JTextField("yyyy-mm-dd");
        bookingFrame.add(checkIn,g);

        g.gridy = 2;
        g.gridx = 0;
        g.gridwidth = 2;
        //JLabel state = new JLabel()
        bookingFrame.add(state,g);

        g.gridy = 3;
        g.gridx = 0;
        //JLabel duration = new JLabel();
        bookingFrame.add(duration,g);

        g.gridx = 1;
        JTextField durationText = new JTextField();
        bookingFrame.add(durationText, g);

        g.gridy = 4;
        g.gridx = 0;
        g.gridwidth = 1;
        JButton book = new JButton("Book");
        book.addActionListener(e -> {
            String temp = (String) comboBox.getSelectedItem();
            if(comboBox.getSelectedItem()==null){
                optionsDialog("noSelection");
                return;
            }
            String temp1 = "";
            if (temp != null) {
                for(int i = 0; i<temp.length();i++){
                    if(!String.valueOf(temp.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(temp.charAt(i));
                    }
                    else break;
                }
            }
            if(clientInterface.makeReservation(temp1, checkIn.getText(), durationText.getText())){
                bookingFrame.dispose();
                updateReservationsView("Reservations");
                frame.setVisible(true);
            }
        });
        bookingFrame.add(book,g);

        g.gridx = 1;
        JButton cancelBook = new JButton("Cancel");
        cancelBook.addActionListener(e -> {
            bookingFrame.dispose();
            frame.setVisible(true);
        });
        bookingFrame.add(cancelBook,g);

        bookingFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        bookingFrame.pack();
        bookingFrame.setVisible(true);
    }

    /**
     * A private method that creates a new Frame in order to complete the cancellation of a reservation that this user has
     * made
     */
    private void cancelReservation(){
        cancelReservation = new JFrame("Cancel Reservation");
        cancelReservation.setLocationRelativeTo(null);
        cancelReservation.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,10,5,10);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        cancelReservation.add(new JLabel("Reservation: "),g);

        g.gridx = 1;
        comboBox = new ReservationsViewBuilder().getReservationsBox(clientInterface.getReservations("Reservations"));
        cancelReservation.add(comboBox, g);
        g.gridy = 2;
        g.gridx = 0;
        g.gridwidth = 1;
        JButton cancelReservationButton = new JButton("Cancel Reservation");
        cancelReservationButton.addActionListener(e -> {
            String temp = (String) comboBox.getSelectedItem();
            if(comboBox.getSelectedItem()==null){
                optionsDialog("noSelection");
                //return;
            }
            else{
                String temp1 = "";
                if (temp != null) {
                    for(int i = 0; i<temp.length();i++){
                        if(!String.valueOf(temp.charAt(i)).equals(" ")){
                            temp1+=String.valueOf(temp.charAt(i));
                        }
                        else break;
                    }
                }
                clientInterface.makeCancellation(temp1);
                JOptionPane.showMessageDialog(cancelReservation,"Reservation cancelled", "", JOptionPane.INFORMATION_MESSAGE);
                updateReservationsView("Reservations");
                cancelReservation.dispose();
                frame.setVisible(true);
            }
        });
        cancelReservation.add(cancelReservationButton,g);

        g.gridx = 1;
        JButton cancelBook = new JButton("Return");
        cancelBook.addActionListener(e -> {
            cancelReservation.dispose();
            frame.setVisible(true);
        });
        cancelReservation.add(cancelBook,g);

        cancelReservation.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        cancelReservation.pack();
        frame.setVisible(false);
        cancelReservation.setVisible(true);
    }

}
