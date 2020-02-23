package com.nps.laa.service.ingest;

import javax.inject.Singleton;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.function.Function;

@Singleton
public class AccessLogMapper implements Function<String, AccessLog> {
    @Override
    public AccessLog apply(String line) {

        final var tokenizer = new StringTokenizer(line);
        if (tokenizer.countTokens() != 4)
            throw new AccessLogBadFormatException(line);

        return AccessLog.builder()
            .endpoint(tokenizer.nextToken())
            .timestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(tokenizer.nextToken())),
                TimeZone.getDefault().toZoneId()))
            .userId(tokenizer.nextToken())
            .region(tokenizer.nextToken())
            .build();

    }
}
