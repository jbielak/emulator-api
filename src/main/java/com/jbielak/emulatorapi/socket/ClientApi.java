package com.jbielak.emulatorapi.socket;

import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.validator.IpAddress;

import java.io.BufferedReader;
import java.io.PrintWriter;

public interface ClientApi {

    LightweightSocket openConnection();
    LightweightSocket openConnection(@IpAddress String address, Integer port);
    LightweightSocket closeConnection();
    PrintWriter getPrintWriter();
    BufferedReader getBufferedReader();
}
