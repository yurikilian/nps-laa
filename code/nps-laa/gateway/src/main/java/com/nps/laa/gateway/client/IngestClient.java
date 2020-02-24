package com.nps.laa.gateway.client;

import com.nps.laa.IngestOperations;
import com.nps.laa.MetricsOperations;
import io.micronaut.http.client.annotation.Client;

@Client("nps-laa-ingest-service")
public interface IngestClient extends IngestOperations {
}
