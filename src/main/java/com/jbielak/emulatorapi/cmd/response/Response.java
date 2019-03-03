package com.jbielak.emulatorapi.cmd.response;

public enum Response {

    OK("Android Console: type 'help' for a list of commands OK"),
    UNKNOWN_COMMAND("KO: unknown command, try 'help'");

    private final String resValue;

    Response(String resValue) {
        this.resValue = resValue;
    }

    public String getGeneralResponseValue() {
        return resValue;
    }
}
