package com.demkom58.jaslab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "conversations")
public class Conversation implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", nullable = false)
    private Integer conversationId;

    @Column(name = "conversation_name", nullable = false)
    private String conversationName;

    @Column(nullable = false)
    private Boolean removed = false;

    public Conversation(Integer conversationId, String conversationName, Boolean removed) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.removed = removed;
    }

    public Conversation() {
    }

    @Override
    public int getId() {
        return conversationId;
    }

    @Override
    public String getDisplayName() {
        return conversationId + " (" + getConversationName() + ")";
    }

    public Integer getConversationId() {
        return conversationId;
    }

    public void setConversationId(Integer conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public Boolean isRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(conversationId, that.conversationId) &&
                Objects.equals(conversationName, that.conversationName) &&
                Objects.equals(removed, that.removed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, conversationName, removed);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId=" + conversationId +
                ", conversationName='" + conversationName + '\'' +
                ", removed=" + removed +
                '}';
    }
}
