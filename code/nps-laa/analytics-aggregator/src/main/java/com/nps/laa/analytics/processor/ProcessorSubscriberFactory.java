package com.nps.laa.analytics.processor;

import io.reactivex.internal.subscribers.BlockingBaseSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProcessorSubscriberFactory {

    static ProcessorSubscriber newSubscriber() {
        return new ProcessorSubscriber();
    }

    static class ProcessorSubscriber extends BlockingBaseSubscriber<Object> {

        private final static Logger logger = LoggerFactory.getLogger(ProcessorSubscriber.class);

        @Override
        public void onNext(Object o) {
            logger.info(o.toString());
        }

        @Override
        public void onError(Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
