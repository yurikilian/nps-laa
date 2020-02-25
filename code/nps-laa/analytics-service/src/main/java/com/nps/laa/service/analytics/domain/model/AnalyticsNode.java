package com.nps.laa.service.analytics.domain.model;

import java.util.Map;

public class AnalyticsNode {
    private final String name;
    private final Map<String, Object> values;

    private AnalyticsNode(String name, Map<String, Object> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getValues() {
        return values;
    }

    public static Builder builder() {
        return new Builder();
    }

    static class Builder {

        private String name;
        private Map<String, Object> values;


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder values(Map<String, Object> values) {
            this.values = values;
            return this;
        }

        public AnalyticsNode build() {
            return new AnalyticsNode(name, values);
        }
    }
}
