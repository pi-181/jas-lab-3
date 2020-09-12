package com.demkom58.jaslab3;

import com.demkom58.jaslab3.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.sql.Date;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
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

        final SessionFactory sessionFactory = configuration.buildSessionFactory();
        final EntityManager entityManager = sessionFactory.createEntityManager();

        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        final User saveUser = new User(
                null, "username", "sur", "pat",
                new Date(System.currentTimeMillis()), System.currentTimeMillis(),
                "mail@gmail.com", true, true
        );

        entityManager.persist(saveUser);
        transaction.commit();

    }
}
