package com.nps.laa.gateway.client;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("nps-laa-metrics")
public interface AnalyticsClient extends AnalyticsOperations {
}
