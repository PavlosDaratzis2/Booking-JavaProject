package MyBooking.Graphics;

import MyBooking.Graphics.Builders.MessageViewBuilder;
import MyBooking.Graphics.Builders.ReservationsViewBuilder;
import MyBooking.Graphics.Builders.UsersViewBuilder;
import MyBooking.Graphics.Components.ReservationOptions;
import MyBooking.Graphics.Components.Tab;
import MyBooking.Graphics.Components.UserOptionsAdmin;
import MyBooking.UserInterface.AdminInterface;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * The class creates the GUI interface for the User type Admin. It uses the components (buttons, labels  etc.) and view
 * builders from the packages inside the package Graphics. Specifically ReservationOptions, UserOptionsAdmin
 * UsersViewBuilder, ReservationsViewBuilder and MessageViewBuilder.
 * It handles the GUI of the program and creates all the action Listeners needed.
 * It also uses the class AdminInterface to get the methods attached to the action Listeners.
 * The GUI has two Tabs and each Tab is separated in two parts. One with buttons and labels made from the package components
 * and one with a JScrollPane that is responsible for the presentation of the contents of each ArrayList of objects
 * (Reservations, Users etc.)
 * UserOptionsAdmin is used for importing the components of the Users Tab.
 * ReservationOptions is used for importing the components of the Reservations Tab.
 * MessageView is placed inside the Reservation Tab since it consists of only two buttons (GetMessages and SendMessage)
 * @author Kostas Stafylidis - Paul Daratzis
 * @version 1.0
 */
public class AdminView extends UserView{
    private final AdminInterface adminInterface;
    private JComboBox ids;
    private Tab userTab, reservationsTab;
    private UserOptionsAdmin userOptionsAdmin;
    private ReservationOptions reservationOptions;

    /**
     * The constructor of the class. The GUI is created using the common components from the superClass and continues
     * the building. The tabs Reservations and Users is created and the appropriate components are set in place. Also, the
     * ActionListeners for each active component is created. Methods from the class AdminInterface are used in the
     * ActionListeners.
     * @param adminInterface the class containing the Admin methods for the Action Listeners of the GUI
     */
    public AdminView(AdminInterface adminInterface){
        super();
        this.adminInterface = adminInterface;

        createUserTab();
        createReservationsTab();
        reservationOptions.setAdminReservationOptions();

        tabbedPane.addTab("Users", null, userTab);
        tabbedPane.addTab("Reservations", null, reservationsTab);

        userDetails.setText(adminInterface.currentUserStringForLabel());
        logOut.addActionListener(e -> {
            frame.dispose();
            adminInterface.logOut();
        });

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    /**
     * A private method to create the User Tab of the GUI. It inserts the Action Listeners to each component.
     */
    private void createUserTab(){
        userTab = new Tab();
        userOptionsAdmin = new UserOptionsAdmin();
        addActionsListenersUserOptions(userOptionsAdmin.getButtonsUserOptions());
        //userTab.addViewPanel(new UsersViewBuilder().getUsersScrollPane(adminInterface.getUsers2("Active")));
        updateUserView("Active");
        userTab.addOptionsPanel(userOptionsAdmin);
        tabbedPane.repaint();
    }

    /**
     * A private method to create the Reservations Tab of the GUI. It inserts the Action Listeners to each component.
     */
    private void createReservationsTab(){
        reservationsTab = new Tab();
        reservationOptions = new ReservationOptions();
        addActionListenersReservationsOptions(reservationOptions.getButtonsReservationOptions(), reservationOptions.getTextFieldsUserOptions());
        //reservationsTab.addViewPanel(scrollPane);
        updateReservationsView("Reservations");
        System.out.println("OK?");
        reservationsTab.addOptionsPanel(reservationOptions);
        tabbedPane.repaint();

    }

    /**
     * A private method to update the Users currently presented in the GUI.
     * @param type the type of the ArrayList of Users to be presented
     */
    private void updateUserView(String type){
        userTab.addViewPanel((new UsersViewBuilder().getUsersScrollPane(adminInterface.getUsers(type))));
        tabbedPane.repaint();
    }

    /**
     * A private method to update the Reservations currently presented in the GUI. Type: Reservations (all the reservations)
     * Cancellations (all the cancellations), search (the result of the search)
     * @param type the type of the ArrayList of Reservations to be presented
     */
    private void updateReservationsView(String type){
        reservationsTab.addViewPanel(new ReservationsViewBuilder().getReservationsScrollPane(adminInterface.getReservations2(type)));
        tabbedPane.repaint();
    }

    /**
     * A private method to update the Messages currently presented in the GUI.
     */
    private void updateMessagesView(){
        userTab.addViewPanel(new MessageViewBuilder().getMessagesScrollPane(adminInterface.getMessages()));
        tabbedPane.repaint();
    }

    /**
     * A private method that creates the ActionListeners needed for the program to run properly. It takes as a parameter
     * an ArrayList of all the buttons the User Tab contains.
     * @param list ArrayList of the buttons that need to have an Action Listener
     */
    private void addActionsListenersUserOptions(ArrayList<JButton> list){
        list.get(0).addActionListener(e -> {
            userOptionsAdmin.clearJTextFields();
            updateUserView("Active");
        });
        list.get(1).addActionListener(e -> {
            adminInterface.searchUsers(userOptionsAdmin.getTextFieldsUserOptions().get(0).getText(), userOptionsAdmin.getTextFieldsUserOptions().get(1).getText());
            updateUserView("search");
        });
        list.get(2).addActionListener(e -> {
            userOptionsAdmin.clearJTextFields();
            updateUserView("Active");
        });
        list.get(3).addActionListener(e -> {
            userOptionsAdmin.clearJTextFields();
            updateUserView("Pending");
        });
        list.get(4).addActionListener(e -> {
            if (adminInterface.noPendingUsers()) {
                optionsDialog("NoPendingUsers");
                return;
            }
            checkPendingUsers();
            frame.setVisible(false);
        });
        list.get(5).addActionListener(e -> updateMessagesView());
        list.get(6).addActionListener(e -> {
            sendMessage();
            frame.setVisible(false);
        });
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
            adminInterface.searchReservations(listFields.get(1).getText(), listFields.get(2).getText(),listFields.get(0).getText(),listFields.get(3).getText().trim(),listFields.get(4).getText().trim());
            updateReservationsView("search");
        });
        list.get(1).addActionListener(e -> {
            reservationOptions.clearJTextFields();
            //updateReservationsViewPanel(adminInterface.getReservations());
            updateReservationsView("Reservations");
        });
        list.get(2).addActionListener(e -> {
            //calculate Statistics
        });

    }

    /**
     * A private method that creates a new Frame for accepting or declining new Users (pending). It contains its own
     * action listeners and uses methods from the adminInterface
     */
    private void checkPendingUsers(){
        JFrame pendingFrame = new JFrame();
        pendingFrame.setLocationRelativeTo(null);
        pendingFrame.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.insets = new Insets(10,10,10,10);
        pendingFrame.add(new JLabel("Pending User ID:"),g);

        g.gridx = 1;
        g.gridwidth =2;
        ids = new UsersViewBuilder().getPendingBox(adminInterface.getUsers("Pending"));
        pendingFrame.add(ids,g);
        g.gridx =0;
        g.gridy =2;
        g.gridwidth = 1;
        JButton acceptPendingUser = new JButton("Accept");
        acceptPendingUser.addActionListener(e -> {
            String temp = (String) ids.getSelectedItem();
            String temp1 = "";
            if (temp != null) {
                for(int i = 0; i<temp.length();i++){
                    if(!String.valueOf(temp.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(temp.charAt(i));
                    }
                    else break;
                }
            }
            adminInterface.acceptPendingUser(temp1);
            pendingFrame.dispose();
            updateUserView("Pending");
            frame.setVisible(true);
        });
        pendingFrame.add(acceptPendingUser, g);
        g.gridx = 1;
        JButton deletePendingUser = new JButton("Delete");
        deletePendingUser.addActionListener(e -> {
            String temp = (String) ids.getSelectedItem();
            String temp1 = "";
            if (temp != null) {
                for(int i = 0; i<temp.length();i++){
                    if(!String.valueOf(temp.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(temp.charAt(i));
                    }
                    else break;
                }
            }
            adminInterface.deletePendingUser(temp1);
            pendingFrame.dispose();
            updateUserView("Pending");
            frame.setVisible(true);
        });
        pendingFrame.add(deletePendingUser,g);

        g.gridx = 2;
        JButton cancelPending = new JButton("Cancel");
        cancelPending.addActionListener(e -> {
            pendingFrame.dispose();
            frame.setVisible(true);
        });
        pendingFrame.add(cancelPending,g);
        pendingFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        pendingFrame.pack();
        pendingFrame.setVisible(true);
    }

    /**
     * A private method that creates a new Frame in order to send a message to a User. Methods used form AdminInterface.
     */
    private void sendMessage(){
        JFrame sendMessageFrame = new JFrame();
        sendMessageFrame.setLocationRelativeTo(null);
        sendMessageFrame.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(10,10,10,10);
        sendMessageFrame.add(new JLabel("Send to:"),g);

        g.gridx = 1;
        ids = new UsersViewBuilder().getPendingBox(adminInterface.getUsers("NoAdmin"));
        sendMessageFrame.add(ids,g);
        g.gridx =0;
        g.gridy =2;
        g.gridwidth = 2;
        JTextField messageText = new JTextField();
        sendMessageFrame.add(messageText,g);

        g.gridy=3;
        g.gridwidth = 1;
        JButton sendMessage = new JButton("Send");
        sendMessage.addActionListener(e -> {
            String temp = (String) ids.getSelectedItem();
            String temp1 = "";
            if (temp != null) {
                for(int i = 0; i<temp.length();i++){
                    if(!String.valueOf(temp.charAt(i)).equals(" ")){
                        temp1+=String.valueOf(temp.charAt(i));
                    }
                    else break;
                }
            }
            if(messageText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(sendMessageFrame,"No message typed", null, JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(!adminInterface.sendMessage(temp1,messageText.getText())){
                return;
            }
            JOptionPane.showMessageDialog(sendMessageFrame,"Message send");
            sendMessageFrame.dispose();
            updateMessagesView();
            frame.setVisible(true);
        });
        sendMessageFrame.add(sendMessage, g);

        g.gridx = 1;
        JButton cancelSend = new JButton("Cancel");
        cancelSend.addActionListener(e -> {
            sendMessageFrame.dispose();
            frame.setVisible(true);
        });
        sendMessageFrame.add(cancelSend,g);
        sendMessageFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        sendMessageFrame.pack();
        sendMessageFrame.setVisible(true);
    }

    /**
     * A method to get the appropriate JOptionDialog for various uses in the program. The types of the OptionsDialog are:
     * Date (Error Data Invalid), NoPendingUsers (Warning There are no more pending users).
     * @param type the type of OptionsDialog needed
     */
    public void optionsDialog(String type){
        switch (type) {
            case "Date" -> JOptionPane.showMessageDialog(frame, "Date invalid type", "Date Error", JOptionPane.ERROR_MESSAGE);
            case "NoPendingUsers" -> JOptionPane.showMessageDialog(frame, "No pending Users", "Pending Users", JOptionPane.WARNING_MESSAGE);
            case "Symbol" -> JOptionPane.showMessageDialog(frame, "The symbol | is not allowed", "Invalid Symbol", JOptionPane.WARNING_MESSAGE);
            case "User" -> JOptionPane.showMessageDialog(frame, "The user is not found", "Invalid User ID", JOptionPane.WARNING_MESSAGE);
        }
    }
}

