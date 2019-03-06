package com.jbielak.emulatorapi.cmd.option;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public enum AvdSnapshotOption {

    LIST("list"),
    SAVE("save"),
    LOAD("load"),
    DELETE("delete");

    private static final Map<String, AvdSnapshotOption> BY_VALUE_MAP = new LinkedHashMap<>();

    private final String avdSnapshotOptionValue;

    AvdSnapshotOption(String avdSnapshotOptionValue) {
        this.avdSnapshotOptionValue = avdSnapshotOptionValue;
    }

    static {
        for (AvdSnapshotOption option : AvdSnapshotOption.values()) {
            BY_VALUE_MAP.put(option.avdSnapshotOptionValue, option);
        }
    }

    public String getAvdSnapshotOptionValue() {
        return avdSnapshotOptionValue;
    }

    public static AvdSnapshotOption forValue(String value) {
        return BY_VALUE_MAP.get(value);
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }
}
