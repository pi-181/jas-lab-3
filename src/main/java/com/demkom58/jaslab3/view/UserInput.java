package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.Group;
import com.demkom58.jaslab3.model.Post;
import com.demkom58.jaslab3.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Optional;

public class UserInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField usernameField;
    private JTextField surnameField;
    private JTextField patronymicField;
    private JTextField birthdayDateField;
    private JTextField creationTimeField;
    private JTextField emailField;
    private JCheckBox verifiedCheckBox;
    private JCheckBox mailConfirmedCheckBox;

    public UserInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 350));
        setLocationRelativeTo(null);

        final String time = String.valueOf(System.currentTimeMillis());
        birthdayDateField.setText(time);
        creationTimeField.setText(time);

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

    public static Optional<User> readFromUser() {
        final UserInput gui = new UserInput();
        gui.setVisible(true);

        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new User(
                null,
                gui.usernameField.getText(),
                gui.surnameField.getText(),
                gui.patronymicField.getText(),
                new Date(Long.parseLong(gui.birthdayDateField.getText())),
                Long.parseLong(gui.creationTimeField.getText()),
                gui.emailField.getText(),
                gui.verifiedCheckBox.isSelected(),
                gui.mailConfirmedCheckBox.isSelected()
        ));
    }
}
