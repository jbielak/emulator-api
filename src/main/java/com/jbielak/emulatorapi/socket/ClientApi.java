package com.jbielak.emulatorapi.socket;

import com.jbielak.emulatorapi.dto.LightweightSocket;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ClientApi {

    LightweightSocket openConnection();
    LightweightSocket openConnection(String address, Integer port);
    LightweightSocket closeConnection();
    PrintWriter getPrintWriter();
    BufferedReader getBufferedReader();
}
