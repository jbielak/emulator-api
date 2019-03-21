package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.HealthStateOption;
import com.jbielak.emulatorapi.cmd.option.PowerStatusOption;
import com.jbielak.emulatorapi.service.PowerStateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/power")
public class AvdPowerStateController {

    private PowerStateService powerStateService;

    public AvdPowerStateController(PowerStateService powerStateService) {
        this.powerStateService = powerStateService;
    }

    @RequestMapping(value = "/display_state", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> displayPowerState() {
        String response = powerStateService.displayPowerState();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/ac/{enabled}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setPowerAc(@PathVariable("enabled") @NotNull boolean enabled) {
        String response = powerStateService.setPowerAc(enabled);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/{power_status}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setPowerStatus(
            @PathVariable("power_status") @NotNull String powerStatus) {
        Optional<PowerStatusOption> powerStatusOption = PowerStatusOption.fromValue(powerStatus);

        if (powerStatusOption.isPresent()) {
            String response = powerStateService.setPowerStatus(powerStatusOption.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid power status value. Allowed values: "
                    + PowerStatusOption.getValues(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/present_state/{present_state_value}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setPowerPresentState(
            @PathVariable("present_state_value") @NotNull boolean presentState) {
        String response = powerStateService.setPowerPresentState(presentState);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/health_state/{health_state_value}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setPowerHealthState(
            @PathVariable("health_state_value") @NotNull String healthState) {
        Optional<HealthStateOption> healthStateOption = HealthStateOption.fromValue(healthState);

        if (healthStateOption.isPresent()) {
            String response = powerStateService.setPowerHealthState(healthStateOption.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid health state value. Allowed values: "
                    + HealthStateOption.getValues(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/capacity/{percent}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setPowerCapacity(
            @PathVariable("percent") @NotNull int capacity) {

        if (capacity < 0 || capacity > 100) {
            return new ResponseEntity<>("Invalid capacity value." +
                    "Allowed values from 0 to 100", HttpStatus.BAD_REQUEST);
        }
        String response = powerStateService.setPowerCapacity(capacity);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
