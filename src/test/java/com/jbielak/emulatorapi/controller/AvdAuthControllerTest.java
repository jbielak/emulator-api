package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.service.AuthService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvdAuthControllerTest {

    @Mock
    private AuthService mockAuthService;

    @InjectMocks
    private AvdAuthController avdAuthController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(avdAuthController)
                .build();
    }

    @Test
    @DisplayName("Given available client when token is provided in configuration should authenticate")
    public void testAuth() throws Exception {
        when(mockAuthService.auth())
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/auth")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(mockAuthService).auth();
        verify(mockAuthService, times(1)).auth();
    }

    @Test
    @DisplayName("Given available client when correct token is passed in query param should authenticate")
    public void testAuthWithQueryParamToken() throws Exception {
        when(mockAuthService.auth("token"))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/auth?authToken=token")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(mockAuthService).auth("token");
        verify(mockAuthService, times(1)).auth("token");
    }

    @Test
    @DisplayName("Given available client when token is not provided in config should not authenticate")
    public void testAuthWithNoTokenProvidedInConfig() throws Exception {
        when(mockAuthService.auth())
                .thenReturn(false);

        mockMvc.perform(post("/api/v1/auth")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(mockAuthService).auth();
        verify(mockAuthService, times(1)).auth();
    }

    @Test
    @DisplayName("Given available client when wrong token is passed in query param should not authenticate")
    public void testAuthWithQueryParamWrongToken() throws Exception {
        when(mockAuthService.auth("token"))
                .thenReturn(false);

        mockMvc.perform(post("/api/v1/auth?authToken=token")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(mockAuthService).auth("token");
        verify(mockAuthService, times(1)).auth("token");
    }

    @Test
    @DisplayName("Given available client when access to AVD is authorized should return true")
    public void testAuthStatusAuthorized() throws Exception {
        when(mockAuthService.isAuthorized())
                .thenReturn(true);

        mockMvc.perform(get("/api/v1/auth/status")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(mockAuthService).isAuthorized();
        verify(mockAuthService, times(1)).isAuthorized();
    }


    @Test
    @DisplayName("Given available client when access to AVD is not authorized should return false")
    public void testAuthStatusNotAuthorized() throws Exception {
        when(mockAuthService.isAuthorized())
                .thenReturn(false);

        mockMvc.perform(get("/api/v1/auth/status")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));

        verify(mockAuthService).isAuthorized();
        verify(mockAuthService, times(1)).isAuthorized();
    }

}
