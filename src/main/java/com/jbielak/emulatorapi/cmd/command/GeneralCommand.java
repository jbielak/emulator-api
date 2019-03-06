package com.jbielak.emulatorapi.cmd.command;

public enum GeneralCommand {

    ROTATE("rotate"),
    AVD("avd %s"),
    AVD_SNAPSHOT("avd snapshot %s %s"),
    PING("ping"),
    KILL("kill");

    private final String generalCommandValue;

    GeneralCommand(String generalCommandValue) {
        this.generalCommandValue = generalCommandValue;
    }

    public String getValue() {
        return this.generalCommandValue;
    }
}
