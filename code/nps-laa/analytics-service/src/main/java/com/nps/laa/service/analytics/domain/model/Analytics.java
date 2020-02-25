package com.nps.laa.service.analytics.domain.model;

import com.mongodb.reactivestreams.client.MongoCollection;

import java.util.Map;

public interface Analytics {
    Map<String, AnalyticsNode> decorate(MongoCollection<?> collection);
}
