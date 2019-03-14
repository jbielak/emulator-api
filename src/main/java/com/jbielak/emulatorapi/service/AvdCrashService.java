package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.command.CrashCommand;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.utils.CmdResponseReader;
import org.springframework.stereotype.Service;

@Service
public class AvdCrashService implements CrashService {

    private ClientApi clientApi;

    public AvdCrashService(ClientApi clientApi) {
        this. clientApi = clientApi;
    }

    @Override
    public String crash() {
        clientApi.getPrintWriter().println(CrashCommand.CRASH.getValue());

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String crashOnExit() {
        clientApi.getPrintWriter().println(CrashCommand.CRASH_ON_EXIT.getValue());

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }
}
