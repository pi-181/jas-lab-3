package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class MessageInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField senderField;
    private JTextField messageField;
    private JCheckBox removedCheckBox;
    private JTextField conversationField;

    public MessageInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 200));
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
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    public static Optional<Message> readFromUser() {
        final MessageInput gui = new MessageInput();
        gui.setVisible(true);

        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new Message(
                null,
                new User(Integer.parseInt(gui.senderField.getText()), null, null, null, null, null, null, null, null),
                gui.messageField.getText(),
                gui.removedCheckBox.isSelected(),
                new Conversation(Integer.parseInt(gui.conversationField.getText()), null, null)
        ));
    }
}
