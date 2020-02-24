package com.nps.laa.service.ingest;


import com.nps.laa.service.ingest.domain.AccessLog;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient
public interface AccessLogEventProducer  {

    @Binding("access-logs")
    void send(AccessLog accessLog);

}
