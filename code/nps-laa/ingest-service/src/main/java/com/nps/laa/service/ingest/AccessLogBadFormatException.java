package com.nps.laa.service.ingest;

public class AccessLogBadFormatException extends RuntimeException {
    public AccessLogBadFormatException(final String line) {
        super(String.format("Bad format for line [%s]", line));
    }
}
