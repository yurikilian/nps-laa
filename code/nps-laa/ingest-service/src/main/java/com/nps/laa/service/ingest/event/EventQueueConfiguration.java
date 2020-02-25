package com.nps.laa.service.ingest.event;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class EventQueueConfiguration extends ChannelInitializer {

    @Value("${event.exchange}")
    private String exchange;

    @Value("${event.queue}")
    private String queue;

    @Value("${event.routingKey}")
    private String routingKey;

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(queue, true, false, false, null);
        channel.queueBind(queue, exchange, routingKey);
    }

}
