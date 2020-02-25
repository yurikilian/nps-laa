package com.nps.laa.service.analytics;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.reactivex.Single;

import java.util.List;

@Controller
public class AnalyticsController implements AnalyticsOperations {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Override
    public Single<HttpResponse<List<String>>> query() {
        return analyticsService.query();
    }


}