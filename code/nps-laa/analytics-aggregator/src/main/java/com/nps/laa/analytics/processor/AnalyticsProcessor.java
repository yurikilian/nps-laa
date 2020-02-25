package com.nps.laa.analytics.processor;


import com.nps.laa.ingest.AccessLog;

public interface AnalyticsProcessor {

    void process(AccessLog accessLog);

}
