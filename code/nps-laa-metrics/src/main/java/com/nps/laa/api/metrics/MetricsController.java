package com.nps.laa.api.metrics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

@Controller("/metrics")
public class MetricsController implements MetricsOperations {

    @Override
    public Single<HttpResponse<?>> query() {
        return Single.just(HttpResponse.ok());
    }
}