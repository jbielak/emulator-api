package com.jbielak.emulatorapi.cmd.command;

public enum CrashCommand {

    CRASH("crash"),
    CRASH_ON_EXIT("crash-on-exit");

    private final String crashCommandValue;

    CrashCommand(String crashCommandValue) {
        this.crashCommandValue = crashCommandValue;
    }

    public String getValue() {
        return this.crashCommandValue;
    }
}
