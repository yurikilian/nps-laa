package com.nps.laa.service.analytics.domain.model;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;

import javax.inject.Singleton;
import java.util.Map;

import static com.mongodb.client.model.Sorts.descending;

@Singleton
public class LessAccessInTheWorldAnalyticsService implements AnalyticsQueryService {

    @Override
    public Flowable<Map<String, Object>> get(MongoCollection<Document> collection,
                                             Map<String, String> params) {

        return Flowable
            .fromPublisher(collection
                .find()
                .sort(descending("count"))
                .limit(1)
            )
            .map(document -> Map.of(
                "Name", "Top 3 in world",
                "url", document.get("url"),
                "count", document.get("count")
            ));
    }

}
