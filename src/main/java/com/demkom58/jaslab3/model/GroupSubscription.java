package com.demkom58.jaslab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "group_subscriptions")
public class GroupSubscription implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id", nullable = false)
    private Integer subscriptionId;

    @ManyToOne
    @JoinColumn(name = "subscriber_id", referencedColumnName = "user_id", nullable = false)
    private User subscriber;

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "conversation_id", nullable = false)
    private Conversation conversation;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime;

    @Column(nullable = false)
    private Boolean accepted = false;

    public GroupSubscription(Integer subscriptionId, User subscriber, Conversation conversation,
                             Long creationTime, Boolean accepted) {
        this.subscriptionId = subscriptionId;
        this.subscriber = subscriber;
        this.conversation = conversation;
        this.creationTime = creationTime;
        this.accepted = accepted;
    }

    public GroupSubscription() {
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
        GroupSubscription that = (GroupSubscription) o;
        return Objects.equals(subscriptionId, that.subscriptionId) &&
                Objects.equals(subscriber, that.subscriber) &&
                Objects.equals(conversation, that.conversation) &&
                Objects.equals(creationTime, that.creationTime) &&
                Objects.equals(accepted, that.accepted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriptionId, subscriber, conversation, creationTime, accepted);
    }

    @Override
    public String toString() {
        return "GroupSubscription{" +
                "subscriptionId=" + subscriptionId +
                ", subscriber=" + subscriber +
                ", conversation=" + conversation +
                ", creationTime=" + creationTime +
                ", accepted=" + accepted +
                '}';
    }

}
