package com.nps.laa.gateway;


import com.nps.laa.MetricsOperations;
import com.nps.laa.gateway.client.MetricsClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;

@Controller("/laa/metrics")
public class MetricsGatewayController implements MetricsOperations {

    private final MetricsClient client;
    public MetricsGatewayController(MetricsClient client) {
        this.client = client;
    }

    @Override
    public Single<HttpResponse<List<String>>> query() {
        return client.query();
    }
}
