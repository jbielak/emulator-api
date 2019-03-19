package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.HealthStateOption;
import com.jbielak.emulatorapi.cmd.option.PowerStatusOption;
import com.jbielak.emulatorapi.service.PowerStateService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvdPowerStateControllerTest {

    @Mock
    private PowerStateService mockPowerStateService;

    @InjectMocks
    private AvdPowerStateController avdPowerStateController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(avdPowerStateController)
                .build();
    }

    @Test
    @DisplayName("Given available client when the display power state is invoked "
            + " then return string with state message")
    public void testDisplayPowerState() throws Exception {
        String response = "AC: online status: Charging health: Good present: true capacity: 100 OK";
        when(mockPowerStateService.displayPowerState())
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/display_state")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).displayPowerState();
        verify(mockPowerStateService, times(1)).displayPowerState();
    }

    @Test
    @DisplayName("Given available client when the set ac charging state is invoked "
            + " with correct path param value then return invoke confirming message")
    public void testSetAc() throws Exception {
        String response = "OK";

        when(mockPowerStateService.setPowerAc(true))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/ac/{enabled}", true)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).setPowerAc(true);
        verify(mockPowerStateService, times(1)).setPowerAc(anyBoolean());
    }

    @Test
    @DisplayName("Given available client when the set ac charging state is invoked "
            + " with incorrect path param value then return 404 Bad Request")
    public void testSetAcIncorrectPathParam() throws Exception {

        mockMvc.perform(post("/api/v1/power/ac/{enabled}", "incorrect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest());

        verify(mockPowerStateService, times(0)).setPowerAc(true);
    }

    @Test
    @DisplayName("Given available client when the set power status state is invoked "
            + " with correct path param value then return invoke confirming message")
    public void testSetPowerStatus() throws Exception {
        String response = "OK";

        when(mockPowerStateService.setPowerStatus(PowerStatusOption.CHARGING))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/status/{power_status}", "charging")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).setPowerStatus(PowerStatusOption.CHARGING);
        verify(mockPowerStateService, times(1))
                .setPowerStatus(PowerStatusOption.CHARGING);
    }

    @Test
    @DisplayName("Given available client when the set power state is invoked "
            + " with incorrect path param value then return 404 Bad Request")
    public void testSetPowerStateIncorrectPathParam() throws Exception {

        mockMvc.perform(post("/api/v1/power/status/{power_status}", "incorrect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid power status value. Allowed values: "
                        +  PowerStatusOption.getValues()));

        verify(mockPowerStateService, times(0))
                .setPowerStatus(PowerStatusOption.CHARGING);
    }

    @Test
    @DisplayName("Given available client when the set power present state is invoked "
            + " with correct path param value then return invoke confirming message")
    public void testSetPowerPresentState() throws Exception {
        String response = "OK";

        when(mockPowerStateService.setPowerPresentState(true))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/present_state/{enabled}", true)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).setPowerPresentState(anyBoolean());
        verify(mockPowerStateService, times(1)).setPowerPresentState(anyBoolean());
    }

    @Test
    @DisplayName("Given available client when the set power present state is invoked "
            + " with incorrect path param value then return 404 Bad Request")
    public void testSetPowerPresentStateIncorrectPathParam() throws Exception {

        mockMvc.perform(post("/api/v1/power/present_state/{enabled}", "incorrect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest());

        verify(mockPowerStateService, times(0)).setPowerPresentState(anyBoolean());
    }

    @Test
    @DisplayName("Given available client when the set health power status state is invoked "
            + " with correct path param value then return invoke confirming message")
    public void testSetPowerHealthState() throws Exception {
        String response = "OK";

        when(mockPowerStateService.setPowerHealthState(any()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/health_state/{health_state_value}", "good")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).setPowerHealthState(any());
        verify(mockPowerStateService, times(1))
                .setPowerHealthState(any());
    }

    @Test
    @DisplayName("Given available client when the set power health state is invoked "
            + " with incorrect path param value then return 404 Bad Request")
    public void testSetPowerHealthStateIncorrectPathParam() throws Exception {

        mockMvc.perform(post("/api/v1/power/health_state/{health_state_value}", "incorrect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid health state value. Allowed values: "
                        +  HealthStateOption.getValues()));

        verify(mockPowerStateService, times(0))
                .setPowerHealthState(any());
    }

    @Test
    @DisplayName("Given available client when the set health power capacity is invoked "
            + " with correct path param value then return invoke confirming message")
    public void testSetPowerCapacity() throws Exception {
        String response = "OK";

        when(mockPowerStateService.setPowerCapacity(anyInt()))
                .thenReturn(response);

        mockMvc.perform(post("/api/v1/power/capacity/{percent}", 50)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(response));

        verify(mockPowerStateService).setPowerCapacity(anyInt());
        verify(mockPowerStateService, times(1))
                .setPowerCapacity(anyInt());
    }

    @Test
    @DisplayName("Given available client when the set power capacity is invoked "
            + " with incorrect path param value then return 404 Bad Request")
    public void testSetPowerCapacityIncorrectPathParam() throws Exception {

        mockMvc.perform(post("/api/v1/power/capacity/{percent}", 200)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid capacity value."
                        + "Allowed values from 0 to 100"));

        verify(mockPowerStateService, times(0))
                .setPowerCapacity(anyInt());
    }
}
