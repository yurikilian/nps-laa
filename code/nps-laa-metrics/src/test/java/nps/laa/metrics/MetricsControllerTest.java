package nps.laa.metrics;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class MetricsControllerTest {


    @Inject
    private MetricsClient client;

    @Test
    public void shouldReturnOkWithEmptyResponseGivenEmptyRequest() throws Exception {
        final HttpResponse<?> response = client.query().blockingGet();
        assertEquals(HttpStatus.OK, response.getStatus());
    }
}
