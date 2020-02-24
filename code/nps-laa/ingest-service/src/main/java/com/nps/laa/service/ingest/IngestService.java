package com.nps.laa.service.ingest;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;
import com.nps.laa.service.ingest.domain.mapper.AccessLogMapper;
import com.nps.laa.service.ingest.domain.AccessLog;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class IngestService {

    private final AccessLogMapper mapper;
    private final MongoClient mongoClient;
    private final AccessLogEventProducer producer;

    public IngestService(AccessLogMapper mapper, MongoClient mongoClient, AccessLogEventProducer producer) {
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
            .doOnSuccess(success -> logs.forEach(producer::send));
    }


    private MongoCollection<AccessLog> getCollection() {
        return
            mongoClient.getDatabase("laa")
                .getCollection("access-log", AccessLog.class);
    }

}
