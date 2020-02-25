package com.nps.laa.metrics.processor;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.metrics.AccessLog;
import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

@Singleton
public class EndpointCountAnalyticsProcessor implements AnalyticsProcessor {

    private final Logger logger = LoggerFactory.getLogger(EndpointCountAnalyticsProcessor.class);
    private final MongoClient mongoClient;

    public EndpointCountAnalyticsProcessor(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void process(AccessLog accessLog) {
        final MongoCollection<Document> collection =
            this.mongoClient.getDatabase("laa")
                .getCollection("totalcount");


        collection.updateOne(
            eq("url", accessLog.getEndpoint()),
            combine(inc("count", 1)),
            new UpdateOptions().upsert(true))
            .subscribe(new BlockingBaseSubscriber<>() {
                @Override
                public void onNext(UpdateResult updateResult) {
                    logger.info("Endpoint count updated");
                }

                @Override
                public void onError(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
    }
}
