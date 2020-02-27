package com.nps.laa.service.analytics.domain.model;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;

import javax.inject.Singleton;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Sorts.ascending;

@Singleton
public class TheMinuteWithMoreAccess implements AnalyticsQueryService {

    @Override
    public Flowable<Map<String, Object>> get(MongoCollection<Document> collection,
                                             Map<String, String> params) {

        return Flowable
            .fromPublisher(collection
                .aggregate(List.of(
                    group(
                        Map.of("_id", "null",
                            "minute", "$minute"),
                        sum("count", 1)
                    ),
                    sort(ascending("count")),
                    limit(3)
                ))
            )
            .map(document -> {
                final var id = (Document) document.get("_id");
                return Map.of(
                    "name", "The minute with more access",
                    "count", document.get("count"),
                    "minute", id.get("minute")
                );
            });
    }

}
