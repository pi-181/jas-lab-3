package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.util.Optional;

public class PostInput extends JDialog {
    private boolean ok = false;

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField contentField;
    private JTextField viewsField;
    private JTextField groupField;
    private JTextField authorField;
    private JTextField postDateField;

    public PostInput() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(new Dimension(300, 250));
        setLocationRelativeTo(null);

        postDateField.setText(String.valueOf(System.currentTimeMillis()));

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

    public static Optional<Post> readFromUser() {
        final PostInput gui = new PostInput();
        gui.setVisible(true);

        if (!gui.ok)
            return Optional.empty();

        return Optional.of(new Post(
                null,
                gui.contentField.getText(),
                Integer.parseInt(gui.viewsField.getText()),
                new Group(Integer.parseInt(gui.groupField.getText()), null, null, null),
                new User(Integer.parseInt(gui.authorField.getText()), null, null, null, null, null, null, null, null),
                Long.parseLong(gui.postDateField.getText())
        ));
    }
}
