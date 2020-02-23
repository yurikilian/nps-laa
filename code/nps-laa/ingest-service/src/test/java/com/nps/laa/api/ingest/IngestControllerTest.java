package com.nps.laa.api.ingest;

import com.nps.laa.api.ingest.IngestClient;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.annotation.MicronautTest;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class IngestControllerTest {

    @Inject
    IngestClient ingestClient;

    @Test
    public void shouldReturnOkAndPersistLogsGivenCorrectRequest() {
        final Single<HttpResponse<?>> single = ingestClient.ingest(List.of(
            "/pets/exotic/cats/10 1037825323957 5b019db5-b3d0-46d2-9963-437860af707f 1",
            "/pets/guaipeca/dogs/1 1037825323957 5b019db5-b3d0-46d2-9963-437860af707g 2",
            "/tiggers/bid/now 1037825323957 5b019db5-b3d0-46d2-9963-437860af707e 3"
        ));

        final HttpResponse<?> response = single.blockingGet();
        assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void shouldReturnOkGivenEmptyRequest() {
        final HttpResponse<?> response = ingestClient.ingest(List.of()).blockingGet();
        assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    public void shouldReturnBadRequestGivenIncorrectRequest() {
        final Single<HttpResponse<?>> single = ingestClient.ingest(List.of(""));

        try {
            final HttpResponse<?> response = single.blockingGet();
        } catch (HttpClientResponseException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
        }
    }
}
