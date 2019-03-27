package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.SentenceType;
import com.jbielak.emulatorapi.service.AvdGeoLocationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvdGeoLocationControllerTest {

    @Mock
    private AvdGeoLocationService mockAvdGeoLocationService;

    @InjectMocks
    private AvdGeoLocationController avdGeoLocationController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(avdGeoLocationController)
                .build();
    }

    @Test
    @DisplayName("Given available client when the set geo location is invoked "
            + " with correct longitude and latitude path params then return confirming message")
    public void testSetGeoLocation() throws Exception {
        String response = "OK";

        when(mockAvdGeoLocationService.setGeoFixLongLat(anyDouble(), anyDouble()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/geo_location/fix/{longitude}/{latitude}",
                -123.0840, 37.4220)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockAvdGeoLocationService).setGeoFixLongLat(anyDouble(), anyDouble());
        verify(mockAvdGeoLocationService, times(1))
                .setGeoFixLongLat(anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("Given available client when the set geo location is invoked "
            + " with invalid longitude or latitude value type in path params then"
            + "return bad request")
    public void testSetGeoLocationWithInvalidTypeValues() throws Exception {

        mockMvc.perform(post("/api/v1/geo_location/fix/{longitude}/{latitude}",
                "invalid", 37.4220)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest());

        verify(mockAvdGeoLocationService, times(0))
                .setGeoFixLongLat(anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("Given available client when the set geo location is invoked "
            + " with longitude value out of range in path params then"
            + "return bad request")
    public void testSetGeoLocationWithLongitudeValueOutOfRange() throws Exception {

        mockMvc.perform(post("/api/v1/geo_location/fix/{longitude}/{latitude}",
                300.0, 37.4220)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest());

        verify(mockAvdGeoLocationService, times(0))
                .setGeoFixLongLat(anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("Given available client when the set geo location is invoked "
            + " with latitude value out of range in path params then"
            + "return bad request")
    public void testSetGeoLocationWithLatitudeValueOutOfRange() throws Exception {

        mockMvc.perform(post("/api/v1/geo_location/fix/{longitude}/{latitude}",
                120.0, 97.4220)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest());

        verify(mockAvdGeoLocationService, times(0))
                .setGeoFixLongLat(anyDouble(), anyDouble());
    }

    @Test
    @DisplayName("Given available client when the set nmea is invoked "
            + " with correct path params then return confirming message")
    public void testSetNmea() throws Exception {
        String response = "OK";
        String sentence = ",071236,A,3751.65,S,14527.36,E,000.0,073.0,130309,011.3,E*62";

        when(mockAvdGeoLocationService.setGeoNmea(any(), anyString()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/geo_location/nmea/{sentence_type}/{sentence}",
                "$GPRMC", sentence)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockAvdGeoLocationService).setGeoNmea(any(), anyString());
        verify(mockAvdGeoLocationService, times(1))
                .setGeoNmea(any(), anyString());
    }

    @Test
    @DisplayName("Given available client when the set nmea is invoked "
            + " with invalid path params then return bad request")
    public void testSetNmeaWithInvalidValues() throws Exception {
        String sentence = ",071236,A,3751.65,S,14527.36,E,000.0,073.0,130309,011.3,E*62";

        mockMvc.perform(post("/api/v1/geo_location/nmea/{sentence_type}/{sentence}",
                "$G", sentence)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(
                        "Invalid sentence type value. Allowed values: "
                        + SentenceType.getValues()));

        verify(mockAvdGeoLocationService, times(0))
                .setGeoNmea(any(), anyString());
    }
}
