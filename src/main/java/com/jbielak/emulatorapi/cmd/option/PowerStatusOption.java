package com.jbielak.emulatorapi.cmd.option;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum PowerStatusOption {

    UNKNOWN("unknown"),
    CHARGING("charging"),
    DISCHARGING("discharging"),
    NOT_CHARGING("not-charging"),
    FULL("full");

    private static final Map<String, PowerStatusOption> BY_VALUE_MAP =
            Stream.of(values()).collect(
                    toMap(PowerStatusOption::getPowerStatusOptionOptionValue, e -> e));

    private final String powerStatusOptionValue;

    PowerStatusOption(String powerStatusOptionValue) {
        this.powerStatusOptionValue = powerStatusOptionValue;
    }

    public String getPowerStatusOptionOptionValue() {
        return powerStatusOptionValue;
    }

    public static Optional<PowerStatusOption> fromValue(String value) {
        return Optional.ofNullable(BY_VALUE_MAP.get(value));
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }

}
