package com.jbielak.emulatorapi.cmd.option;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum HealthStateOption {

    UNKNOWN("unknown"),
    GOOD("good"),
    OVERHEAT("overheat"),
    DEAD("dead"),
    OVERVOLTAGE("overvoltage"),
    FAILURE("failure");

    private static final Map<String, HealthStateOption> BY_VALUE_MAP =
            Stream.of(values()).collect(
                    toMap(HealthStateOption::getHealthStateOptionValue, e -> e));

    private final String healthStateOptionValue;

    HealthStateOption(String healthStateOptionValue) {
        this.healthStateOptionValue = healthStateOptionValue;
    }

    public String getHealthStateOptionValue() {
        return healthStateOptionValue;
    }

    public static Optional<HealthStateOption> fromValue(String value) {
        return Optional.ofNullable(BY_VALUE_MAP.get(value));
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }

}
