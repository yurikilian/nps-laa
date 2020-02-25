package com.nps.laa.service.metrics;

import com.nps.laa.AnalyticsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface AnalyticsTestClient extends AnalyticsOperations {
}
