package com.nps.laa.service.metrics;

import com.nps.laa.MetricsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface MetricsServiceTestClient extends MetricsOperations {
}
