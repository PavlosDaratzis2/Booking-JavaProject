package MyBooking.Graphics.Builders;

import MyBooking.Booking.Reservation;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;


/**
 * Η κλαση εχει ενα πεδιο το οποιο ειναι τυπου GridBagConstraints. Η κλαση διμιουργει
 * συνδεση με τον χρηστη μεσω γραφικων διεπαφων. Σκοπος της κλασης ειναι η εμφανιση των
 * καταληματων στα οποια εχει γινει κρατησει στην οθονη του υπολογιστη.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class ReservationsViewBuilder {
    GridBagConstraints g;
    /**
     *
     * @param currentReservations ειναι παραμετρος με τυπο δεδομενον Reservation και ειναι ArrayList.
     * Η μεθοδος εμφανιζει στην οθονη τα καταληματα στα οποια εχει πραγματοποιηθει κρατηση. Η εμφανισει των
     * καταλληματων στην οθονη επιτιγχανεται μεσω γραφικων διεπαφων που δημιουργουνται στη μεθοδο.Η μεθοδος δεχεται
     * ως ορισμα το ArrayList των καταλυματων στα οποια εχει γινει κρατηση και με την χρηση γραφικων διεπαφων και τις
     * συναρτησης getReservationLabel φορτωνει την λιστα των καταλυματων σε γραφικη μορφη στον παραθυρο επικοινινιας του χρηστη.
     * @return scrollPane που ειναι τυπου δεδομενων JScrollPane.
     */
    public JScrollPane getReservationsScrollPane(ArrayList<Reservation> currentReservations) {
        JPanel temp = new JPanel();
        temp.setLayout(new GridBagLayout());
        g = new GridBagConstraints();
        g.insets = new Insets(5,0,5,0);
        g.gridwidth = 10;
        g.gridheight = 5;
        g.gridx = 0;
        g.gridy = 0;
        g.fill = GridBagConstraints.HORIZONTAL;
        currentReservations.forEach(reservation -> temp.add(getReservationLabel(reservation),g));
        temp.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        long count = currentReservations.stream().filter(reservation -> !reservation.getCanceled()).count();

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
     * @param reservation ειναι ορισμαμε τυπο δεδομενων Reservation.
     * Σκοπος της ιδιωτικης μεθοδου getReservationLabel ειναι η προβολη
     * ενος καταλυματος  με την φωτογραφια του αν υπαρχει και τα χαρακτηριστικα
     * του(Name,Check in,Check out,Provider Name, Client Name) στο παραθυρο επικοινονιας
     * με τον χρηστη μεσω γραφικων διεπαφων.Αυτη η μεθοδος ειναι ιδιοτικη διοτι εχει πολυ
     * συγκεκριμενη χρηση (Καλειται απο την getReservationsScrollPane).
     * @return  reservationPanel που ειναι τυπου δεδομενων JPanel.
     */
    private JPanel getReservationLabel(Reservation reservation){
        JPanel reservationPanel = new JPanel();

        reservationPanel.setName(reservation.getId());
        reservationPanel.setLayout(new GridLayout(5,0));

        JLabel accommodationName = new JLabel(reservation.getAccommodationName());
        reservationPanel.add(accommodationName);
        JLabel checkIn = new JLabel("Check In: "+reservation.getDateFromCalendar(reservation.getCheckIn()));
        reservationPanel.add(checkIn);
        JLabel checkOut = new JLabel("Check Out: "+ reservation.getDateFromCalendar(reservation.getCheckOutPrint()));
        reservationPanel.add(checkOut);
        JLabel providerName = new JLabel("Provider: " + reservation.getProviderName());
        reservationPanel.add(providerName);
        JLabel clientNameLabel = new JLabel("Client: " + reservation.getClientName());
        reservationPanel.add(clientNameLabel);
        reservationPanel.setPreferredSize(new Dimension(260,100));
        reservationPanel.setBackground(new Color(200,230,189));
        reservationPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED),"ID: "+reservation.getId()));
        g.gridy += 5;
        return reservationPanel;
    }
    /**
     *
     * @param reservations ειναι τυπου δεδομενων Reservation και ειναι ArrayList.
     * Η μεθοδος χρησιμοποιηται για τη δημιουργια ενος ComboBox.
     * @return επιστρεφει ενα αντικειμενο JComboBox το οποιο θα προστεθει στο
     * παραθυρο επικοινονιας με τον χρηστη.
     */
    public JComboBox getReservationsBox(ArrayList<Reservation> reservations){
        String[] temp1 = new String[reservations.size()];
        int count =0;
        for(Reservation reservation : reservations){
            temp1[count] = reservation.getId()+" "+reservation.getAccommodationName()+ " " + reservation.getDateFromCalendar(reservation.getCheckIn());
            count++;
        }
        return new JComboBox(temp1);
    }



}