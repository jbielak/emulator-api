package com.jbielak.emulatorapi.cmd.response;

public enum AuthResponse {

    AUTH_MISSING_TOKEN("KO: missing authentication token"),
    AUTH_TOKEN_NOT_MATCH("KO: authentication token does not match ~/.emulator_console_auth_token");

    private final String authResValue;

    AuthResponse(String authResValue) {
        this.authResValue = authResValue;
    }

    public String getAuthResValue() {
        return authResValue;
    }
}
