package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.option.HealthStateOption;
import com.jbielak.emulatorapi.cmd.option.PowerStatusOption;

public interface  PowerStateService {

    String displayPowerState();
    String setPowerAc(boolean enabled);
    String setPowerStatus(PowerStatusOption powerStatusOption);
    String setPowerPresentState(boolean present);
    String setPowerHealthState(HealthStateOption healthStateOption);
    String setPowerCapacity(int percent);

}
