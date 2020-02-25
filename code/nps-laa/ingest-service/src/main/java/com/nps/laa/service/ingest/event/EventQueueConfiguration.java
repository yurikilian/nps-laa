package com.nps.laa.service.ingest.event;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;
import io.micronaut.context.annotation.Value;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class EventQueueConfiguration extends ChannelInitializer {


    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("nps", BuiltinExchangeType.DIRECT);
        channel.queueDeclare("log", true, false, false, null);
        channel.queueBind("log", "nps", "log.creation");
    }

}
