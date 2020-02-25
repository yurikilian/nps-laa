package com.nps.laa.metrics;

import com.nps.laa.metrics.processor.AnalyticsProcessor;
import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;
import io.micronaut.context.BeanContext;


@RabbitListener
public class AccessLogEventListener {

    private final BeanContext beanContext;

    public AccessLogEventListener(BeanContext beanContext) {
        this.beanContext = beanContext;
    }

    @Queue("access-logs")
    public void consume(AccessLog accessLog) {
        System.out.println(accessLog);
        final var processors = beanContext.getBeansOfType(AnalyticsProcessor.class);

        processors
            .parallelStream()
            .forEach(processor -> processor.process(accessLog));


    }


}