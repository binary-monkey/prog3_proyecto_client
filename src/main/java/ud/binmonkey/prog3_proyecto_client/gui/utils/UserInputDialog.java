package ud.binmonkey.prog3_proyecto_client.gui.utils;

import javax.swing.*;
import java.awt.*;

/**
 * TODO: dialog does not close the first time "OK" is pressed
 * Simple dialog with a textfield and an "OK" button to get basic user input
 */
@SuppressWarnings({"WeakerAccess", "CodeBlock2Expr"})
public class UserInputDialog {

    private JDialog dialog;
    private JTextField inputTextField;

    private UserInputDialog(String title, JFrame frame) {
        dialog = new JDialog(frame, title, true);
        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        dialog.setMinimumSize(new Dimension(300, 120));
        init(frame);
        dialog.setVisible(true);
    }

    public void init(JFrame frame) {
        inputTextField = new JTextField();
        inputTextField.setColumns(12);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(actionEvent -> {
            dialog.dispose();
        });
        dialog.setLocation(frame.getLocation());
        dialog.setLayout(new GridLayout(2, 1, 5, 5));
        dialog.add(inputTextField);
        dialog.add(okButton);
        dialog.pack();
    }

    @SuppressWarnings("SameParameterValue")
    public void setVisible(boolean visible){
        dialog.setVisible(visible);
    }

    public static String getInput(String title, JFrame frame){
        UserInputDialog input = new UserInputDialog(title, frame);
        input.setVisible(true);
        return input.inputTextField.getText();
    }
}
