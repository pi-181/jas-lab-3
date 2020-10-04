package com.demkom58.jaslab3.view;

import javax.swing.*;
import java.awt.*;

public class MainGui extends JFrame {
    private JPanel rootPanel;

    public MainGui() {
        setContentPane(rootPanel);

        final Dimension minSize = new Dimension(450, 400);
        setMaximumSize(minSize);
        setSize(minSize);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Laboratory Work №3");
    }
}
