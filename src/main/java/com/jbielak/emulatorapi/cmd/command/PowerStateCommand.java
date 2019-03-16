package com.jbielak.emulatorapi.cmd.command;

public enum PowerStateCommand {

    POWER_DISPLAY("power display"),
    POWER_AC("power ac %s"),
    POWER_STATUS("power status %s"),
    POWER_PRESENT("power present %s"),
    POWER_HEALTH("power health %s"),
    POWER_POWER_CAPACITY("power capacity %s");

    private final String powerStateCommandValue;

    PowerStateCommand(String powerStateCommandValue) {
        this.powerStateCommandValue = powerStateCommandValue;
    }

    public String getValue() {
        return this.powerStateCommandValue;
    }
}
