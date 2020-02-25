package com.nps.laa.service.analytics.domain.model;


import com.mongodb.reactivestreams.client.MongoCollection;

import java.util.Map;

public abstract class AnalyticsDecorator implements Analytics {

    private Analytics analytics;

    @Override
    public Map<String, AnalyticsNode> decorate(MongoCollection<?> collection) {
        return analytics.decorate(collection);
    }
}
