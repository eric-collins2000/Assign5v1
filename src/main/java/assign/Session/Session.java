package assign.Session;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Session {
    private String sessionId;
    private String userFullName;

    public Session() {
    }

    public Session(String sessionId) {
        this.sessionId = sessionId;
        this.userFullName = "";
    }

    public Session(String sessionId, String userFullName) {
        this.sessionId = sessionId;
        this.userFullName = userFullName;
    }

    @Override
    public String toString() {
        logger.info(sessionId);
        return "assign4.Session{" +
                "sessionId='" + sessionId + '\'' +
                ", userFullName='" + userFullName + '\'' +
                '}';
    }

    // accessors
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    private static final Logger logger = LogManager.getLogger();
}
