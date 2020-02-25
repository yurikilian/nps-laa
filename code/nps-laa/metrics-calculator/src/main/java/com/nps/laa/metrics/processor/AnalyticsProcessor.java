package com.nps.laa.metrics.processor;

import com.nps.laa.metrics.AccessLog;

public interface AnalyticsProcessor {

    void process(AccessLog accessLog);

}
