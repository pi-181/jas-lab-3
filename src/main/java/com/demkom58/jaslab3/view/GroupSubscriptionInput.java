package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class GroupSubscriptionInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField subscriberField;
    private JTextField groupField;
    private JTextField creationTimeField;
    private JCheckBox acceptedCheckBox;

    public GroupSubscriptionInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 200));
        setLocationRelativeTo(null);

        creationTimeField.setText(String.valueOf(System.currentTimeMillis()));

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

    public static Optional<GroupSubscription> readFromUser() {
        final GroupSubscriptionInput gui = new GroupSubscriptionInput();
        gui.setVisible(true);

        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new GroupSubscription(
                null,
                new User(Integer.parseInt(gui.subscriberField.getText()), null, null, null, null, null, null, null, null),
                new Group(Integer.parseInt(gui.groupField.getText()), null, null, null),
                Long.parseLong(gui.creationTimeField.getText()),
                gui.acceptedCheckBox.isSelected()
        ));
    }
}
