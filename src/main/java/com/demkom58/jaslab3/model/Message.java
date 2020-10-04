package com.demkom58.jaslab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;

    @Column(name = "text_message", nullable = false)
    private String textMessage;

    @Column(nullable = false)
    private Boolean removed;

    @ManyToOne
    @JoinColumn(name = "conversation_id",
            referencedColumnName = "conversation_id", nullable = false)
    private Conversation conversation;

    public Message(Integer messageId, User sender, String textMessage,
                   Boolean removed, Conversation conversation) {
        this.messageId = messageId;
        this.sender = sender;
        this.textMessage = textMessage;
        this.removed = removed;
        this.conversation = conversation;
    }

    public Message() {
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(textMessage, message.textMessage) &&
                Objects.equals(removed, message.removed) &&
                Objects.equals(conversation, message.conversation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, sender, textMessage, removed, conversation);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", sender=" + sender +
                ", textMessage='" + textMessage + '\'' +
                ", removed=" + removed +
                ", conversationId=" + conversation +
                '}';
    }
}
