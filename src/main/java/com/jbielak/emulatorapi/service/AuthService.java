package com.jbielak.emulatorapi.service;

public interface AuthService {

    boolean auth();
    boolean auth(String authToken);
    boolean isAuthorized();
}
