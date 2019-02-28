package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.advice.ClientConnectionExceptionHandler;
import com.jbielak.emulatorapi.dto.LightweightSocket;
import com.jbielak.emulatorapi.socket.ClientApi;
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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmulatorClientControllerTest {

    @Mock
    private ClientApi mockClientApi;

    @InjectMocks
    private EmulatorClientController emulatorClientController;

    private MockMvc mockMvc;

    @Before //not @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(emulatorClientController)
                .setControllerAdvice(new ClientConnectionExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Given available client should return client address and port")
    public void testOpenConnectionWithDefaultConfig() throws Exception {
        when(mockClientApi.openConnection())
                .thenReturn(new LightweightSocket("127.0.0.1", 5554));

        mockMvc.perform(post("/api/v1/emulator_client/connect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("127.0.0.1")))
                .andExpect(jsonPath("$.port", is(5554)))
                .andExpect(jsonPath("$.empty").doesNotExist());

        verify(mockClientApi).openConnection();
        verify(mockClientApi, times(1)).openConnection();
    }

    @Test
    @DisplayName("Given unavailable client should return InternalServerError")
    public void testConnectToUnavailableClient() throws Exception {
        when(mockClientApi.openConnection())
                .thenReturn(new LightweightSocket());

        mockMvc.perform(post("/api/v1/emulator_client/connect")
                .contentType((MediaType.APPLICATION_JSON)))
                //.andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason(containsString(
                        "Could not open/close client connection.")));

        verify(mockClientApi).openConnection();
        verify(mockClientApi, times(1)).openConnection();
    }

    @Test
    @DisplayName("Given available client on address and port specified" +
            "in request path should return client")
    public void testOpenConnectionWithPathConfig() throws Exception {
        String address = "127.0.0.2";
        Integer port = 5555;
        when(mockClientApi.openConnection(address, port))
                .thenReturn(new LightweightSocket(address, port));

        mockMvc.perform(post("/api/v1/emulator_client/connect/{address}/{port}", address, port)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is(address)))
                .andExpect(jsonPath("$.port", is(port)))
                .andExpect(jsonPath("$.empty").doesNotExist());

        verify(mockClientApi).openConnection(address, port);
        verify(mockClientApi, times(1)).openConnection(address, port);
    }


    @Test
    @DisplayName("Given unavailable client on address and port" +
            "specified in request path should return InternalServerError")
    public void testOpenConnectionWithUnavailableClientOnPathConfigParams() throws Exception {
        String address = "127.0.0.2";
        Integer port = 5555;
        when(mockClientApi.openConnection(address, port))
                .thenReturn(new LightweightSocket());

        mockMvc.perform(post("/api/v1/emulator_client/connect/{address}/{port}", address, port)
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason(containsString(
                        "Could not open/close client connection.")));


        verify(mockClientApi).openConnection(address, port);
        verify(mockClientApi, times(1)).openConnection(address, port);
    }

    @Test
    @DisplayName("Given available client should return closed client address and port")
    public void testCloseConnection() throws Exception {
        when(mockClientApi.closeConnection())
                .thenReturn(new LightweightSocket("127.0.0.1", 5554));

        mockMvc.perform(post("/api/v1/emulator_client/disconnect")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("127.0.0.1")))
                .andExpect(jsonPath("$.port", is(5554)))
                .andExpect(jsonPath("$.empty").doesNotExist());

        verify(mockClientApi).closeConnection();
        verify(mockClientApi, times(1)).closeConnection();
    }

    @Test
    @DisplayName("Given unavailable client should return InternalServerError")
    public void testCloseConnectionWithUnavailableClient() throws Exception {
        when(mockClientApi.closeConnection())
                .thenReturn(new LightweightSocket());

        mockMvc.perform(post("/api/v1/emulator_client/disconnect")
                .contentType((MediaType.APPLICATION_JSON)))
                //.andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason(containsString(
                        "Could not open/close client connection.")));

        verify(mockClientApi).closeConnection();
        verify(mockClientApi, times(1)).closeConnection();
    }

}
