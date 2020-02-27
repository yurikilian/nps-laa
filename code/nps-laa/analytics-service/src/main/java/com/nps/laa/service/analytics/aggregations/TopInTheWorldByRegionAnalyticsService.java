package com.nps.laa.service.analytics.aggregations;

import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;

import javax.inject.Singleton;
import java.util.Map;

import static com.mongodb.client.model.Sorts.descending;

@Singleton
public class TopInTheWorldByRegionAnalyticsService implements AnalyticsQueryService {

    @Override
    public Flowable<Map<String, Object>> get(MongoCollection<Document> collection,
                                             Map<String, String> params) {

        return Flowable
            .fromPublisher(collection
                .find().limit(3)
                .sort(descending("count")))
            .map(document -> Map.of(
                "name", "Top 3 in world by region",
                "count", document.get("count"),
                "region", document.get("region")
            ));
    }

}
