package com.demkom58.jaslab3;

import com.demkom58.jaslab3.view.MainGui;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> new MainGui().setVisible(true));
    }
}
