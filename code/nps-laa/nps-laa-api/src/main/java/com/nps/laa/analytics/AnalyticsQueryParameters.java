package com.nps.laa.analytics;

import java.util.HashMap;

public class AnalyticsQueryParameters {
    private String name;
    private HashMap<String, Object> additionalParameters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }

    public void setAdditionalParameters(HashMap<String, Object> additionalParameters) {
        this.additionalParameters = additionalParameters;
    }
}
