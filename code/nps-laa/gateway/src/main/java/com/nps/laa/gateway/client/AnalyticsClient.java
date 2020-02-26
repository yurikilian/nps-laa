package com.nps.laa.gateway.client;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.Map;

@Client("nps-laa-analytics")
public interface AnalyticsClient extends AnalyticsOperations {

    @Override
    Single<HttpResponse<?>> query(@Nullable @Valid Map<String, String> params);
}
