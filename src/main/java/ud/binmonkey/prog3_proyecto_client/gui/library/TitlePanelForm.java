package ud.binmonkey.prog3_proyecto_client.gui.library;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class TitlePanelForm {
    public JPanel titlePanel;
    public JLabel yearLabel;
    public JLabel titleLabel;
    public JPanel posterPanel;

    private String id;

    public TitlePanelForm(String id, String poster, String title) {

        this.id = id;

        /* Loads poster from IMDB */
        try {
            URL url = new URL(poster);
            BufferedImage posterImage = ImageIO.read(url);

            ImageIcon image = new ImageIcon(posterImage);
            JLabel label = new JLabel("", image, JLabel.CENTER);

            posterPanel.add(label, BorderLayout.CENTER);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        titleLabel.setText(title);
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
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout(0, 0));
        titlePanel.setBackground(new Color(-8948618));
        titlePanel.setForeground(new Color(-1));
        titlePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, new Color(-4473925)));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        titlePanel.add(panel1, BorderLayout.SOUTH);
        titleLabel = new JLabel();
        titleLabel.setBackground(new Color(-8882056));
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 12));
        titleLabel.setHorizontalAlignment(0);
        titleLabel.setHorizontalTextPosition(0);
        titleLabel.setText("Title");
        panel1.add(titleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        posterPanel = new JPanel();
        posterPanel.setLayout(new BorderLayout(0, 0));
        titlePanel.add(posterPanel, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return titlePanel;
    }
}
