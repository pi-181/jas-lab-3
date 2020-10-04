package com.demkom58.jaslab3;

import com.demkom58.jaslab3.view.MainView;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new MainView().setVisible(true));
    }
}
