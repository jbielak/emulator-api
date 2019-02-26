package com.jbielak.emulatorapi.socket;


import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.validation.IpAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
@Validated
public class EmulatorClient implements ClientApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmulatorClient.class);

    @IpAddress
    @Value("${socket.emulator.address}")
    private String LOCALHOST;

    @Value("${socket.emulator.port}")
    private Integer ANDROID_EMULATOR_PORT;

    private Socket socket;
    private PrintWriter printWriter = null;
    private BufferedReader bufferedReader = null;
    private String currentAddress = null;
    private Integer currentPort = null;

    @Override
    @PostConstruct
    public LightweightSocket openConnection() {
        return openConnection(LOCALHOST, ANDROID_EMULATOR_PORT);
    }

    @Override
    public LightweightSocket openConnection(String address, Integer port) {
        if (address == null && port == null) {
            currentAddress = LOCALHOST;
            currentPort = ANDROID_EMULATOR_PORT;
        } else {
            currentAddress = address;
            currentPort = port;
        }

        try {
            socket = new Socket(address, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            LOGGER.info(String.format("Connection with on %s, port %s.", currentAddress, currentPort));
            return new LightweightSocket(currentAddress, currentPort);
        } catch (IOException e) {
            LOGGER.error(String.format("Could not open Connection to %s on %s. Check if target machine is running",
                    currentAddress, currentPort), e);
        }

        return new LightweightSocket();
    }

    @Override
    @PreDestroy
    public LightweightSocket closeConnection() {
        LightweightSocket socketToClose = new LightweightSocket(currentAddress, currentPort);
        try {
            printWriter.close();
            bufferedReader.close();
            socket.shutdownOutput();
            socket.shutdownInput();
            socket.close();
            LOGGER.info(String.format("Socket connection with Android Emulator on %s, port %s closed.",
                    currentAddress, currentPort));
            currentAddress = null;
            currentPort = null;
            return socketToClose;
        } catch (IOException e) {
            LOGGER.error(String.format("Could not close Connection socket connection" +
                            "to Android Emulator on %s, port %s.",
                    currentAddress, currentPort), e);
        }
        return new LightweightSocket();
    }

    @Override
    public PrintWriter getPrintWriter() {
        return this.printWriter;
    }

    @Override
    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }

}
