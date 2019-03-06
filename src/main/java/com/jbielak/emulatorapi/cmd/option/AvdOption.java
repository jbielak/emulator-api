package com.jbielak.emulatorapi.cmd.option;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum AvdOption {

    STOP("stop"),
    START("start"),
    STATUS("status"),
    NAME("name");

    private static final Map<String, AvdOption> BY_VALUE_MAP = new LinkedHashMap<>();

    static {
        for (AvdOption option : AvdOption.values()) {
            BY_VALUE_MAP.put(option.avdOptionValue, option);
        }
    }

    private final String avdOptionValue;

    AvdOption(String avdOptionValue) {
        this.avdOptionValue = avdOptionValue;
    }

    public String getAvdOptionValue() {
        return avdOptionValue;
    }

    public static AvdOption forValue(String value) {
        return BY_VALUE_MAP.get(value);
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }
}
