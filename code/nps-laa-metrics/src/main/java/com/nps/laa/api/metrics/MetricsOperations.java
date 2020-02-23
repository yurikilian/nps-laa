package com.nps.laa.api.metrics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;
import org.bson.Document;

import java.util.List;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

public interface MetricsOperations {

    @Get(produces = APPLICATION_JSON)
    Single<HttpResponse<List<Document>>> query();
}
