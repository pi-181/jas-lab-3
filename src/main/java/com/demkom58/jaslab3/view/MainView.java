package com.demkom58.jaslab3.view;

import com.demkom58.jaslab3.controller.MainViewController;
import com.demkom58.jaslab3.model.*;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class MainView extends JFrame {
    private final Dimension minSize = new Dimension(450, 400);
    private final MainViewController mainViewController;

    private JPanel rootPanel;

    private JTree tablesTree;
    private JTextArea infoArea;

    private JButton removeButton;
    private JButton createButton;
    private JButton refreshButton;

    public MainView() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setContentPane(rootPanel);
        setMaximumSize(minSize);
        setSize(minSize);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Laboratory Work №3");

        this.mainViewController = new MainViewController(tablesTree, infoArea, removeButton,
                createButton, refreshButton, createConfiguration());
        this.mainViewController.setup();
    }

    private static Configuration createConfiguration() {
        final String host = System.getenv("host");
        final String database = System.getenv("database");
        final String port = System.getenv("port");
        final String user = System.getenv("user");
        final String password = System.getenv("password");
        final String schema = System.getenv("schema");

        final Properties properties = new Properties();
        properties.put("hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.url",
                "jdbc:postgresql://" + host + ":" + port + "/" + database);
        properties.put("hibernate.default_schema", schema);
        properties.put("hibernate.connection.username", user);
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");

        final Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Conversation.class);
        configuration.addAnnotatedClass(ConversationSubscription.class);
        configuration.addAnnotatedClass(Group.class);
        configuration.addAnnotatedClass(GroupSubscription.class);
        configuration.addAnnotatedClass(Message.class);
        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(User.class);
        configuration.setProperties(properties);

        return configuration;
    }

}
