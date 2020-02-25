package com.nps.laa.service.metrics;


import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.service.metrics.domain.model.Metric;
import io.micronaut.http.HttpResponse;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

@Singleton
public class MetricsService {

    private final MongoClient mongoClient;

    public MetricsService(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Single<HttpResponse<List<String>>> query() {
        final List<Bson> predicate = accessDayByPeriod();

        return Flowable.fromPublisher(
            getCollection()
                .aggregate(predicate))
            .toList()
            .map(documents -> HttpResponse.ok(documents
                .stream()
                .map(Document::toJson).collect(Collectors.toList())));

    }

    private List<Bson> topThreeUrlAroundTheWorld() {
        return List.of(
            group("$endpoint", sum("count", 1)),
            sort(ascending("count")),
            limit(3)
        );
    }

    private List<Bson> topThreeUrlAroundTheWorldByRegion() {
        return List.of(
            group(
                Map.of("_id", "$endpoint",
                    "region", "$region"),
                sum("count", 1)
            ),
            sort(ascending("count")),
            limit(3)
        );
    }

    private List<Bson> lessAccessWorld() {
        return List.of(
            group(
                "$endpoint",
                sum("count", 1)
            ),
            sort(descending("count")),
            limit(1)
        );
    }


    //TODO: better filter
    private List<Bson> accessDayByPeriod() {
        return List.of(
            group(
                Map.of(
                    "_id", "$endpoint",
                    "date", Map.of(
                        "$dateToString", Map.of(
                            "format", "%Y-%m-%d",
                            "date", "$timestamp"
                        )
                    )
                ),
                sum("count", 1)),

            match(
                Filters.lt("date", LocalDateTime.now())
            ),
            match(
                Filters.gte("date", LocalDateTime.now())
            ),
            sort(descending("count")),
            limit(1)
        );
    }


    private List<Bson> minuteWithMoreAccess() {
        return List.of(
            group(
                Map.of(
                    "_id", "$endpoint",
                    "minute", Map.of(
                        "$dateToString", Map.of(
                            "format", "%M",
                            "date", "$timestamp"
                        )
                    )
                ),
                sum("count", 1)),
            sort(descending("count")),
            limit(1)
        );
    }



    private MongoCollection<Metric> getCollection() {
        return
            mongoClient.getDatabase("laa")
                .getCollection("access-log", Metric.class);
    }

}
