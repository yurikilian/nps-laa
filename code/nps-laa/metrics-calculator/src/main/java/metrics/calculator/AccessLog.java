package metrics.calculator;

import java.time.LocalDateTime;

public class AccessLog {

    private String endpoint;
    private LocalDateTime timestamp;
    private String userId;
    private String region;

    public AccessLog() {
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    @Override
    public String toString() {
        return "AccessLog{" +
            "endpoint='" + endpoint + '\'' +
            ", timestamp=" + timestamp +
            ", userId='" + userId + '\'' +
            ", region='" + region + '\'' +
            '}';
    }
}
