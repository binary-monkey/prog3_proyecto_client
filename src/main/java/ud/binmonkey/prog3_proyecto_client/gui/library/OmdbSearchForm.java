package ud.binmonkey.prog3_proyecto_client.gui.library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.json.JSONObject;
import ud.binmonkey.prog3_proyecto_client.gui.MainWindow;
import ud.binmonkey.prog3_proyecto_client.https.HTTPSClient;
import ud.binmonkey.prog3_proyecto_client.omdb.MediaType;
import ud.binmonkey.prog3_proyecto_client.omdb.OmdbMovie;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;


public class OmdbSearchForm {

    private String selectedFile;
    private String selectedID;
    private JFrame frame;

    private JPanel mainOmdbListPanel;

    private JPanel searchPanel;
    private JTextField searchText;
    private JButton searchButton;
    private JComboBox<MediaType> typeBox;

    private JPanel addPanel;
    private JTextField idText;
    private JButton selectButton;

    private JPanel titleGrid;
    private JTable titleTable;

    private DefaultTableModel titleModel = new DefaultTableModel();

    public OmdbSearchForm() {

        /* JTable Config */
        titleModel.addColumn("ID");
        titleModel.addColumn("Year");
        titleModel.addColumn("Title");
        titleModel.addColumn("Type");

        titleTable.setModel(titleModel);

        /* JTable Column Sizes */
        titleTable.getColumn("ID").setMaxWidth(70);
        titleTable.getColumn("Year").setMaxWidth(70);
        titleTable.getColumn("Type").setMaxWidth(45);

        titleTable.getColumn("ID").setMinWidth(70);
        titleTable.getColumn("Year").setMinWidth(70);
        titleTable.getColumn("Type").setMinWidth(45);

        /* Center Text */
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        titleTable.getColumn("ID").setCellRenderer(centerRenderer);
        titleTable.getColumn("Year").setCellRenderer(centerRenderer);
        titleTable.getColumn("Type").setCellRenderer(centerRenderer);

        /* Sorter Config */
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(titleModel);
        titleTable.setRowSorter(sorter);

        /* Search Button Listener */
        searchButton.addActionListener((ActionEvent e) -> listSearch());

        searchText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    listSearch();
                }
            }
        });

        titleTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = titleTable.getSelectedRow();
                idText.setText((String) titleModel.getValueAt(row, 0));
                selectedID = (String) titleModel.getValueAt(row, 0);
            }
        });

        /*TODO*/
        selectButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                OmdbMovie movie = new OmdbMovie(
                        HTTPSClient.parseJSONResponse((new HTTPSClient()).getMovie(
                                selectedID,
                                MainWindow.INSTANCE.getFrame().getUser(),
                                MainWindow.INSTANCE.getFrame().getToken()
                        ))
                );
                JFrame editionFrame = new JFrame("Edit movie details");
                frame.setTitle("Edit movie details");
                MovieEditForm form = new MovieEditForm(movie);
                form.setFrame(editionFrame);
                form.setSelectedFile(getSelectedFile());
                editionFrame.getContentPane().add(form.editPanel);
                editionFrame.setVisible(true);
                editionFrame.setSize(800, 600);
                if (frame != null) {
                    editionFrame.setLocation(frame.getLocation());
                    frame.dispose();
                }
                editionFrame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Search");
        OmdbSearchForm form = new OmdbSearchForm();
        form.setFrame(frame);
        frame.setContentPane(form.getMainOmdbListPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void listSearch() {

        MediaType type = null;

        /* Clear Table */
        while (titleModel.getRowCount() > 0) {
            titleModel.removeRow(0);
        }

        switch (typeBox.getSelectedItem().toString()) {
            case "All":
                type = MediaType.ALL;
                break;
            case "Movie":
                type = MediaType.MOVIE;
                break;
            case "Series":
                type = MediaType.SERIES;
                break;
            case "Episode":
                type = MediaType.EPISODE;
                break;
        }

        HTTPSClient client = new HTTPSClient();

        JSONObject json = HTTPSClient.parseJSONResponse(
                client.searchMovie(searchText.getText(), type.name(),
                        MainWindow.INSTANCE.getFrame().getUser(),
                        MainWindow.INSTANCE.getFrame().getToken()
                )
        );

        Map search = json.toMap();

        if (search != null) {
            for (Object id : search.keySet()) {

                Map title = (Map) search.get(id);
                Object[] data = {id, title.get("Year"), title.get("Title"), title.get("Type")};
                titleModel.addRow(data);
            }
        }
    }

    public JPanel getMainOmdbListPanel() {
        return mainOmdbListPanel;
    }

    public void setMainOmdbListPanel(JPanel mainOmdbListPanel) {
        this.mainOmdbListPanel = mainOmdbListPanel;
    }

    public JTextField getSearchText() {
        return searchText;
    }

    public void setSearchText(JTextField searchText) {
        this.searchText = searchText;
    }

    public JComboBox<MediaType> getTypeBox() {
        return typeBox;
    }

    public void setTypeBox(JComboBox<MediaType> typeBox) {
        this.typeBox = typeBox;
    }

    public String getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public String getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(String selectedFile) {
        this.selectedFile = selectedFile;
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
        mainOmdbListPanel = new JPanel();
        mainOmdbListPanel.setLayout(new BorderLayout(0, 0));
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        mainOmdbListPanel.add(searchPanel, BorderLayout.NORTH);
        searchButton = new JButton();
        searchButton.setText("Search");
        searchPanel.add(searchButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        typeBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("All");
        defaultComboBoxModel1.addElement("Movie");
        defaultComboBoxModel1.addElement("Series");
        defaultComboBoxModel1.addElement("Episode");
        typeBox.setModel(defaultComboBoxModel1);
        searchPanel.add(typeBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchText = new JTextField();
        searchText.setText("");
        searchText.setToolTipText("");
        searchPanel.add(searchText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addPanel = new JPanel();
        addPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        mainOmdbListPanel.add(addPanel, BorderLayout.SOUTH);
        idText = new JTextField();
        idText.setText("");
        idText.setToolTipText("");
        addPanel.add(idText, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        selectButton = new JButton();
        selectButton.setText("Select");
        addPanel.add(selectButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainOmdbListPanel.add(scrollPane1, BorderLayout.CENTER);
        titleTable = new JTable();
        scrollPane1.setViewportView(titleTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainOmdbListPanel;
    }
}
