package com.nps.laa.service.metrics;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.MetricsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.bson.Document;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller("/metrics")
public class MetricsController implements MetricsOperations {

    private final MongoClient mongoClient;

    public MetricsController(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public Single<HttpResponse<List<String>>> query() {
        final var predicate = List.of(
            Aggregates.group(
                Map.of(
                    "url", "$endpoint",
                    "region", "$region"
                ),
                Accumulators.sum("count", 1)
            )
        );

        return Flowable.fromPublisher(
            getCollection()
                .aggregate(predicate))
        .toList()
            .map(documents -> HttpResponse.ok(documents
                .stream()
                .map(Document::toJson).collect(Collectors.toList())));
    }

    private MongoCollection<Metric> getCollection() {
        return
            mongoClient.getDatabase("laa")
                .getCollection("access-log", Metric.class);
    }

}