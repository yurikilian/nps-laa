package com.nps.laa.service.analytics.domain.model;

import com.mongodb.client.model.Projections;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.reactivex.Flowable;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.inject.Singleton;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.mongodb.client.model.Accumulators.sum;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.descending;

@Singleton
public class AccessPerPeriodAnalyticsService implements AnalyticsQueryService {

    @Override
    public Flowable<Map<String, Object>> get(MongoCollection<Document> collection, Map<String, String> params) {

        final AccessPeriod accessPeriod = extractPeriod(params);
        return Flowable
            .fromPublisher(
                collection
                    /*
                     .find(
                         and(
                             gte("timestamp", accessPeriod.getStartDate()),
                             lt("timestamp", accessPeriod.getEndDate()))
                     )*/
                    .aggregate(

                            List.of(

                                project(Projections.fields(
                                    
                                ))
                            )

                    )
            )
            .map(document -> {

                return document;
                //final var id = (Document) document.get("_id");

                /*
                return Map.of(
                    "name", "The minute with more access",
                    "count", document.get("count"),
                    "minute", id.get("minute")
                );*/
            });
    }

    private AccessPeriod extractPeriod(Map<String, String> params) {
        final AccessPeriodType type = Optional.ofNullable(params.get("period"))
            .map(AccessPeriodType::fromValue).orElse(AccessPeriodType.YEAR);

        switch (type) {

            case DAY:
                return new AccessPeriod(
                    LocalDateTime.now().toLocalDate().atStartOfDay(),
                    LocalDateTime.now().toLocalDate().atTime(LocalTime.MAX));
            case WEEK:
                return new AccessPeriod(
                    LocalDateTime.now().with(DayOfWeek.SUNDAY), LocalDateTime.now().with(DayOfWeek.SATURDAY)
                );
            default:
                return new AccessPeriod(
                    LocalDateTime.now().with(TemporalAdjusters.firstDayOfYear()),
                    LocalDateTime.now().with(TemporalAdjusters.lastDayOfYear()));
        }
    }


    static class AccessPeriod {

        private final LocalDateTime startDate;
        private final LocalDateTime endDate;

        public LocalDateTime getStartDate() {
            return startDate;
        }

        public LocalDateTime getEndDate() {
            return endDate;
        }

        AccessPeriod(LocalDateTime startDate, LocalDateTime endDate) {
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


        return List.of(
            group(
                Map.of(
                    "_id", "$endpoint"

                    /*
                    "date", Map.of(
                        "$dateToString", Map.of(
                            "format", "%Y-%m-%d",
                            "date", "$timestamp"
                        )
                    )*/
                ), sum("count", 1)),

            match(
                and(
                    gte("timestamp", accessPeriod.getStartDate()),
                    lt("timestamp", accessPeriod.getEndDate())
                )
            ),
            sort(descending("count")),
            limit(1)
        );
    }
}
