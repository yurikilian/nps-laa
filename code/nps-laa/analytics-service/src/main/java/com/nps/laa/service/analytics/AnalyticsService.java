package com.nps.laa.service.analytics;


import com.mongodb.reactivestreams.client.MongoClient;
import com.nps.laa.service.analytics.domain.model.LessAccessInTheWorldAnalyticsService;
import com.nps.laa.service.analytics.domain.model.TopInTheWorldAnalyticsService;
import com.nps.laa.service.analytics.domain.model.TopInTheWorldByRegionAnalyticsService;
import io.micronaut.http.HttpResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Singleton
public class AnalyticsService {

    @Inject
    private MongoClient mongoClient;

    @Inject
    private TopInTheWorldAnalyticsService topInTheWorldAnalyticsService;

    @Inject
    private TopInTheWorldByRegionAnalyticsService topInTheWorldByRegionAnalyticsService;

    @Inject
    private LessAccessInTheWorldAnalyticsService lessAccessInTheWorldAnalyticsService;


    public Single<HttpResponse<?>> query(@Valid Map<String, String> params) {
        final var database = mongoClient.getDatabase("laa");

        return Flowable.fromPublisher(topInTheWorldAnalyticsService.get(database.getCollection("totalcount"), params))
            .mergeWith(topInTheWorldByRegionAnalyticsService.get(database.getCollection("totalcountregion"), params))
            .mergeWith(lessAccessInTheWorldAnalyticsService.get(database.getCollection("totalcount"), params))
            .toList()
            .map(documents -> HttpResponse.ok(new ArrayList<>(documents)));
    }

}
