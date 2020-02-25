package com.nps.laa.service.ingest.event;


import com.nps.laa.ingest.AccessLog;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;
import io.reactivex.Single;

@RabbitClient("nps")
public interface AccessLogCreationEventProducer {

    @Binding("log.creation")
    Single<?> send(AccessLog accessLog);

}
