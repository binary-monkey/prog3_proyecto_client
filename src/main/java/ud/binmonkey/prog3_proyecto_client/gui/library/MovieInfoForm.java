package ud.binmonkey.prog3_proyecto_client.gui.library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.imgscalr.Scalr;
import ud.binmonkey.prog3_proyecto_client.gui.MainWindow;
import ud.binmonkey.prog3_proyecto_client.mysql.MySQL;
import ud.binmonkey.prog3_proyecto_client.neo4j.Neo4jUtils;
import ud.binmonkey.prog3_proyecto_client.omdb.MediaType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class MovieInfoForm {
    public JPanel MovieInfoPanel;
    public JPanel posterPanel;
    public JScrollPane infoScrollPane;
    public JPanel infoPanel;
    public JLabel idLabel;
    public JLabel idField;
    public JLabel titleLabel;
    public JLabel titleField;
    public JLabel yearLabel;
    public JLabel yearField;
    public JButton returnButton;
    public JScrollPane plotScrollPane;
    public JLabel plotLabel;
    public JTextArea plotArea;
    public JPanel sidePanel;
    public JPanel generalInfoPanel;
    public JLabel imdbLabel;
    public JLabel rottenLabel;
    public JLabel metascoreLabel;
    public JPanel ratingPanel;
    public JLabel metascoreValueLabel;
    public JLabel rottenValueLabel;
    public JLabel imdbValueLabel;
    public JPanel southPanel;
    public JButton watchButton;
    public JPanel ageLabel;
    public JPanel ageField;
    public JLabel IMDB;

    private Neo4jUtils neo4j;
    private MySQL mySQL;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public MovieInfoForm(String id) {

        neo4j = new Neo4jUtils();
        mySQL = new MySQL();

        /* Loads poster from IMDB */
        try {
            URL url = new URL(neo4j.getAttribute(id, MediaType.MOVIE, "poster"));
            BufferedImage posterImage = ImageIO.read(url);

            ImageIcon image = new ImageIcon(Scalr.resize(posterImage, 400));
            JLabel label = new JLabel("", image, JLabel.CENTER);

            posterPanel.add(label, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        idField.setText(id);
        titleField.setText(neo4j.getAttribute(id, MediaType.MOVIE, "title"));
        yearField.setText(neo4j.getAttribute(id, MediaType.MOVIE, "year"));
        plotArea.setText(neo4j.getAttribute(id, MediaType.MOVIE, "plot"));

        /* Ratings */
        imdbValueLabel.setText(neo4j.getRating(id, MediaType.MOVIE, "Internet Movie Database"));
        rottenValueLabel.setText(neo4j.getRating(id, MediaType.MOVIE, "Rotten Tomatoes"));
        metascoreValueLabel.setText(neo4j.getRating(id, MediaType.MOVIE, "Metacritic"));

        watchButton.addActionListener(actionEvent -> {
            /* TODO Watch */

            /* Log view in MySQL */
            try {
                mySQL.getStatement().executeUpdate("INSERT INTO user_viewing_history " +
                        " VALUES (DEFAULT, '" + MainWindow.INSTANCE.getFrame().getUser() + "'," +
                        " '" + id + "', CURRENT_TIMESTAMP)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        MovieInfoPanel = new JPanel();
        MovieInfoPanel.setLayout(new BorderLayout(0, 0));
        infoScrollPane = new JScrollPane();
        MovieInfoPanel.add(infoScrollPane, BorderLayout.CENTER);
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        infoScrollPane.setViewportView(infoPanel);
        final Spacer spacer1 = new Spacer();
        infoPanel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        plotScrollPane = new JScrollPane();
        plotScrollPane.setEnabled(true);
        infoPanel.add(plotScrollPane, new GridConstraints(3, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        plotArea = new JTextArea();
        plotArea.setEditable(false);
        plotArea.setLineWrap(true);
        plotScrollPane.setViewportView(plotArea);
        plotLabel = new JLabel();
        plotLabel.setText("Plot:");
        infoPanel.add(plotLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        generalInfoPanel = new JPanel();
        generalInfoPanel.setLayout(new GridLayoutManager(3, 6, new Insets(0, 0, 0, 0), -1, -1));
        infoPanel.add(generalInfoPanel, new GridConstraints(0, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        generalInfoPanel.add(spacer2, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        generalInfoPanel.add(spacer3, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        idLabel = new JLabel();
        idLabel.setText("ID:");
        generalInfoPanel.add(idLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idField = new JLabel();
        idField.setText("Label");
        generalInfoPanel.add(idField, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titleLabel = new JLabel();
        titleLabel.setText("Title:");
        generalInfoPanel.add(titleLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleField = new JLabel();
        titleField.setText("Label");
        generalInfoPanel.add(titleField, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        generalInfoPanel.add(spacer4, new GridConstraints(1, 4, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        yearLabel = new JLabel();
        yearLabel.setText("Year:");
        generalInfoPanel.add(yearLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearField = new JLabel();
        yearField.setText("Label");
        generalInfoPanel.add(yearField, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ageLabel = new JPanel();
        ageLabel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        generalInfoPanel.add(ageLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ageField = new JPanel();
        ageField.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        generalInfoPanel.add(ageField, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        ratingPanel = new JPanel();
        ratingPanel.setLayout(new GridLayoutManager(1, 7, new Insets(0, 0, 0, 0), -1, -1));
        infoPanel.add(ratingPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        imdbLabel = new JLabel();
        imdbLabel.setIcon(new ImageIcon(getClass().getResource("/icons/imdb.png")));
        imdbLabel.setText("");
        ratingPanel.add(imdbLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imdbValueLabel = new JLabel();
        imdbValueLabel.setText("Label");
        ratingPanel.add(imdbValueLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rottenLabel = new JLabel();
        rottenLabel.setIcon(new ImageIcon(getClass().getResource("/icons/rottentomatoes.png")));
        rottenLabel.setText("");
        ratingPanel.add(rottenLabel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rottenValueLabel = new JLabel();
        rottenValueLabel.setText("Label");
        ratingPanel.add(rottenValueLabel, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        metascoreLabel = new JLabel();
        metascoreLabel.setIcon(new ImageIcon(getClass().getResource("/icons/metacritic.png")));
        metascoreLabel.setText("");
        ratingPanel.add(metascoreLabel, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        metascoreValueLabel = new JLabel();
        metascoreValueLabel.setText("Label");
        ratingPanel.add(metascoreValueLabel, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        ratingPanel.add(spacer5, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        MovieInfoPanel.add(sidePanel, BorderLayout.WEST);
        posterPanel = new JPanel();
        posterPanel.setLayout(new BorderLayout(0, 0));
        sidePanel.add(posterPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        sidePanel.add(spacer6, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        sidePanel.add(spacer7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        sidePanel.add(spacer8, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        sidePanel.add(spacer9, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        MovieInfoPanel.add(southPanel, BorderLayout.SOUTH);
        watchButton = new JButton();
        watchButton.setText("Watch");
        southPanel.add(watchButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer10 = new Spacer();
        southPanel.add(spacer10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return MovieInfoPanel;
    }
}
