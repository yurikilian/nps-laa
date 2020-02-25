package com.nps.laa.service.analytics;


import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.service.analytics.domain.model.Metric;
import io.micronaut.http.HttpResponse;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;

@Singleton
public class AnalyticsService {

    private final MongoClient mongoClient;

    public AnalyticsService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<HttpResponse<List<String>>> query() {
      return Single.just(HttpResponse.ok(Collections.emptyList()));
    }


    private MongoCollection<Metric> getCollection() {
        return
            mongoClient.getDatabase("laa")
                .getCollection("access-log", Metric.class);
    }

}
