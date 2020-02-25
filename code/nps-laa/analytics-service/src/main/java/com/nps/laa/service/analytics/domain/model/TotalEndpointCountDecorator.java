package com.nps.laa.service.analytics.domain.model;

import com.mongodb.reactivestreams.client.MongoCollection;

import java.util.Map;

public class TotalEndpointCountDecorator extends AnalyticsDecorator {

    @Override
    public Map<String, AnalyticsNode> decorate(MongoCollection<?> collection) {
        var currentMap = super.decorate(collection);

        currentMap.putAll(Map.of());
        return currentMap;
    }
}
