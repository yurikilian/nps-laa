package com.nps.laa.service.ingest;


import com.nps.laa.ingest.IngestOperations;
import io.micronaut.http.client.annotation.Client;

@Client("/")
public interface IngestServiceTestClient extends IngestOperations {
}
