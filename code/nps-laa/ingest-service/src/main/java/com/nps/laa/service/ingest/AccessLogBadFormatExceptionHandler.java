package com.nps.laa.service.ingest;

import io.micronaut.context.annotation.Requires;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;

import javax.inject.Singleton;
import java.util.Map;


@Produces
@Singleton
@Requires(classes = {AccessLogBadFormatException.class, ExceptionHandler.class})
public class AccessLogBadFormatExceptionHandler implements
    ExceptionHandler<AccessLogBadFormatException, HttpResponse<?>> {

    @Override
    public HttpResponse<?> handle(final HttpRequest request,
                                  final AccessLogBadFormatException exception) {
        return HttpResponse.badRequest().body(Map.of("error", exception.getMessage()));
    }
}
