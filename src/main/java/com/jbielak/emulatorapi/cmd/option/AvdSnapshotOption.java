package com.jbielak.emulatorapi.cmd.option;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum AvdSnapshotOption {

    LIST("list"),
    SAVE("save"),
    LOAD("load"),
    DELETE("delete");

    private static final Map<String, AvdSnapshotOption> BY_VALUE_MAP =
            Stream.of(values()).collect(
                    toMap(AvdSnapshotOption::getAvdSnapshotOptionValue, e -> e));

    private final String avdSnapshotOptionValue;

    AvdSnapshotOption(String avdSnapshotOptionValue) {
        this.avdSnapshotOptionValue = avdSnapshotOptionValue;
    }

    public String getAvdSnapshotOptionValue() {
        return avdSnapshotOptionValue;
    }

    public static Optional<AvdSnapshotOption> fromValue(String value) {
        return Optional.ofNullable(BY_VALUE_MAP.get(value));
    }

    public static Set<String> getValues() {
        return BY_VALUE_MAP.keySet();
    }
}
