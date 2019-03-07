package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.AvdOption;
import com.jbielak.emulatorapi.cmd.option.AvdSnapshotOption;
import com.jbielak.emulatorapi.service.GeneralCommandsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/api/v1/general_commands")
public class AvdGeneralCommandsController {

    private GeneralCommandsService generalCommandsService;

    public AvdGeneralCommandsController(GeneralCommandsService generalCommandsService) {
        this.generalCommandsService = generalCommandsService;
    }

    @RequestMapping(value = "/rotate", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<Boolean> rotate() {
        generalCommandsService.rotate();

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/avd/{action}", method = RequestMethod.POST)
    public @ResponseBody

    ResponseEntity<String> avd(@PathVariable("action") String action) {
        AvdOption avdOption = AvdOption.forValue(action);
        if (avdOption == null) {
            return new ResponseEntity<>("Invalid action value. Allowed values: "
                    + AvdOption.getValues(), HttpStatus.BAD_REQUEST);
        }
        String response = generalCommandsService.avd(avdOption);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/avd_snapshot/list", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> avdSnapshotList() {

        String response = generalCommandsService.avdSnapshot(AvdSnapshotOption.LIST);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/avd_snapshot", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> avdSnapshotLoad(@QueryParam("name") @NotNull String name) {

        if (name == null || name.isEmpty()) {
            return new ResponseEntity<>(String.format("Snapshot name is required for action: %s",
                    AvdSnapshotOption.LOAD.getAvdSnapshotOptionValue()), HttpStatus.BAD_REQUEST);
        }
        String response = generalCommandsService.avdSnapshot(AvdSnapshotOption.LOAD, name);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/avd_snapshot", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> avdSnapshotSave(@QueryParam("name") String name) {

        if (name == null || name.isEmpty()) {
                    return new ResponseEntity<>(String.format("Snapshot name is required for action: %s",
                            AvdSnapshotOption.SAVE.getAvdSnapshotOptionValue()), HttpStatus.BAD_REQUEST);
        }
        String response = generalCommandsService.avdSnapshot(AvdSnapshotOption.SAVE, name);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/avd_snapshot", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseEntity<String> avdSnapshot(@QueryParam("name") String name) {

          if (name == null || name.isEmpty()) {
                    return new ResponseEntity<>(String.format("Snapshot name is required for action: %s",
                            AvdSnapshotOption.DELETE.getAvdSnapshotOptionValue()), HttpStatus.BAD_REQUEST);
          }
          String response = generalCommandsService.avdSnapshot(AvdSnapshotOption.DELETE, name);

          return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<String> ping() {
        String response = generalCommandsService.ping();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/kill", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> kill() {
        String response = generalCommandsService.kill();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
