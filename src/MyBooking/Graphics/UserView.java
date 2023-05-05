package MyBooking.Graphics;


import javax.swing.*;
import java.awt.*;

/**
 * An abstract class of the concrete subclasses AdminView, ClientView and ProviderView. It is responsible
 * for creating the beginning of the GUI for all subclasses.
 */
public abstract class UserView {
    protected JFrame frame;
    protected Container contentPane;
    protected JTabbedPane tabbedPane;
    protected JLabel userDetails;
    protected JButton logOut;

    /**
     * The constructor of the UserView, common for all subclasses as it begins the GUI. Common code needed for all subclasses
     * is documented within. The frame that contains all GUI is created.
     */
    public UserView() {
        frame = new JFrame();
        frame.setSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        contentPane = frame.getContentPane();

        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        tabbedPane = new JTabbedPane();
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 10;
        contentPane.add(tabbedPane, g);
        g.gridy = 10;
        g.gridwidth = 1;

        JPanel userBar = new JPanel();
        userBar.setLayout(new FlowLayout());
        userDetails = new JLabel();
        userBar.add(userDetails);
        logOut = new JButton("Logout");
        userBar.add(logOut);
        contentPane.add(userBar, g);
    }
}
