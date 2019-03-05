package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.response.AuthResponse;
import com.jbielak.emulatorapi.cmd.response.Response;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.utils.CmdResponseReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AvdAuthService implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvdAuthService.class);

    private static final String AUTH_COMMAND = "auth %s\n";

    @Value("${avd.auth.token}")
    private String AVD_AUTH_TOKEN;

    private ClientApi clientApi;
    private boolean isAuthorized;

    public AvdAuthService(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    @Override
    public boolean auth() {
        if (AVD_AUTH_TOKEN == null || AVD_AUTH_TOKEN.isEmpty()) {
            throw new IllegalArgumentException("AVD authentication token not provided.");
        }
        return auth(AVD_AUTH_TOKEN);
    }

    @Override
    public boolean auth(String authToken) {
        if (isAuthorized) {
            LOGGER.info("AVD authentication already provided.");
            return true;
        }
        LOGGER.info("AVD authentication started.");

        clientApi.getPrintWriter().println(String.format(AUTH_COMMAND, authToken));

        String response = CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
        isAuthorized = response.contains(Response.OK.getGeneralResponseValue());

        if (response.contains(AuthResponse.AUTH_TOKEN_NOT_MATCH.getAuthResValue())) {
            throw new IllegalArgumentException("Authentication token does" +
                    " not match ~/.emulator_console_auth_token");
        }

        if (response.contains(Response.UNKNOWN_COMMAND.getGeneralResponseValue())
                && !isAuthorized) {
            clientApi.getPrintWriter().println(String.format(AUTH_COMMAND, authToken));
            response = CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
        }

        isAuthorized = response.contains(Response.OK.getGeneralResponseValue());
        return isAuthorized;
    }

    @Override
    public boolean isAuthorized() {
        return isAuthorized;
    }
}
