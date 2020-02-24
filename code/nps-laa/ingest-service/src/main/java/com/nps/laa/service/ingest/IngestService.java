package com.nps.laa.service.ingest;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.service.ingest.domain.mapper.AccessLogMapper;
import com.nps.laa.service.ingest.domain.AccessLog;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class IngestService {

    private final AccessLogMapper mapper;
    private final MongoClient mongoClient;

    public IngestService(AccessLogMapper mapper, MongoClient mongoClient) {
        this.mapper = mapper;
        this.mongoClient = mongoClient;
    }

    Single<List<AccessLog>> handle(final List<String> accessLogs) {
        final var logs = accessLogs.stream().map(mapper).collect(Collectors.toList());

        if (logs.isEmpty()) {
            return Single.just(Collections.emptyList());
        }

        return Single.fromPublisher(getCollection()
            .insertMany(logs))
            .map(success -> logs);
    }


    private MongoCollection<AccessLog> getCollection() {
        return
            mongoClient.getDatabase("laa")
                .getCollection("access-log", AccessLog.class);
    }

}
