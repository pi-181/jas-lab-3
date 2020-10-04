package com.demkom58.jaslab3.model;

import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String surname;

    private String patronymic;

    @Column(name = "birthday_date")
    private Date birthdayDate;

    @Column(name = "creation_time", nullable = false)
    private Long creationTime;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean verified = false;

    @Column(name = "mail_confirmed", nullable = false)
    private Boolean mailConfirmed = false;

    public User(Integer userId, String username, String surname, String patronymic,
                Date birthdayDate, Long creationTime, String email,
                Boolean verified, Boolean mailConfirmed) {
        this.userId = userId;
        this.username = username;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthdayDate = birthdayDate;
        this.creationTime = creationTime;
        this.email = email;
        this.verified = verified;
        this.mailConfirmed = mailConfirmed;
    }

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Nullable
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(@Nullable String patronymic) {
        this.patronymic = patronymic;
    }

    @Nullable
    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(@Nullable Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean isMailConfirmed() {
        return mailConfirmed;
    }

    public void setMailConfirmed(Boolean mailConfirmed) {
        this.mailConfirmed = mailConfirmed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(patronymic, user.patronymic) &&
                Objects.equals(birthdayDate, user.birthdayDate) &&
                Objects.equals(creationTime, user.creationTime) &&
                Objects.equals(email, user.email) &&
                Objects.equals(verified, user.verified) &&
                Objects.equals(mailConfirmed, user.mailConfirmed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, surname, patronymic, birthdayDate, creationTime, email, verified, mailConfirmed);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", creationTime=" + creationTime +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", mailConfirmed=" + mailConfirmed +
                '}';
    }
}
