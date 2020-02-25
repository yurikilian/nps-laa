package com.nps.laa.service.metrics;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;

@Controller
public class AnalyticsController implements AnalyticsOperations {

    private final MetricsService metricsService;

    public AnalyticsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public Single<HttpResponse<List<String>>> query() {
        return metricsService.query();
    }


}