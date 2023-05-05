package MyBooking.Graphics.Components;

import javax.swing.*;
import java.awt.*;

/**
 * Η κλαση Tab αναπαριστουν τις καρτελες στο παραθυρο επικοινινια με το χρηστη. Η κλαση tab
 * στην ουσια ειναι ο σκελετος των γραφικων διεπαφων γιατι πανω σε αυτη στιριζονται
 * ολα τα αλλα συστατικα. Η κλαση δεξια φτιαχνει τους Builders δηλαδη τα αποτελεσματα
 * των αναζητησεων που θα φαινονται στο παραθυρο επικονονιας και αριστερα φτιαχνει τα
 * Components οπου στην ουσια ειναι τα συστατικα οπου φτιαχνου τις φορμες αναζυτησεις και
 * βρισκονται στο αριστρερο μερος του παραθυρου επικοινονιας με τον χρηστη. Η κλαση εχει
 * 2 πεδια.
 * @author K.Stafylidis - P.Daratzis.
 * @version 2.0
 */
public class Tab extends JPanel {
    /**
     * optionsPanel εχει τυπο δεδομενων JPanel.
     */
    JPanel optionsPanel;
    /**
     * viewPanel εχει τυπο δεδομενων JScrollPane.
     */
    JScrollPane viewPanel;
    /**
     * Ο κατασκευαστης της κλασης δεν δεχεται ορισματα. Στον κατασκευαστη της κλασης δημιουργηται
     * η γραφικη διεπαφη με τον χρηστη. Στον κατασκευαστη της κλασης διμιουργουνται τα δυο JPanel
     * (optionPanel,viewPanel) οπου ειναι ο σκελετος πανω στα οποια εφαρμοζονται ολα τα αλλα συστατικα
     * των γραφικων.
     */
    public Tab(){
        setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.weightx = 0.5;
        g.weighty = 0.5;
        g.gridy = 0;
        g.gridx = 0;
        g.gridheight = 4;
        g.gridwidth = 3;

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));

        add(optionsPanel, g);

        g.gridx = 4;
        g.gridy = 0;
        g.gridwidth = 6;
        g.gridheight = 5;
        viewPanel = new JScrollPane();
        add(viewPanel,g);
    }
    /**
     *
     * @param panel ειναι ορισμα με τυπο δεδομενων JPanel.
     * Η μεθοδος χρησιμοποιηται για να προσθεσει ενα optionPanel
     * στο παραθηρο επικοινονιας με τον χρηστη
     */
    public void addOptionsPanel(JPanel panel){
        remove(this.optionsPanel);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);

        g.gridy = 0;
        g.gridx = 0;
        g.gridheight = 4;
        g.gridwidth = 3;
        this.optionsPanel = panel;
        add(this.optionsPanel,g);
        this.repaint();
    }
    /**
     *
     * @param viewPanel ειναι μεθοδος με τυπο δεδομενων JScrollPane.
     * Η μεθοδος χρησιμοποιηται για να προσθεσει ενα viewPanel
     * στο παραθηρο επικοινονιας με τον χρηστη
     *
     */
    public void addViewPanel(JScrollPane viewPanel){
        remove(this.viewPanel);

        GridBagConstraints g= new GridBagConstraints();
        g.insets = new Insets(10,10,10,10);
        g.gridx = 4;
        g.gridy = 0;
        g.gridwidth = 6;
        g.gridheight = 5;
        this.viewPanel = viewPanel;
        add(this.viewPanel,g);
        this.repaint();
    }
}