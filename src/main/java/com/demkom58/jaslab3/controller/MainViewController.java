package com.demkom58.jaslab3.controller;

import com.demkom58.jaslab3.model.*;
import com.demkom58.jaslab3.repo.CommonRepository;
import com.demkom58.jaslab3.repo.CrudRepository;
import com.demkom58.jaslab3.view.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Optional;
import java.util.StringJoiner;

public class MainViewController {
    private final JTree tablesTree;
    private final JTextArea infoArea;

    private final JButton removeButton;
    private final JButton createButton;
    private final JButton refreshButton;

    private final SessionFactory sessionFactory;
    private final EntityManager entityManager;

    private final CrudRepository<Integer, Conversation> conversationRepository;
    private final CrudRepository<Integer, ConversationSubscription> conversationSubscriptionRepository;
    private final CrudRepository<Integer, Group> groupRepository;
    private final CrudRepository<Integer, GroupSubscription> groupSubscriptionRepository;
    private final CrudRepository<Integer, Message> messageRepository;
    private final CrudRepository<Integer, Post> postRepository;
    private final CrudRepository<Integer, User> userRepository;

    private DefaultMutableTreeNode root;

    public MainViewController(JTree tablesTree, JTextArea infoArea, JButton removeButton,
                              JButton createButton, JButton refreshButton, Configuration configuration) {
        this.tablesTree = tablesTree;
        this.infoArea = infoArea;
        this.removeButton = removeButton;
        this.createButton = createButton;
        this.refreshButton = refreshButton;

        this.sessionFactory = configuration.buildSessionFactory();
        this.entityManager = sessionFactory.createEntityManager();

        this.conversationRepository = new CommonRepository<>(entityManager, "conversations", "conversation_id");
        this.conversationSubscriptionRepository = new CommonRepository<>(entityManager, "conversation_subscriptions", "subscription_id");
        this.groupRepository = new CommonRepository<>(entityManager, "groups", "group_id");
        this.groupSubscriptionRepository = new CommonRepository<>(entityManager, "group_subscriptions", "subscription_id");
        this.messageRepository = new CommonRepository<>(entityManager, "messages", "message_id");
        this.postRepository = new CommonRepository<>(entityManager, "posts", "post_id");
        this.userRepository = new CommonRepository<>(entityManager, "users", "user_id");
    }

    public void setup() {
        removeButton.addActionListener(this::onRemove);
        createButton.addActionListener(this::onCreate);
        refreshButton.addActionListener(this::onRefresh);

        tablesTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onSelect(e);
            }
        });
        tablesTree.setModel(new DefaultTreeModel(root = new DefaultMutableTreeNode("Root")));

        root.add(new DefaultMutableTreeNode(conversationRepository));
        root.add(new DefaultMutableTreeNode(conversationSubscriptionRepository));
        root.add(new DefaultMutableTreeNode(groupRepository));
        root.add(new DefaultMutableTreeNode(groupSubscriptionRepository));
        root.add(new DefaultMutableTreeNode(messageRepository));
        root.add(new DefaultMutableTreeNode(postRepository));
        root.add(new DefaultMutableTreeNode(userRepository));

        onRefresh(null);
    }

    private void onRemove(ActionEvent e) {
        final TreePath selectionPath = tablesTree.getSelectionPath();
        if (selectionPath == null)
            return;

        final Object lastPathComponent = selectionPath.getLastPathComponent();
        if (!(lastPathComponent instanceof DefaultMutableTreeNode))
            return;

        final DefaultMutableTreeNode entityNode
                = (DefaultMutableTreeNode) lastPathComponent;
        final Object userObject = entityNode.getUserObject();
        if (!(userObject instanceof ObservableEntity))
            return;

        final ObservableEntity entity = (ObservableEntity) userObject;
        getSelectedRepository().ifPresent(repository -> repository.removeById(entity.getId()));
    }

    private void onCreate(ActionEvent e) {
        getSelectedRepository().ifPresent(repo -> {
            try {
                if (repo == conversationRepository)
                    ConversationInput.readFromUser().ifPresent(conversationRepository::save);
                else if (repo == conversationSubscriptionRepository)
                    ConversationSubscriptionInput.readFromUser().ifPresent(conversationSubscriptionRepository::save);
                else if (repo == groupRepository)
                    GroupInput.readFromUser().ifPresent(groupRepository::save);
                else if (repo == groupSubscriptionRepository)
                    GroupSubscriptionInput.readFromUser().ifPresent(groupSubscriptionRepository::save);
                else if (repo == messageRepository)
                    MessageInput.readFromUser().ifPresent(messageRepository::save);
                else if (repo == postRepository)
                    PostInput.readFromUser().ifPresent(postRepository::save);
                else if (repo == userRepository)
                    UserInput.readFromUser().ifPresent(userRepository::save);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });
    }

    private void onRefresh(ActionEvent e) {
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final TreeNode childAt = root.getChildAt(i);
            if (!(childAt instanceof DefaultMutableTreeNode))
                continue;

            final DefaultMutableTreeNode node = (DefaultMutableTreeNode) childAt;
            final Object userObject = node.getUserObject();
            if (!(userObject instanceof CrudRepository))
                continue;

            node.removeAllChildren();
            final CrudRepository<?, ?> repository = (CrudRepository<?, ?>) userObject;
            final Collection<?> all = repository.getAll();
            all.forEach(o -> node.add(new DefaultMutableTreeNode(o)));
        }

        tablesTree.updateUI();
    }

    private void onSelect(MouseEvent e) {
        final TreePath pathForLocation = tablesTree.getPathForLocation(e.getX(), e.getY());
        if (pathForLocation == null)
            return;

        final Object pathComponent = pathForLocation.getLastPathComponent();
        if (!(pathComponent instanceof DefaultMutableTreeNode))
            return;

        final DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) pathComponent;
        final Object userObject = treeNode.getUserObject();
        if (userObject instanceof ObservableEntity) {
            infoArea.setText(userObject.toString());
            return;
        }

        if (userObject instanceof CrudRepository) {
            final StringJoiner joiner = new StringJoiner("\n");
            for (int i = 0; i < treeNode.getChildCount(); i++) {
                final DefaultMutableTreeNode childAt = (DefaultMutableTreeNode) treeNode.getChildAt(i);
                final Object childObject = childAt.getUserObject();
                if (childObject instanceof ObservableEntity)
                    joiner.add(childObject.toString());
            }
            infoArea.setText(joiner.toString());
        }
    }

    private Optional<CrudRepository<Integer, ?>> getSelectedRepository() {
        final Object lastPathComponent = tablesTree.getSelectionPath().getLastPathComponent();
        if (!(lastPathComponent instanceof DefaultMutableTreeNode))
            return Optional.empty();

        DefaultMutableTreeNode entityNode = (DefaultMutableTreeNode) lastPathComponent;
        final Object userObject = entityNode.getUserObject();
        if (userObject instanceof CrudRepository)
            return Optional.of((CrudRepository<Integer, ?>) userObject);

        if (userObject instanceof ObservableEntity) {
            final TreeNode node = entityNode.getParent();
            if (!(node instanceof DefaultMutableTreeNode))
                return Optional.empty();

            final DefaultMutableTreeNode repoNode = (DefaultMutableTreeNode) node;
            final Object repoNodeObject = repoNode.getUserObject();
            if (!(repoNodeObject instanceof CrudRepository))
                return Optional.empty();

            return Optional.of((CrudRepository<Integer, ?>) repoNodeObject);
        }

        return Optional.empty();
    }

}
