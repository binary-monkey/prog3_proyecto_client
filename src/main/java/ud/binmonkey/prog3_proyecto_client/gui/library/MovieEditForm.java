package ud.binmonkey.prog3_proyecto_client.gui.library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.json.JSONObject;
import ud.binmonkey.prog3_proyecto_client.gui.MainWindow;
import ud.binmonkey.prog3_proyecto_client.gui.utils.Notifier;
import ud.binmonkey.prog3_proyecto_client.https.HTTPSClient;
import ud.binmonkey.prog3_proyecto_client.omdb.OmdbMovie;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class MovieEditForm {

    public JPanel editPanel;
    public JTextField titleText;
    public JLabel titleLabel;
    public JLabel idLabel;
    public JTextField idText;
    public JLabel yearLabel;
    public JTextField yearText;
    public JLabel Plot;
    public JPanel infoPanel;
    public JTextArea plotTextArea;
    public JButton confirmButton;
    public JPanel posterPanel;
    public JPanel optionsPanel;
    public JButton editButton;
    public JButton saveChangesButton;
    public JButton discardButton;
    public JLabel infoLabel;
    public JLabel ratingLaabel;
    public JPanel ratingPanel;
    public JLabel metascoreLabel;
    public JLabel metascoreValueLabel;
    public JLabel imdbRating;
    public JLabel imdbRatingValueLabel;
    public JLabel imdbRatingShowLabel;
    public JLabel runtimeLabel;
    public JLabel runtimeValueLabel;
    public JLabel Metascore;
    private JFrame frame;
    private String selectedFile;

    @SuppressWarnings("RedundantCast") /* more readable */
    public MovieEditForm(OmdbMovie movie) {

        movie.setLanguage("EN");
        idText.setText(movie.getImdbID());
        titleText.setText(movie.getTitle());
        yearText.setText(movie.getYear());
        plotTextArea.setText(movie.getPlot());
        plotTextArea.setLineWrap(true);
        plotTextArea.setWrapStyleWord(true);
        metascoreValueLabel.setText(new Integer(movie.getMetascore()).toString());
        imdbRatingValueLabel.setText(new Integer(movie.getImdbRating()).toString());

        int hours = (int) (movie.getRuntime() / 60);
        int minutes = movie.getRuntime() - hours * 60;

        runtimeValueLabel.setText(Integer.toString(hours) + "h " + Integer.toString(minutes) + "m");

        /* disable elements */
        titleText.setEditable(false);
        yearText.setEditable(false);
        plotTextArea.setEditable(false);
        saveChangesButton.setEnabled(false);
        discardButton.setEnabled(false);

        /* Loads poster from IMDB */
        try {
            URL url = new URL(movie.getPoster());
            BufferedImage poster = ImageIO.read(url);

            ImageIcon image = new ImageIcon(poster);
            JLabel label = new JLabel("", image, JLabel.CENTER);
            posterPanel.add(label, BorderLayout.CENTER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* EDIT */
        editButton.addActionListener(actionEvent -> {
            saveChangesButton.setEnabled(true);
            discardButton.setEnabled(true);
            plotTextArea.setEditable(true);
            yearText.setEditable(true);
            titleText.setEditable(true);
        });

        /* SAVE */
        saveChangesButton.addActionListener(actionEvent -> {
            try {
                /* if empty */
                if (titleText.getText().replaceAll(" ", "").equals("")
                        || yearText.getText().replaceAll(" ", "").equals("")
                        || plotTextArea.getText().replaceAll(" ", "").equals("")) {
                    infoLabel.setText("Fields cannot be empty");
                    return;
                }
                movie.setTitle(titleText.getText());
                movie.setYear(yearText.getText());
                movie.setPlot(plotTextArea.getText());
                infoPanel.repaint();
                infoLabel.setText("Changed saved!");
            } catch (Exception e) {
                infoLabel.setText("Error saving changes.");
            }
        });

        /* DISCARD */
        discardButton.addActionListener(actionEvent -> {
            titleText.setText(movie.getTitle());
            yearText.setText(movie.getYear());
            plotTextArea.setText(movie.getPlot());
        });

        /* CONFIRM */
        confirmButton.addActionListener(actionEvent -> {
            HTTPSClient client = new HTTPSClient();
            try {

                String[] exts = getSelectedFile().split(".");
                String ext = "";
                if (exts.length > 1) {
                    ext += exts[exts.length - 1];
                } else {
                    ext += ".mp4"; /*? default */
                }
                movie.setFilename(movie.getTitle() + "(" + movie.getYear() + ")" +
                        /* extension */
                        ext);
                client.publishMovie(movie.toJSON(), getSelectedFile(),
                        MainWindow.INSTANCE.getFrame().getUser(),
                        MainWindow.INSTANCE.getFrame().getToken());

                getFrame().dispose();
                Notifier.showMessage(movie.getTitle() + " published correctly!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        HTTPSClient client = new HTTPSClient();
        JSONObject response = HTTPSClient.parseJSONResponse(client.getTitle(
                "tt0117951",
                MainWindow.INSTANCE.getFrame().getUser(),
                MainWindow.INSTANCE.getFrame().getToken()));
        MovieEditForm editForm = new MovieEditForm(new OmdbMovie(response));
        JPanel libraryPanel = editForm.editPanel;
        frame.setContentPane(libraryPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        editPanel = new JPanel();
        editPanel.setLayout(new BorderLayout(0, 0));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        editPanel.add(panel1, BorderLayout.SOUTH);
        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        panel1.add(confirmButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        infoLabel = new JLabel();
        infoLabel.setText("");
        panel1.add(infoLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        editPanel.add(infoPanel, BorderLayout.CENTER);
        idLabel = new JLabel();
        idLabel.setText("IMDB ID");
        infoPanel.add(idLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idText = new JTextField();
        idText.setEditable(false);
        idText.setText("id");
        infoPanel.add(idText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titleLabel = new JLabel();
        titleLabel.setText("Title");
        infoPanel.add(titleLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        titleText = new JTextField();
        infoPanel.add(titleText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        yearLabel = new JLabel();
        yearLabel.setText("Year");
        infoPanel.add(yearLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yearText = new JTextField();
        infoPanel.add(yearText, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        Plot = new JLabel();
        Plot.setText("Plot");
        infoPanel.add(Plot, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        infoPanel.add(optionsPanel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        editButton = new JButton();
        editButton.setText("Edit");
        optionsPanel.add(editButton);
        saveChangesButton = new JButton();
        saveChangesButton.setText("Save");
        optionsPanel.add(saveChangesButton);
        discardButton = new JButton();
        discardButton.setText("Discard");
        optionsPanel.add(discardButton);
        final JScrollPane scrollPane1 = new JScrollPane();
        infoPanel.add(scrollPane1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        plotTextArea = new JTextArea();
        plotTextArea.setRows(5);
        plotTextArea.setWrapStyleWord(true);
        scrollPane1.setViewportView(plotTextArea);
        ratingLaabel = new JLabel();
        ratingLaabel.setText("Rating");
        infoPanel.add(ratingLaabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ratingPanel = new JPanel();
        ratingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        infoPanel.add(ratingPanel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        metascoreLabel = new JLabel();
        metascoreLabel.setText("Metascore: ");
        ratingPanel.add(metascoreLabel);
        final Spacer spacer3 = new Spacer();
        ratingPanel.add(spacer3);
        metascoreValueLabel = new JLabel();
        metascoreValueLabel.setText("");
        ratingPanel.add(metascoreValueLabel);
        final Spacer spacer4 = new Spacer();
        ratingPanel.add(spacer4);
        final JSeparator separator1 = new JSeparator();
        ratingPanel.add(separator1);
        imdbRatingShowLabel = new JLabel();
        imdbRatingShowLabel.setText("IMDB: ");
        ratingPanel.add(imdbRatingShowLabel);
        imdbRatingValueLabel = new JLabel();
        imdbRatingValueLabel.setText("");
        ratingPanel.add(imdbRatingValueLabel);
        runtimeLabel = new JLabel();
        runtimeLabel.setText("Runtime");
        infoPanel.add(runtimeLabel, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        runtimeValueLabel = new JLabel();
        runtimeValueLabel.setText("");
        infoPanel.add(runtimeValueLabel, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        posterPanel = new JPanel();
        posterPanel.setLayout(new BorderLayout(0, 0));
        editPanel.add(posterPanel, BorderLayout.WEST);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return editPanel;
    }
}
