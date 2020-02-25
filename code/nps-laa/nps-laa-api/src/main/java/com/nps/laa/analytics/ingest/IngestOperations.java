package com.nps.laa.analytics.ingest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import java.util.List;

public interface IngestOperations {

    @Post(produces = MediaType.APPLICATION_JSON)
    Single<HttpResponse<?>> ingest(@Body List<String> accessLogs);

}
