package com.example.Main.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "session", indexes = {
        @Index(name = "USER_ID", columnList = "USER_ID")
})
@Entity
public class Session {
    @Id
    @Column(name = "id", nullable = false)
    private Long id; // Need to verify this

    @Lob
    @Column(name = "TOKEN", nullable = false)
    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}