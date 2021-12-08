package assign.Models;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
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

    public int getUser() {
        return user.getId();
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
        return json;
    }
}