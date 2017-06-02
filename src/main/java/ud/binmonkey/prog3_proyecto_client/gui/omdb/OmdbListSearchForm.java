package ud.binmonkey.prog3_proyecto_client.gui.omdb;

import ud.binmonkey.prog3_proyecto_client.omdb.MediaType;
import ud.binmonkey.prog3_proyecto_client.omdb.Omdb;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class OmdbListSearchForm {
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

    public OmdbListSearchForm() {

        titleModel.addColumn("ID");
        titleModel.addColumn("Year");
        titleModel.addColumn("Title");
        titleModel.addColumn("Type");

        titleTable.setModel(titleModel);

        titleTable.getColumn("ID").setMaxWidth(65);
        titleTable.getColumn("Year").setMaxWidth(70);
        titleTable.getColumn("Type").setMaxWidth(45);

        titleTable.getColumn("ID").setMinWidth(65);
        titleTable.getColumn("Year").setMinWidth(70);
        titleTable.getColumn("Type").setMinWidth(45);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        titleTable.getColumn("ID").setCellRenderer(centerRenderer);
        titleTable.getColumn("Year").setCellRenderer(centerRenderer);
        titleTable.getColumn("Type").setCellRenderer(centerRenderer);

        /* Search Button listener */
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
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Search");
        frame.setContentPane(new OmdbListSearchForm().mainOmdbListPanel);
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

        HashMap search = Omdb.search(searchText.getText(), type);

        if (search != null) {
            for (Object id : search.keySet()) {

                Map title = (Map) search.get(id);
                Object[] data = {id, title.get("Year"), title.get("Title"), title.get("Type")};
                titleModel.addRow(data);
            }
        }
    }
}
