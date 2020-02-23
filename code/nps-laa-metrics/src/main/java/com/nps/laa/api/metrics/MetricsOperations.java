package com.nps.laa.api.metrics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

public interface MetricsOperations {

    @Get
    Single<HttpResponse<?>> query();
}
