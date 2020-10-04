package com.demkom58.jaslab3.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "groups")
public class Group implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @ManyToOne
    @JoinColumn(name = "owner_id",
            referencedColumnName = "user_id", nullable = false)
    private User owner;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    private String description;

    public Group(Integer groupId, User owner, String groupName, String description) {
        this.groupId = groupId;
        this.owner = owner;
        this.groupName = groupName;
        this.description = description;
    }

    public Group() {
    }

    @Override
    public int getId() {
        return groupId;
    }

    @Override
    public String getDisplayName() {
        return groupId + " (" + groupName + ")";
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(groupId, group.groupId) &&
                Objects.equals(owner.getUserId(), group.owner.getUserId()) &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(description, group.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, owner.getUserId(), groupName, description);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", owner=" + owner.getUserId() +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
