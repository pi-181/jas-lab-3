package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.Conversation;
import com.demkom58.jaslab3.model.ConversationSubscription;
import com.demkom58.jaslab3.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class ConversationSubscriptionInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField userField;
    private JTextField conversationField;
    private JTextField timestampField;
    private JCheckBox acceptedCheckBox;

    public ConversationSubscriptionInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);

        timestampField.setText(String.valueOf(System.currentTimeMillis()));

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

    public static Optional<ConversationSubscription> readFromUser() {
        final ConversationSubscriptionInput gui = new ConversationSubscriptionInput();
        gui.setVisible(true);
        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new ConversationSubscription(
                null,
                new User(Integer.parseInt(gui.userField.getText()), null, null, null, null, null, null, null, null),
                new Conversation(Integer.parseInt(gui.conversationField.getText()), null, null),
                Long.parseLong(gui.timestampField.getText()),
                gui.acceptedCheckBox.isSelected()
        ));
    }
}
