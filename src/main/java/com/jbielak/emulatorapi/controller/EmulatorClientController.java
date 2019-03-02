package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.exception.ClientConnectionException;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.validation.IpAddress;
import com.jbielak.emulatorapi.validation.Port;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/emulator_client")
@Validated
public class EmulatorClientController {

    private ClientApi emulatorClient;

    public EmulatorClientController(ClientApi clientApi) {
        this.emulatorClient = clientApi;
    }

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
    ResponseEntity<LightweightSocket> connect(@PathVariable @NotNull @IpAddress String address,
                                              @PathVariable @NotNull @Port Integer port)
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
