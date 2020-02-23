package com.nps.laa.service.ingest;

import java.time.LocalDateTime;

public class AccessLog  {

    private final String endpoint;
    private final LocalDateTime timestamp;
    private final String userId;
    private final String region;

    private AccessLog(Builder builder) {
        this.endpoint = builder.endpoint;
        this.timestamp = builder.timestamp;
        this.userId = builder.userId;
        this.region = builder.region;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getEndpoint() {
        return endpoint;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    public String getUserId() {
        return userId;
    }

    public String getRegion() {
        return region;
    }


    public static final class Builder {
        private String endpoint;
        private LocalDateTime timestamp;
        private String userId;
        private String region;

        private Builder() {
        }

        public AccessLog build() {
            return new AccessLog(this);
        }

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }
    }
}
