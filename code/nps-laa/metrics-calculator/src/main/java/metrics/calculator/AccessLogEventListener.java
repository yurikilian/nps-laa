package metrics.calculator;

import io.micronaut.configuration.rabbitmq.annotation.Queue;
import io.micronaut.configuration.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class AccessLogEventListener {


    @Queue("access-logs")
    public void consume(AccessLog accessLog) {
        System.out.println(accessLog);
    }


}