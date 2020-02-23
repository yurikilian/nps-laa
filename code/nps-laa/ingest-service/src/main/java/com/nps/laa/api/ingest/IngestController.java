package com.nps.laa.api.ingest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;


@Controller
public class IngestController implements IngestOperations {
    private final IngestHandler handler;

    public IngestController(IngestHandler handler) {
        this.handler = handler;
    }

    @Override
    public Single<HttpResponse<?>> ingest(@Body List<String> accessLogs) {
        return handler
            .handle(accessLogs)
            .map(accessLog -> HttpResponse.ok());
    }

}
