package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.exception.ClientConnectionException;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.validator.IpAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emulator_client")
@Validated
public class EmulatorClientController {

    @Autowired
    private ClientApi emulatorClient;

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> connect() throws ClientConnectionException {

        LightweightSocket lightweightSocket = emulatorClient.openConnection();
        if (lightweightSocket.isEmpty()) {
            throw new ClientConnectionException("Could not connect to configured client.");
        }
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

    @RequestMapping(value = "/connect/{address}/{port}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> connect(@PathVariable @IpAddress String address,
                                              @PathVariable Integer port)
            throws ClientConnectionException {

        LightweightSocket lightweightSocket = emulatorClient.openConnection(address, port);
        if (lightweightSocket.isEmpty()) {
            throw new ClientConnectionException(String
                    .format("Could not connect to client on address: %s, port: %s.",
                    address, port));
        }
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> close() throws ClientConnectionException {

        LightweightSocket lightweightSocket = emulatorClient.closeConnection();
        if (lightweightSocket.isEmpty()) {
            throw new ClientConnectionException("Could not close client connection.");
        }
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

}
