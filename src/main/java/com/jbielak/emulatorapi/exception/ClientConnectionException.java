package com.jbielak.emulatorapi.exception;

import java.io.IOException;

public class ClientConnectionException extends IOException {

    public ClientConnectionException(String message) {
        super(message);
    }

    public ClientConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
