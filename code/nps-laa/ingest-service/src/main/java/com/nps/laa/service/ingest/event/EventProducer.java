package com.nps.laa.service.ingest.event;


import com.nps.laa.ingest.AccessLog;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient
public interface EventProducer {

    @Binding("${event.routingKey}")
    void send(AccessLog accessLog);

}
