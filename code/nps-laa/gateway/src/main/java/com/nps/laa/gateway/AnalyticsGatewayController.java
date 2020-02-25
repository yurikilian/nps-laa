package com.nps.laa.gateway;


import com.nps.laa.analytics.AnalyticsOperations;
import com.nps.laa.analytics.AnalyticsQueryParameters;
import com.nps.laa.gateway.client.AnalyticsClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.reactivex.Single;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller("/laa/metrics")
public class AnalyticsGatewayController implements AnalyticsOperations {

    private final AnalyticsClient client;

    public AnalyticsGatewayController(AnalyticsClient client) {
        this.client = client;
    }

    @Override
    @Get("/{?params*}")
    public Single<HttpResponse<List<String>>> query(@Valid @Nullable Map<String,String> params) {
        return client.query(params);
    }

}
