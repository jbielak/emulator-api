package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.SentenceType;
import com.jbielak.emulatorapi.service.GeoLocationService;
import com.jbielak.emulatorapi.validation.ValidationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/geo_location")
public class AvdGeoLocationController {

    private GeoLocationService avdGeoLocationService;

    public AvdGeoLocationController(GeoLocationService avdGeoLocationService) {
        this.avdGeoLocationService = avdGeoLocationService;
    }

    @RequestMapping(value = "/fix/{longitude}/{latitude}",
            method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setGeoLocation(@PathVariable Double longitude,
                                          @PathVariable Double latitude) {

        if (!ValidationUtils.isValidLongitude(longitude)) {
            return new ResponseEntity<>(
                    "Invalid longitude value. Allowed values in range: -180.0 - 180.0.",
                    HttpStatus.BAD_REQUEST);
        }
        if (!ValidationUtils.isValidLatitude(latitude)) {
            return new ResponseEntity<>(
                    "Invalid latitude value. Allowed values in range: -90.0 - 90.0.",
                    HttpStatus.BAD_REQUEST);
        }

        String  response = avdGeoLocationService.setGeoFixLongLat(longitude, latitude);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/nmea/{sentence_type}/{sentence}",
            method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> setNmeaGeoLocation(
            @PathVariable("sentence_type") String sentenceTypeValue,
            @PathVariable("sentence") String sentence) {
        Optional<SentenceType> sentenceType = SentenceType.fromValue(sentenceTypeValue);

        if (sentenceType.isPresent()) {
            String response = avdGeoLocationService.setGeoNmea(sentenceType.get(), sentence);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid sentence type value. Allowed values: "
                        + SentenceType.getValues(), HttpStatus.BAD_REQUEST);
        }

    }

}
