package com.nps.laa.gateway.client;

import com.nps.laa.IngestOperations;
import io.micronaut.http.client.annotation.Client;

@Client("nps-laa-ingest-service")
public interface IngestClient extends IngestOperations {
}
