package MyBooking.Graphics.Components;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Η κλαση ReservationOptions δημιουργει γραφικη επαφη με το χρηστη. Σκοπος της
 * κλασης ειναι να δημιουργησει μεσω γραφικων διεπαφων τη φορμα για τις κρατισεις.
 * Η κλαση εχει 14 πεδια.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class ReservationOptions extends JPanel {
    /**
     * reservationAccommodationName εχει τυπο δεδομενων JTextField,reservationProviderName εχει τυπο δεδομενων JTextField
     * reservationClientName εχει τυπο δεδομενων JTextField,date1 εχει τυπο δεδομενων JTextField,date2 χει τυπο δεδομενων JTextField.
     */
    JTextField reservationAccommodationName, reservationProviderName, reservationClientName,date1, date2;
    /**
     *  searchReservations εχει τυπο δεδομενων JButton,resetReservations  εχει τυπο δεδομενων JButton,reservationStatistics
     *  εχει τυπο δεδομενων JButton,reservationCancel εχει τυπο δεδομενων JButton,cancellations εχει τυπο δεδομενων JButton,
     *  reservations εχει τυπο δεδομενων JButton,messages εχει τυπο δεδομενων JButton.
     *
     */
    JButton searchReservations,resetReservations, reservationStatistics, reservationCancel, cancellations, reservations, messages;
    /**
     * clientNameLabel εχει τυπο δεδομενων JLabel,providerNameLabel εχει τυπο δεδομενων JLabel.
     */
    JLabel clientNameLabel, providerNameLabel;
    /**
     * Ο κατασκευαστης της κλασης δεν παιρνει ορισματα.Στον κατασκευαστη
     * της κλασης επιτυγχανεται η γραφικη διεπαφη με το χρηστη.Στον κατασκευαστη
     * της κλασης διμιουρηται ενα panel που αυτο ειναι ο σκελετος για το παραθυρο
     * επικοινονονιας με το χρηστη. Επισης διμιουργουνται JLabel,JButton,JTextFields.
     */
    public ReservationOptions (){

        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        add(new JLabel("Search Options"), g);

        g.gridy = 1;
        g.gridx = 0;
        add(new JLabel("Accommodation Name:"), g);

        g.gridx = 1;
        reservationAccommodationName = new JTextField();
        add(reservationAccommodationName, g);

        g.gridy = 2;
        g.gridx = 0;

        providerNameLabel = new JLabel("Provider Name:");
        add(providerNameLabel, g);
        g.gridx = 1;
        reservationProviderName = new JTextField();
        add(reservationProviderName, g);

        g.gridy = 3;
        g.gridx = 0;
        clientNameLabel = new JLabel("Client Name");
        add(clientNameLabel, g);

        g.gridx = 1;
        reservationClientName = new JTextField();
        add(reservationClientName, g);

        g.gridy = 4;
        g.gridx = 0;
        add(new JLabel("Reservations after:"), g);
        g.gridx = 1;
        date1 = new JTextField("yyyy-mm-dd");
        add(date1, g);

        g.gridy = 5;
        g.gridx = 0;
        add(new JLabel("Reservations before:"), g);

        g.gridx = 1;
        date2 = new JTextField("yyyy-mm-dd");
        add(date2, g);

        g.gridy = 6;
        g.gridx = 0;
        searchReservations = new JButton("Search");
        add(searchReservations, g);

        g.gridx = 1;
        resetReservations = new JButton("Reset");
        add(resetReservations, g);

        g.gridwidth = 2;
        g.gridy = 7;
        g.gridx = 0;
        g.insets = new Insets(20, 0,0,0);
        reservations = new JButton("Reservations");
        reservations.setVisible(false);
        add(reservations,g);

        g.gridy = 8;
        cancellations = new JButton("Cancellations");
        cancellations.setVisible(false);
        add(cancellations,g);


        g.gridy = 9;

        reservationStatistics = new JButton("Reservation Statistics");
        reservationStatistics.setVisible(false);
        add(reservationStatistics,g);

        reservationCancel = new JButton("Cancel Resevervation");
        reservationCancel.setVisible(false);
        add(reservationCancel,g);

        g.gridy = 10;
        messages = new JButton("Get Messages");
        messages.setVisible(false);
        add(messages,g);

    }
    /**
     * Η μεθοδος χρησιμοποιηται για να επιστρεψει τα κουμπια που υπαρχουν στη φορμα
     * της κρατησεις σε μια List.
     * @return list που ειναι τυπου δεδομενων JButton.
     */
    public ArrayList<JButton> getButtonsReservationOptions() {
        ArrayList<JButton> list = new ArrayList<>();
        list.add(searchReservations);
        list.add(resetReservations);
        list.add(reservationStatistics);
        list.add(reservationCancel);
        list.add(cancellations);
        list.add(reservations);
        list.add(messages);
        return list;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να εισαγει ολες τις τιμες που πληκτρολογησε
     * ο χρηστης σε μια List.
     * @return list που ειναι τυπου δεδομενων JTextField
     */
    public ArrayList<JTextField> getTextFieldsUserOptions() {
        ArrayList<JTextField> list = new ArrayList<>();
        list.add(reservationAccommodationName);
        list.add(reservationProviderName);
        list.add(reservationClientName);
        list.add(date1);
        list.add(date2);
        return list;
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να διαγραψει ολες τις τιμες
     * που εχει πληκτρολογησει ο χρηστης σε καθε φορμα.
     */
    public void clearJTextFields() {
        reservationAccommodationName.setText(null);
        reservationProviderName.setText(null);
        reservationClientName.setText(null);
        date1.setText("yyyy-mm-dd");
        date2.setText("yyyy-mm-dd");
    }
    /**
     * Η μεθοδος χρησιμοποιηται για να επιλεξει
     * ποια κουμπια θα ειναι ορατα στο τυπο χρηστη
     * πελατη.
     */
    public void setClientReservationOptions(){
        clientNameLabel.setVisible(false);
        providerNameLabel.setVisible(false);
        reservationClientName.setVisible(false);
        reservationProviderName.setVisible(false);
        reservationCancel.setVisible(true);
        messages.setVisible(true);

    }
    /**
     * Η μεθοδος χρησιμοποιηται για να επιλεξει
     * ποια κουμπια θα ειναι ορατα στο τυπο χρηστη
     * πελατη.
     */
    public void setProviderReservationOptions(){
        providerNameLabel.setVisible(false);
        reservationProviderName.setVisible(false);
        reservationStatistics.setVisible(true);
        reservationCancel.setVisible(false);
        cancellations.setVisible(true);
        reservations.setVisible(true);
        messages.setVisible(true);
    }
    /**
     * Η μεθοδος χρησιμοποιηται διοτι θετει τα κουμπια για τον χρηστη admin.
     */
    public void setAdminReservationOptions(){

    }
}