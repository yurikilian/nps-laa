package com.nps.laa.service.analytics;


import com.mongodb.reactivestreams.client.MongoClient;
import com.nps.laa.service.analytics.aggregations.*;
import io.micronaut.http.HttpResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

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

    @Inject
    private TheMinuteWithMoreAccess theMinuteWithMoreAccess;

    @Inject
    private TopAccessPerPeriodAnalyticsService topAccessPerPeriodAnalyticsService;

    public Single<HttpResponse<?>> query(@Valid Map<String, String> params) {
        final var database = mongoClient.getDatabase("laa");

        return Flowable.fromPublisher(topInTheWorldAnalyticsService.get(database.getCollection("totalcount"), params))
            .mergeWith(topInTheWorldByRegionAnalyticsService.get(database.getCollection("totalcountregion"), params))
            .mergeWith(lessAccessInTheWorldAnalyticsService.get(database.getCollection("totalcount"), params))
            .mergeWith(theMinuteWithMoreAccess.get(database.getCollection("totalcounttimestamp"), params))
            .mergeWith(topAccessPerPeriodAnalyticsService.get(database.getCollection("accesslog"), params))
            .toList()
            .map(resultMapper());
    }

    private Function<List<Map<String, Object>>, HttpResponse<?>> resultMapper() {
        return maps -> {
            final var results =
                maps
                    .stream()
                    .collect(groupingBy(map -> map.get("name")))
                    .entrySet()
                    .stream()
                    .map(e ->
                        Map.of(
                            "category", e.getKey(),
                            "stats", e.getValue()
                        ))
                    .collect(toList());

            return HttpResponse.ok(results);
        };
    }


}
