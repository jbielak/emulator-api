package com.jbielak.emulatorapi.cmd.option;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum HealthStateOption {

    UNKNOWN("unknown"),
    GOOD("good"),
    OVERHEAT("overheat"),
    DEAD("dead"),
    OVERVOLTAGE("overvoltage"),
    FAILURE("failure");

    private static final Map<String, HealthStateOption> BY_VALUE_MAP = new LinkedHashMap<>();

    static {
        for (HealthStateOption option : HealthStateOption.values()) {
            BY_VALUE_MAP.put(option.healthStateOptionValue, option);
        }
    }

    private final String healthStateOptionValue;

    HealthStateOption(String healthStateOptionValue) {
        this.healthStateOptionValue = healthStateOptionValue;
    }

    public String getHealthStateOptionValue() {
        return healthStateOptionValue;
    }

    public static HealthStateOption forValue(String value) {
        return BY_VALUE_MAP.get(value);
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }

}
