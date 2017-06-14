package ud.binmonkey.prog3_proyecto_client.gui.library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import ud.binmonkey.prog3_proyecto_client.neo4j.Neo4jUtils;
import ud.binmonkey.prog3_proyecto_client.omdb.MediaType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class LibraryForm {
    public JPanel libraryPanel;
    public JTextField searchField;
    public JComboBox searchbyBox;
    public JButton searchButton;
    public JPanel searchPanel;
    public JPanel titlesScrollPanel;
    public JButton resetButton;
    public JComboBox sortComboBox;
    public JLabel sortLabel;
    public JLabel searchLabel;
    public JPanel sortPanel;
    public JLabel typeLabel;
    public JComboBox typeBox;
    public JPanel titlesPanel;
    public JButton Search;

    private Neo4jUtils neo4j;
    private JPanel backupPanel;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    public LibraryForm() {

        titlesPanel.setLayout(new GridLayout(0, 5));
        neo4j = new Neo4jUtils();

        showTitles(MediaType.ALL, "title", ".* *.", "title"); /* Shows All */ /* TODO move wildcard to method */

        /* Search Button */
        searchButton.addActionListener(actionEvent -> {

            MediaType type = MediaType.ALL;

            switch (typeBox.getSelectedItem().toString()) {
                case "Movies":
                    type = MediaType.MOVIE;
                    break;
                case "Series":
                    type = MediaType.SERIES;
                    break;
            }

            titlesPanel.removeAll();

            showTitles(type, searchbyBox.getSelectedItem().toString(), searchField.getText(),
                    sortComboBox.getSelectedItem().toString());

            titlesPanel.revalidate();
            titlesPanel.repaint();
        });

        /* Reset Button */
        resetButton.addActionListener(actionEvent -> {

            typeBox.setSelectedIndex(0);
            sortComboBox.setSelectedIndex(0);
            searchbyBox.setSelectedIndex(0);

            searchField.setText("");

            titlesPanel.removeAll();

            showTitles(MediaType.ALL, "title", ".* *.", "title"); /* TODO move wildcard to method */

            titlesPanel.revalidate();
            titlesPanel.repaint();
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        LibraryForm libraryForm = new LibraryForm();
        JPanel libraryPanel = libraryForm.libraryPanel;

        frame.setContentPane(libraryPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    public void addTitle(String id, String title, String poster) {

        JPanel titlePanel = new TitlePanelForm(id, poster, title).titlePanel;
        titlePanel.setMaximumSize(new Dimension(400, 400));

        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2) {

                    MovieInfoForm movieInfoForm = new MovieInfoForm(id);
                    JPanel moviePanel = movieInfoForm.MovieInfoPanel;

                    backupPanel = libraryPanel;

                    libraryPanel.removeAll();
                    libraryPanel.add(moviePanel);
                    libraryPanel.revalidate();
                    libraryPanel.repaint();

                }
            }
        });

        titlesPanel.add(titlePanel);
    }

    private void showTitles(MediaType type, String condition, String value, String orderBy) {

        for (ArrayList<String> title : neo4j.getTitles(type, condition, value, orderBy)) {
            addTitle(title.get(0), title.get(1), title.get(2));
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        libraryPanel = new JPanel();
        libraryPanel.setLayout(new BorderLayout(0, 0));
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        libraryPanel.add(searchPanel, BorderLayout.NORTH);
        searchButton = new JButton();
        searchButton.setText("Search");
        searchPanel.add(searchButton, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchLabel = new JLabel();
        searchLabel.setText("Search by");
        searchPanel.add(searchLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchField = new JTextField();
        searchPanel.add(searchField, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resetButton = new JButton();
        resetButton.setText("Reset");
        searchPanel.add(resetButton, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sortPanel = new JPanel();
        sortPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        searchPanel.add(sortPanel, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        sortLabel = new JLabel();
        sortLabel.setText("Sort by");
        sortPanel.add(sortLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sortComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Title");
        defaultComboBoxModel1.addElement("Year");
        defaultComboBoxModel1.addElement("Rating");
        sortComboBox.setModel(defaultComboBoxModel1);
        sortPanel.add(sortComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("All");
        defaultComboBoxModel2.addElement("Movies");
        defaultComboBoxModel2.addElement("Series");
        typeBox.setModel(defaultComboBoxModel2);
        sortPanel.add(typeBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeLabel = new JLabel();
        typeLabel.setText("Type");
        sortPanel.add(typeLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchbyBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Title");
        defaultComboBoxModel3.addElement("Year");
        defaultComboBoxModel3.addElement("Genre");
        defaultComboBoxModel3.addElement("Person");
        searchbyBox.setModel(defaultComboBoxModel3);
        searchPanel.add(searchbyBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        libraryPanel.add(scrollPane1, BorderLayout.CENTER);
        titlesPanel = new JPanel();
        titlesPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(titlesPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return libraryPanel;
    }
}
