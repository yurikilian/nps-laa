package com.nps.laa.gateway.client;

import com.nps.laa.MetricsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("nps-laa-metrics")
public interface MetricsServiceClient extends MetricsOperations {
}
