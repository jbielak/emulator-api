package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/api/v1/auth")
public class AvdAuthController {

    private AuthService avdAuthService;

    public AvdAuthController(AuthService authService) {
        avdAuthService = authService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Boolean> auth(@QueryParam("authToken") String authToken) {
        Boolean authResult;
        if (authToken != null && !authToken.isEmpty()) {
            authResult = avdAuthService.auth(authToken);
        } else {
            authResult = avdAuthService.auth();
        }
        return new ResponseEntity<>(authResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<Boolean> getAuthStatus() {
        Boolean authStatus = avdAuthService.isAuthorized();

        return new ResponseEntity<>(authStatus, HttpStatus.OK);
    }

}
