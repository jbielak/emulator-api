package com.jbielak.emulatorapi.cmd.response;

public enum GeneralCommandResponse {

    AVD_STATUS_RUNNING("virtual device is running OK"),
    AVD_SNAPSHOT_OK("OK"),
    AVD_SNAPSHOT_LOAD_FAIL("KO: Device 'cache' does not have the requested snapshot 'saved'\n"
            + "KO: Snapshot load failure: snapshot doesn't exist"),
    PING_ALIVE("I am alive! OK"),
    KILL_SUCCESS("OK: killing emulator, bye bye OK");


    private final String generalCommandResponseValue;

    GeneralCommandResponse(String generalCommandResponseValue) {
        this.generalCommandResponseValue = generalCommandResponseValue;
    }

    public String getValue() {
        return generalCommandResponseValue;
    }
}
