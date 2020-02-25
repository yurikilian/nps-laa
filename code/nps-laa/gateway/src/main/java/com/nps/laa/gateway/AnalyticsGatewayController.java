package com.nps.laa.gateway;


import com.nps.laa.analytics.AnalyticsOperations;
import com.nps.laa.gateway.client.AnalyticsClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;

@Controller("/laa/metrics")
public class AnalyticsGatewayController implements AnalyticsOperations {

    private final AnalyticsClient client;
    public AnalyticsGatewayController(AnalyticsClient client) {
        this.client = client;
    }

    @Override
    public Single<HttpResponse<List<String>>> query() {
        return client.query();
    }
}
