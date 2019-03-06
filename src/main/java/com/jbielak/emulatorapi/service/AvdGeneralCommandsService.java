package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.command.GeneralCommand;
import com.jbielak.emulatorapi.cmd.option.AvdOption;
import com.jbielak.emulatorapi.cmd.option.AvdSnapshotOption;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.utils.CmdResponseReader;
import org.springframework.stereotype.Service;

@Service
public class AvdGeneralCommandsService implements GeneralCommandsService {

    private static final String EMPTY_STRING = "";

    private ClientApi clientApi;

    public AvdGeneralCommandsService(ClientApi clientApi) {
        this. clientApi = clientApi;
    }

    @Override
    public void  rotate() {
        CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
        clientApi.getPrintWriter().println(GeneralCommand.ROTATE.getValue());
    }

    @Override
    public String avd(AvdOption avdOption) {
        clientApi.getPrintWriter().println(String.format(GeneralCommand.AVD.getValue(),
                avdOption.getAvdOptionValue()));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String avdSnapshot(AvdSnapshotOption avdSnapshotOption) {
        return avdSnapshot(avdSnapshotOption, EMPTY_STRING);
    }

    @Override
    public String avdSnapshot(AvdSnapshotOption avdSnapshotOption, String name) {
        clientApi.getPrintWriter().println(String.format(GeneralCommand.AVD_SNAPSHOT.getValue(),
                avdSnapshotOption.getAvdSnapshotOptionValue(), name));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(),
                false, 4000);
    }

    @Override
    public String ping() {
        clientApi.getPrintWriter().println(GeneralCommand.PING.getValue());

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String kill() {
        clientApi.getPrintWriter().println(GeneralCommand.KILL.getValue());

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }
}
