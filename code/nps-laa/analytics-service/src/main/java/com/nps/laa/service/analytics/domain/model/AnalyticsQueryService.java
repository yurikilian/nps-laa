package com.nps.laa.service.analytics.domain.model;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;

import java.util.Map;

public interface AnalyticsQueryService {

    Flowable<Map<String, Object>> get(MongoCollection<Document> collection, Map<String,String> params);

}
