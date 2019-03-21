package com.jbielak.emulatorapi.cmd.option;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum AvdOption {

    STOP("stop"),
    START("start"),
    STATUS("status"),
    NAME("name");

    private static final Map<String, AvdOption> BY_VALUE_MAP =
            Stream.of(values()).collect(
                    toMap(AvdOption::getAvdOptionValue, e -> e));

    private final String avdOptionValue;

    AvdOption(String avdOptionValue) {
        this.avdOptionValue = avdOptionValue;
    }

    public String getAvdOptionValue() {
        return avdOptionValue;
    }

    public static Optional<AvdOption> fromValue(String value) {
        return Optional.ofNullable(BY_VALUE_MAP.get(value));
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }
}
