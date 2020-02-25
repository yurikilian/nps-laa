package com.nps.laa.service.analytics;

import com.nps.laa.analytics.AnalyticsOperations;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.reactivex.Single;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class AnalyticsController implements AnalyticsOperations {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @Get("/{?params*}")
    public Single<HttpResponse<List<String>>> query(@Valid @Nullable Map<String, String> params) {
        return analyticsService.query(params);
    }


}