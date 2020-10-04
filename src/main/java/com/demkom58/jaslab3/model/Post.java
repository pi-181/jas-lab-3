package com.demkom58.jaslab3.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "posts")
public class Post implements ObservableEntity, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer postId;

    @Column(name = "post_content", nullable = false)
    private String postContent;

    @Column(nullable = false)
    private Integer views;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @Column(name = "postDate", nullable = false)
    private Long postDate;

    public Post(Integer postId, String postContent, Integer views,
                Group group, User author, Long postDate) {
        this.postId = postId;
        this.postContent = postContent;
        this.views = views;
        this.group = group;
        this.author = author;
        this.postDate = postDate;
    }

    public Post() {
    }

    @Override
    public int getId() {
        return postId;
    }

    @Override
    public String getDisplayName() {
        return postId + " (" + postContent + ")";
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroupId(Group group) {
        this.group = group;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getPostDate() {
        return postDate;
    }

    public void setPostDate(Long postDate) {
        this.postDate = postDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) &&
                Objects.equals(postContent, post.postContent) &&
                Objects.equals(views, post.views) &&
                Objects.equals(group.getGroupId(), post.group.getGroupId()) &&
                Objects.equals(author, post.author) &&
                Objects.equals(postDate, post.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postContent, views, group, author, postDate);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", views=" + views +
                ", group=" + group.getGroupId() +
                ", author=" + author.getUserId() +
                ", postDate=" + postDate +
                '}';
    }
}
