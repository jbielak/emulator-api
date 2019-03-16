package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.command.PowerStateCommand;
import com.jbielak.emulatorapi.cmd.option.HealthStateOption;
import com.jbielak.emulatorapi.cmd.option.PowerStatusOption;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.utils.CmdResponseReader;
import org.springframework.stereotype.Service;

@Service
public class AvdPowerStateService implements PowerStateService {


    private ClientApi clientApi;

    public AvdPowerStateService(ClientApi clientApi) {
        this. clientApi = clientApi;
    }

    @Override
    public String displayPowerState() {
        clientApi.getPrintWriter().println(PowerStateCommand.POWER_DISPLAY.getValue());

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setPowerAc(boolean enabled) {
        String state = enabled ? "on" : "off";
        clientApi.getPrintWriter().println(
                String.format(PowerStateCommand.POWER_AC.getValue(), state));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setPowerStatus(PowerStatusOption powerStatusOption) {
        clientApi.getPrintWriter().println(
                String.format(PowerStateCommand.POWER_STATUS.getValue(),
                        powerStatusOption.getAvdOptionValue()));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setPowerPresentState(boolean present) {
        clientApi.getPrintWriter().println(
                String.format(PowerStateCommand.POWER_PRESENT.getValue(), present));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setPowerHealthState(HealthStateOption healthStateOption) {
        clientApi.getPrintWriter().println(
                String.format(PowerStateCommand.POWER_HEALTH.getValue(),
                        healthStateOption.getHealthStateOptionValue()));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setPowerCapacity(int percent) {
        clientApi.getPrintWriter().println(
                String.format(PowerStateCommand.POWER_POWER_CAPACITY.getValue(), percent));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }
}
