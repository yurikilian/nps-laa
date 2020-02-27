package com.nps.laa.analytics.processor;

import com.mongodb.client.model.UpdateOptions;
import com.mongodb.reactivestreams.client.MongoClient;
import com.nps.laa.ingest.AccessLog;
import io.micronaut.context.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.inc;

@Singleton
public class EndpointCountPerDateAnalytics extends AbstractAnalyticsProcessor {

    private final Logger logger = LoggerFactory.getLogger(EndpointCountPerDateAnalytics.class);

    public EndpointCountPerDateAnalytics(@Value("${database}") String database,
                                         MongoClient mongoClient) {
        super(database, mongoClient);
    }

    @Override
    public String collectionName() {
        return "totalcounttimestamp";
    }

    @Override
    public void process(AccessLog accessLog) {
        getCollection().updateOne(
            combine(
                eq("url", accessLog.getEndpoint()),
                eq("day", accessLog.getTimestamp().getDayOfMonth()),
                eq("month", accessLog.getTimestamp().getMonthValue()),
                eq("year", accessLog.getTimestamp().getYear()),
                eq("hour", accessLog.getTimestamp().getHour()),
                eq("minute", accessLog.getTimestamp().getMinute())
            ),
            combine(inc("count", 1)),

            new UpdateOptions().upsert(true))
            .subscribe(getSubscriber());
    }
}
