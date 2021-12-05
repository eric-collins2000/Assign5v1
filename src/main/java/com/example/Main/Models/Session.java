package com.example.Main.Models;

import com.example.Main.Models.User;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "session", indexes = {
        @Index(name = "USER_ID", columnList = "USER_ID")
})
@Entity
public class Session {
    @Id
    @Column(name = "TOKEN", nullable = false)
    private String token;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Convert(disableConversion = true)
    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
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

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("user_id", user);
        json.put("token", token);
        json.put("timestamp", timestamp);
        System.out.println("Hey I got: " + json.toString());
        return json;
    }
}