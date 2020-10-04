package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.Conversation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class ConversationInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField nameField;
    private JCheckBox removedCheckBox;

    public ConversationInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 150));
        setLocationRelativeTo(null);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
        );
    }

    private void onOK() {
        ok = true;
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static Optional<Conversation> readFromUser() {
        final ConversationInput gui = new ConversationInput();
        gui.setVisible(true);

        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new Conversation(null, gui.nameField.getText(), gui.removedCheckBox.isSelected()));
    }

}
