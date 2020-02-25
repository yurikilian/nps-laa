package com.nps.laa.service.analytics;

import com.nps.laa.AnalyticsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface AnalyticsTestClient extends AnalyticsOperations {
}
