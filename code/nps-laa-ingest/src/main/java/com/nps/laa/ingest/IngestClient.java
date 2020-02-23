package com.nps.laa.ingest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import java.util.List;

@Client("/ingest")
public interface IngestClient extends IngestOperations{

    @Override
    Single<HttpResponse<?>> ingest(@Body List<String> accessLogs);

}
