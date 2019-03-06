package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.option.AvdOption;
import com.jbielak.emulatorapi.cmd.option.AvdSnapshotOption;

public interface GeneralCommandsService {

    void rotate();
    String avd(AvdOption avdOption);
    String avdSnapshot(AvdSnapshotOption avdSnapshotOption);
    String avdSnapshot(AvdSnapshotOption avdSnapshotOption, String name);
    String ping();
    String kill();
}
