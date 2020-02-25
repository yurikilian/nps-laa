package com.nps.laa.service.analytics;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.annotation.Client;
import io.reactivex.Single;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Client("/")
public interface AnalyticsTestClient extends AnalyticsOperations {

    @Override
    Single<HttpResponse<List<String>>> query(@Nullable @Valid Map<String, String> params);
}
