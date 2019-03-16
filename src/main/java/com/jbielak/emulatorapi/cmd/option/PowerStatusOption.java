package com.jbielak.emulatorapi.cmd.option;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum PowerStatusOption {

    UNKNOWN("unknown"),
    CHARGING("charging"),
    DISCHARGING("discharging"),
    NOT_CHARGING("not-charging"),
    FULL("full");

    private static final Map<String, PowerStatusOption> BY_VALUE_MAP = new LinkedHashMap<>();

    static {
        for (PowerStatusOption option : PowerStatusOption.values()) {
            BY_VALUE_MAP.put(option.powerStatusOptionValue, option);
        }
    }

    private final String powerStatusOptionValue;

    PowerStatusOption(String powerStatusOptionValue) {
        this.powerStatusOptionValue = powerStatusOptionValue;
    }

    public String getAvdOptionValue() {
        return powerStatusOptionValue;
    }

    public static PowerStatusOption forValue(String value) {
        return BY_VALUE_MAP.get(value);
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }

}
