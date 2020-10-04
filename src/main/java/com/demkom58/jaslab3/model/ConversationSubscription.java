package com.demkom58.jaslab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "conversation_subscriptions")
public class ConversationSubscription implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id",
            referencedColumnName = "user_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "conversation_id",
            referencedColumnName = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime = System.currentTimeMillis();

    @Column(nullable = false)
    private Boolean accepted = false;

    public ConversationSubscription(Integer subscriptionId, User subscriber, Conversation conversation,
                                    Long creationTime, Boolean accepted) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.conversation = conversation;
        this.creationTime = creationTime;
        this.accepted = accepted;
    }

    public ConversationSubscription() {
    }

    @Override
    public int getId() {
        return subscriptionId;
    }

    @Override
    public String getDisplayName() {
        return subscriptionId + " (" + conversation.getConversationName() + ")";
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConversationSubscription that = (ConversationSubscription) o;
        return Objects.equals(subscriptionId, that.subscriptionId) &&
                Objects.equals(subscriber.getUserId(), that.subscriber.getUserId()) &&
                Objects.equals(conversation.getConversationId(), that.conversation.getConversationId()) &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, subscriber.getUserId(), conversation.getConversationId(), creationTime, accepted);
    }

    @Override
    public String toString() {
        return "ConversationSubscription{" +
                "subscriptionId=" + subscriptionId +
                ", subscriber=" + subscriber.getUserId() +
                ", conversation=" + conversation.getConversationId() +
                ", creationTime=" + creationTime +
                ", accepted=" + accepted +
                '}';
    }
}
