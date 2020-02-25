package com.nps.laa.analytics.event;

import com.nps.laa.ingest.AccessLog;
import com.nps.laa.analytics.processor.AnalyticsProcessor;
import io.micronaut.configuration.rabbitmq.annotation.Binding;
import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import io.micronaut.context.BeanContext;


@RabbitListener
public class AccessLogCreationEventConsumer {

    private final BeanContext beanContext;

    public AccessLogCreationEventConsumer(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    @Queue("log")
    public void consume(AccessLog accessLog) {
        final var processors = beanContext.getBeansOfType(AnalyticsProcessor.class);

        processors
            .parallelStream()
            .forEach(processor -> processor.process(accessLog));
    }


}