package com.nps.laa.analytics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import java.util.List;

import static io.micronaut.http.MediaType.APPLICATION_JSON;

public interface AnalyticsOperations {

    @Get(produces = APPLICATION_JSON)
    Single<HttpResponse<List<String>>> query();
}
