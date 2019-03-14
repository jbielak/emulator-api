package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.service.CrashService;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvdCrashControllerTest {

    @Mock
    private CrashService mockCrashService;

    @InjectMocks
    private AvdCrashController avdCrashController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(avdCrashController)
                .build();
    }

    @Test
    @DisplayName("Given available client when crash is invoked correctly" +
            " then return empty string")
    public void testCrash() throws Exception {
        when(mockCrashService.crash())
                .thenReturn("");

        mockMvc.perform(post("/api/v1/crash")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(mockCrashService).crash();
        verify(mockCrashService, times(1)).crash();
    }

    @Test
    @DisplayName("Given available client when crash on exit is invoked correctly" +
            " then return empty string")
    public void testCrashOnExit() throws Exception {
        when(mockCrashService.crashOnExit())
                .thenReturn("");

        mockMvc.perform(post("/api/v1/crash/on_exit")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

        verify(mockCrashService).crashOnExit();
        verify(mockCrashService, times(1)).crashOnExit();
    }
}
