package com.nps.laa.metrics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import io.micronaut.configuration.rabbitmq.connect.ChannelInitializer;

import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

    @Override
    public void initialize(Channel channel) throws IOException {
        channel.exchangeDeclare("nps-laa", BuiltinExchangeType.DIRECT);
        channel.queueDeclare("access-logs", true, false, false, null);
        channel.queueBind("access-logs", "npa-laa", "access-logs");
    }
}
