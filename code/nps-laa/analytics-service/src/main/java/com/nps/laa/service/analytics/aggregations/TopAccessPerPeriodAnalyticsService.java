package com.nps.laa.service.analytics.aggregations;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.nps.laa.service.analytics.core.DateUtils;
import io.reactivex.Flowable;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.inject.Singleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.expr;
import static com.mongodb.client.model.Sorts.descending;
import static org.bson.Document.parse;

@Singleton
public class TopAccessPerPeriodAnalyticsService implements AnalyticsQueryService {

    @Override
    public Flowable<Map<String, Object>> get(MongoCollection<Document> collection, Map<String, String> params) {

        return Flowable
            .fromPublisher(
                collection.aggregate(accessDayByPeriod(extractPeriod(params)))
            )
            .map(document -> {
                final var id = (Document) document.get("_id");
                return Map.of(
                    "name", "Top Access per Period",
                    "count", document.get("count"),
                    "endpoint", id.get("_id")
                );
            });
    }


    private AccessPeriod extractPeriod(Map<String, String> params) {
        final AccessPeriodType type = Optional.ofNullable(params.get("period"))
            .map(AccessPeriodType::fromValue).orElse(AccessPeriodType.YEAR);

        switch (type) {
            case DAY:
                return new AccessPeriod(
                    LocalDate.now(),
                    LocalDate.now().plusDays(1L));
            case WEEK:
                final LocalDate[] weekdays = DateUtils.getWeekdays(LocalDate.now());
                return new AccessPeriod(weekdays[0], weekdays[1]);
            default:
                return new AccessPeriod(
                    LocalDate.now().with(TemporalAdjusters.firstDayOfYear()),
                    LocalDate.now().with(TemporalAdjusters.lastDayOfYear()));
        }
    }


    static class AccessPeriod {

        private final LocalDate startDate;
        private final LocalDate endDate;

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        AccessPeriod(LocalDate startDate, LocalDate endDate) {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    enum AccessPeriodType {
        DAY("day"),
        WEEK("week"),
        YEAR("year");

        AccessPeriodType(String value) {
            this.value = value;
        }

        private String value;

        public static AccessPeriodType fromValue(String value) {
            for (AccessPeriodType period : values()) {
                if (period.value.equals(value))
                    return period;
            }

            return AccessPeriodType.YEAR;
        }


    }

    private List<Bson> accessDayByPeriod(AccessPeriod accessPeriod) {
        final var formattedStartDate =
            accessPeriod.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        final var formattedEndDate =
            accessPeriod.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        System.out.println("Formatted Date: " + formattedStartDate);
        System.out.println("Formatted Date: " + formattedEndDate);

        return List.of(
            match(
                expr(
                    parse(rawMatchExpression(formattedStartDate, formattedEndDate))
                )
            ),
            group(
                Map.of(
                    "_id", "$endpoint"
                ),
                sum("count", 1)
            ),
            sort(descending("count")),
            limit(3)
        );
    }

    private String rawMatchExpression(String formattedStartDate, String formattedEndDate) {
        return "{\"$and\":" +
            "[{\"$gte\":[\"$timestamp\",{\"$dateFromString\":{\"dateString\":\"" + formattedStartDate + "\",\"format\":\"%Y-%m-%d\"}}]}," +
            "{\"$lte\":[\"$timestamp\",{\"$dateFromString\":{\"dateString\":\"" + formattedEndDate + "\",\"format\":\"%Y-%m-%d\"}}]}]}";
    }
}
