package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.socket.ClientApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/emulator_client")
public class EmulatorClientController {

    @Autowired
    private ClientApi emulatorClient;

    @RequestMapping(value = "/connect", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> connect() {
        LightweightSocket lightweightSocket = emulatorClient.openConnection();
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

    @RequestMapping(value = "/connect/{address}/{port}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> connect(@PathVariable String address, @PathVariable Integer port) {
        LightweightSocket lightweightSocket = emulatorClient.openConnection(address, port);
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<LightweightSocket> close() {
        LightweightSocket lightweightSocket = emulatorClient.closeConnection();
        return new ResponseEntity<>(lightweightSocket, HttpStatus.OK);
    }

}
