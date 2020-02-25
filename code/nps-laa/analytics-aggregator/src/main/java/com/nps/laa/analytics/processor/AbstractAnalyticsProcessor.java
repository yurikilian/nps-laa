package com.nps.laa.analytics.processor;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.analytics.processor.ProcessorSubscriberFactory.ProcessorSubscriber;
import org.bson.Document;


abstract class AbstractAnalyticsProcessor implements AnalyticsProcessor {
    private final String database;
    private final MongoClient mongoClient;

    protected AbstractAnalyticsProcessor(String database,
                                         MongoClient mongoClient) {
        this.database = database;
        this.mongoClient = mongoClient;
    }

    protected ProcessorSubscriber getSubscriber() {
        return ProcessorSubscriberFactory.newSubscriber();
    }

    protected MongoCollection<Document> getCollection() {
        return
            this.mongoClient.getDatabase(this.database)
                .getCollection(this.collectionName());
    }

}
