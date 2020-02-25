package com.nps.laa.analytics.event;

import com.nps.laa.ingest.AccessLog;
import com.nps.laa.analytics.processor.AnalyticsProcessor;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import io.micronaut.context.BeanContext;


@RabbitListener
public class EventConsumer {

    private final BeanContext beanContext;

    public EventConsumer(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    @Binding("${event.routingKey}")
    public void consume(AccessLog accessLog) {
        final var processors = beanContext.getBeansOfType(AnalyticsProcessor.class);

        processors
            .parallelStream()
            .forEach(processor -> processor.process(accessLog));
    }


}