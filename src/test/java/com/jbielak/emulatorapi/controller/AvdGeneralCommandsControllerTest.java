package com.jbielak.emulatorapi.controller;

import com.jbielak.emulatorapi.cmd.option.AvdOption;
import com.jbielak.emulatorapi.cmd.option.AvdSnapshotOption;
import com.jbielak.emulatorapi.cmd.response.GeneralCommandResponse;
import com.jbielak.emulatorapi.service.AvdGeneralCommandsService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AvdGeneralCommandsControllerTest {

    @Mock
    private AvdGeneralCommandsService mockAvdGeneralCommandsService;

    @InjectMocks
    private AvdGeneralCommandsController avdGeneralCommandsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(avdGeneralCommandsController)
                .build();
    }

    @Test
    @DisplayName("Given available client when avd rotate invoked then return true")
    public void testRotate() throws Exception {
        mockMvc.perform(post("/api/v1/general_commands/rotate")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(mockAvdGeneralCommandsService).rotate();
        verify(mockAvdGeneralCommandsService, times(1)).rotate();
    }

    @Test
    @DisplayName("Given available client when avd status method is invoked then return AVD status")
    public void testAvdStatusAvailable() throws Exception {
        when(mockAvdGeneralCommandsService.avd(AvdOption.STATUS))
                .thenReturn(GeneralCommandResponse.AVD_STATUS_RUNNING.getValue());

        mockMvc.perform(post("/api/v1/general_commands/avd/{option}/",
                AvdOption.STATUS.getAvdOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_STATUS_RUNNING.getValue()));

        verify(mockAvdGeneralCommandsService).avd(AvdOption.STATUS);
        verify(mockAvdGeneralCommandsService, times(1)).avd(AvdOption.STATUS);
    }

    @Test
    @DisplayName("Given unavailable client when avd status method is invoked then return empty messagae")
    public void testAvdStatusUnavailable() throws Exception {
        String statusUnavailable = "";
        when(mockAvdGeneralCommandsService.avd(AvdOption.STATUS))
                .thenReturn(statusUnavailable);

        mockMvc.perform(post("/api/v1/general_commands/avd/{option}/",
                AvdOption.STATUS.getAvdOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(statusUnavailable));

        verify(mockAvdGeneralCommandsService).avd(AvdOption.STATUS);
        verify(mockAvdGeneralCommandsService, times(1)).avd(AvdOption.STATUS);
    }

    @Test
    @DisplayName("Given available client when avd method with unknown option is invoked then return 404")
    public void testAvdBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/general_commands/avd/{action}/",
                "unknown_option")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid action value. Allowed values: "
                        + AvdOption.getValues()));

        verify(mockAvdGeneralCommandsService, times(0)).avd(AvdOption.STATUS);
    }

    @Test
    @DisplayName("Given available client when snapshot list method is invoked then return list")
    public void testAvdSnapshotList() throws Exception {
        String listStr = "list";
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.LIST))
                .thenReturn(listStr);

        mockMvc.perform(get("/api/v1/general_commands/avd_snapshot/"
                + AvdSnapshotOption.LIST.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(listStr));

        verify(mockAvdGeneralCommandsService).avdSnapshot(AvdSnapshotOption.LIST);
        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.LIST);
    }

    @Test
    @DisplayName("Given available client when snapshot list method with name query param "
            + "is invoked should ignore name param then return list")
    public void testAvdSnapshotListWithNameQueryParam() throws Exception {
        String listStr = "list";
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.LIST))
                .thenReturn(listStr);

        mockMvc.perform(get("/api/v1/general_commands/avd_snapshot/" +
                        AvdSnapshotOption.LIST.getAvdSnapshotOptionValue() + "/?name=test_name",
                AvdSnapshotOption.LIST.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(listStr));

        verify(mockAvdGeneralCommandsService).avdSnapshot(AvdSnapshotOption.LIST);
        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.LIST);
    }

    @Test
    @DisplayName("Given available client when snapshot save method is invoked with name param "
            + "then save snapshot")
    public void testAvdSnapshotSave() throws Exception {
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.SAVE, "test_name"))
                .thenReturn(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue());

        mockMvc.perform(post("/api/v1/general_commands/avd_snapshot/?name=test_name",
                AvdSnapshotOption.SAVE.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue()));

        verify(mockAvdGeneralCommandsService).avdSnapshot(AvdSnapshotOption.SAVE, "test_name");
        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.SAVE, "test_name");
    }

    @Test
    @DisplayName("Given available client when snapshot load method is invoked with name of the " +
            "saved snapshot param then load snapshot")
    public void testAvdSnapshotLoad() throws Exception {
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.LOAD, "test_name"))
                .thenReturn(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue());

        mockMvc.perform(get("/api/v1/general_commands/avd_snapshot/?name=test_name",
                AvdSnapshotOption.LOAD.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue()));

        verify(mockAvdGeneralCommandsService).avdSnapshot(AvdSnapshotOption.LOAD, "test_name");
        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.LOAD, "test_name");
    }

    @Test
    @DisplayName("Given available client when snapshot load method is invoked with name of the "
            + "not saved snapshot param then return message")
    public void testAvdSnapshotLoadNotExistingSnapshot() throws Exception {
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.LOAD, "test_name"))
                .thenReturn(GeneralCommandResponse.AVD_SNAPSHOT_LOAD_FAIL.getValue());

        mockMvc.perform(get("/api/v1/general_commands/avd_snapshot?name=test_name",
                AvdSnapshotOption.LOAD.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_SNAPSHOT_LOAD_FAIL.getValue()));

        verify(mockAvdGeneralCommandsService).avdSnapshot(AvdSnapshotOption.LOAD, "test_name");
        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.LOAD, "test_name");
    }

    @Test
    @DisplayName("Given available client when snapshot method that requires name query param" +
            " is invoked without name param then return 404")
    public void testAvdSnapshotWithoutNameQueryParam() throws Exception {
        mockMvc.perform(post("/api/v1/general_commands/avd_snapshot/?name=",
                AvdSnapshotOption.SAVE.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Snapshot name is required for action: "
                        +  AvdSnapshotOption.SAVE.getAvdSnapshotOptionValue()));

        verify(mockAvdGeneralCommandsService, times(0))
                .avdSnapshot(AvdSnapshotOption.LIST, "");
    }

    @Test
    @DisplayName("Given available client when snapshot save POST method is invoked"
            + " with correct name query param then return OK")
    public void testAvdSnapshotSaveWithCorrectNameQueryParam() throws Exception {
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.SAVE, "test_name"))
                .thenReturn(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue());

        mockMvc.perform(post("/api/v1/general_commands/avd_snapshot/?name=test_name",
                AvdSnapshotOption.SAVE.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue()));

        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.SAVE, "test_name");
    }

    @Test
    @DisplayName("Given available client when snapshot delete DELETE method is invoked"
            + " with correct name query param then return OK")
    public void testAvdSnapshotDeleteWithCorrectNameQueryParam() throws Exception {
        when(mockAvdGeneralCommandsService.avdSnapshot(AvdSnapshotOption.DELETE, "test_name"))
                .thenReturn(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue());

        mockMvc.perform(delete("/api/v1/general_commands/avd_snapshot/?name=test_name",
                AvdSnapshotOption.DELETE.getAvdSnapshotOptionValue())
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.AVD_SNAPSHOT_OK.getValue()));

        verify(mockAvdGeneralCommandsService, times(1))
                .avdSnapshot(AvdSnapshotOption.DELETE, "test_name");
    }

    @Test
    @DisplayName("Given available client when ping method is invoked then return message")
    public void testAvdSnapshotPingAvailable() throws Exception {
        when(mockAvdGeneralCommandsService.ping())
                .thenReturn(GeneralCommandResponse.PING_ALIVE.getValue());

        mockMvc.perform(get("/api/v1/general_commands/ping")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.PING_ALIVE.getValue()));

        verify(mockAvdGeneralCommandsService).ping();
        verify(mockAvdGeneralCommandsService, times(1)).ping();
    }

    @Test
    @DisplayName("Given unavailable client when ping method is invoked then return empty message")
    public void testAvdSnapshotPingUnavailable() throws Exception {
        String pingResponse = "";
        when(mockAvdGeneralCommandsService.ping())
                .thenReturn(pingResponse);

        mockMvc.perform(get("/api/v1/general_commands/ping")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(pingResponse));

        verify(mockAvdGeneralCommandsService).ping();
        verify(mockAvdGeneralCommandsService, times(1)).ping();
    }

    @Test
    @DisplayName("Given available client when kill method is invoked then return message")
    public void testAvdSnapshotKillAvailable() throws Exception {
        when(mockAvdGeneralCommandsService.kill())
                .thenReturn(GeneralCommandResponse.KILL_SUCCESS.getValue());

        mockMvc.perform(post("/api/v1/general_commands/kill")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(GeneralCommandResponse.KILL_SUCCESS.getValue()));

        verify(mockAvdGeneralCommandsService).kill();
        verify(mockAvdGeneralCommandsService, times(1)).kill();
    }

    @Test
    @DisplayName("Given unavailable client when kill method is invoked then return empty message")
    public void testAvdSnapshotKillUnavailable() throws Exception {
        String killResponse = "";
        when(mockAvdGeneralCommandsService.kill())
                .thenReturn(killResponse);

        mockMvc.perform(post("/api/v1/general_commands/kill")
                .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(status().isOk())
                .andExpect(content().string(killResponse));

        verify(mockAvdGeneralCommandsService).kill();
        verify(mockAvdGeneralCommandsService, times(1)).kill();
    }
}
