package com.nps.laa.gateway;

import com.nps.laa.IngestOperations;
import com.nps.laa.gateway.client.IngestClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;

@Controller("/laa/ingest")
public class IngestGatewayController implements IngestOperations {

    private final IngestClient client;

    public IngestGatewayController(IngestClient client) {
        this.client = client;
    }

    @Override
    public Single<HttpResponse<?>> ingest(List<String> accessLogs) {
        return client.ingest(accessLogs);
    }
}