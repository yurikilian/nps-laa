package nps.laa.metrics;

import io.micronaut.http.client.annotation.Client;

@Client("/metrics")
public interface MetricsClient extends MetricsOperations {
}
