package com.nps.laa.analytics.processor;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.ingest.AccessLog;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;

@Singleton
public class EndpointCountPerDateAnalytics implements AnalyticsProcessor {

    private final Logger logger = LoggerFactory.getLogger(EndpointCountPerDateAnalytics.class);
    private final MongoClient mongoClient;

    public EndpointCountPerDateAnalytics(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void process(AccessLog accessLog) {
        final MongoCollection<Document> collection =
            this.mongoClient.getDatabase("laa")
                .getCollection("totalcounttimestamp");


        collection.updateOne(
            combine(eq("url", accessLog.getEndpoint()),
                eq("timestamp", accessLog.getTimestamp())),
            combine(inc("count", 1)),

            new UpdateOptions().upsert(true))
            .subscribe(new BlockingBaseSubscriber<>() {
                @Override
                public void onNext(UpdateResult updateResult) {
                    logger.info("Endpoint count per region updated");
                }

                @Override
                public void onError(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
    }
}
