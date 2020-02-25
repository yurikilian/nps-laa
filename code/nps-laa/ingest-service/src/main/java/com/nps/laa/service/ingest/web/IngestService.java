package com.nps.laa.service.ingest.web;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import com.nps.laa.ingest.AccessLog;
import com.nps.laa.service.ingest.event.AccessLogCreationEventProducer;
import com.nps.laa.service.ingest.web.mapper.AccessLogMapper;
import io.micronaut.context.annotation.Value;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class IngestService {


    private final String database;
    private final String collection;

    private final AccessLogMapper mapper;
    private final MongoClient mongoClient;
    private final AccessLogCreationEventProducer producer;


    public IngestService(@Value("${database}") String database,
                         @Value("${collection}") String collection,
                         AccessLogMapper mapper,
                         MongoClient mongoClient,
                         AccessLogCreationEventProducer producer) {
        this.database = database;
        this.collection = collection;
        this.mapper = mapper;
        this.mongoClient = mongoClient;
        this.producer = producer;
    }

    Single<Success> handle(final List<String> accessLogs) {
        final var logs = accessLogs.stream().map(mapper).collect(Collectors.toList());

        if (logs.isEmpty()) {
            return Single.just(Success.SUCCESS);
        }

        return Single.fromPublisher(getCollection()
            .insertMany(logs))
            .doOnSuccess(success -> logs.forEach(accessLog -> {

                producer.send(accessLog).subscribe((o, throwable) -> {
                    System.out.println(o);

                    if (throwable != null)
                        throwable.printStackTrace();


                });
            }));
    }

    private MongoCollection<AccessLog> getCollection() {
        return
            mongoClient.getDatabase(this.database)
                .getCollection(this.collection, AccessLog.class);
    }

}
