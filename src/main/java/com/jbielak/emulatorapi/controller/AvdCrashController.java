package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.service.CrashService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/crash")
public class AvdCrashController {

    private CrashService crashService;

    public AvdCrashController(CrashService crashService) {
        this.crashService = crashService;
    }


    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> crashEmulator() {
        String response = crashService.crash();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/on_exit", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> crashEmulatorOnExit() {
        String response = crashService.crashOnExit();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
