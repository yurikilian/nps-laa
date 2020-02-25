package com.nps.laa.service.ingest.web;

import com.nps.laa.ingest.IngestOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;


@Controller
public class IngestController implements IngestOperations {
    private final IngestService handler;

    public IngestController(IngestService handler) {
        this.handler = handler;
    }

    @Override
    public Single<HttpResponse<?>> ingest(@Body List<String> accessLogs) {
        return handler
            .handle(accessLogs)
            .map(accessLog -> HttpResponse.ok());
    }

}
