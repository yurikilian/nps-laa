package nps.laa.ingest;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Single;

import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class IngestHandler {

    private final AccessLogMapper mapper;
    private final MongoClient mongoClient;

    public IngestHandler(AccessLogMapper mapper, MongoClient mongoClient) {
        this.mapper = mapper;
        this.mongoClient = mongoClient;
    }

    Single<List<AccessLog>> handle(final List<String> accessLogs) {
        final var logs = accessLogs.stream().map(mapper).collect(Collectors.toList());

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
