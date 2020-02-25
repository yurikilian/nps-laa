package com.nps.laa.analytics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface AnalyticsOperations {

    @Get("/{?params*}")
    Single<HttpResponse<List<String>>> query(@Valid @Nullable Map<String,String> params);
}
