package com.nps.laa.analytics.processor;


import com.nps.laa.ingest.AccessLog;

public interface AnalyticsProcessor {
    String collectionName();
    void process(AccessLog accessLog);
}
