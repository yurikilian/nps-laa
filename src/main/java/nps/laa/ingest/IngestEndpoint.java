package nps.laa.ingest;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;

import java.util.List;

@Controller("/ingest")
public class IngestEndpoint {
    private final IngestHandler handler;

    public IngestEndpoint(IngestHandler handler) {
        this.handler = handler;
    }

    @Post(produces = MediaType.APPLICATION_JSON)
    public Single<HttpResponse<?>> ingest(@Body List<String> accessLogs) {

        return handler
                .handle(accessLogs)
                .map(accessLog -> HttpResponse.ok());

    }

}
