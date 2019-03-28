package com.jbielak.emulatorapi.cmd.option;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum SentenceType {
    
    GPGGA("$GPGGA"),
    GPRMC("$GPRMC");

    private static final Map<String, SentenceType> BY_VALUE_MAP =
            Stream.of(values()).collect(
                    toMap(SentenceType::getSentenceTypeValue, e -> e));

    private final String sentenceTypeValue;

    SentenceType(String sentenceTypeValue) {
        this.sentenceTypeValue = sentenceTypeValue;
    }

    public String getSentenceTypeValue() {
        return sentenceTypeValue;
    }

    public static Optional<SentenceType> fromValue(String value) {
        return Optional.ofNullable(BY_VALUE_MAP.get(value));
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }
}
