package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.command.GeoLocationCommand;
import com.jbielak.emulatorapi.cmd.option.SentenceType;
import com.jbielak.emulatorapi.socket.ClientApi;
import com.jbielak.emulatorapi.utils.CmdResponseReader;
import org.springframework.stereotype.Service;

@Service
public class AvdGeoLocationService implements GeoLocationService {

    private ClientApi clientApi;

    public AvdGeoLocationService(ClientApi clientApi) {
        this.clientApi = clientApi;
    }

    @Override
    public String setGeoFixLongLat(double longitude, double latitude) {
        clientApi.getPrintWriter().println(
                String.format(GeoLocationCommand.GEO_FIX_LONG_LAT.getValue(), longitude, latitude));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }

    @Override
    public String setGeoNmea(SentenceType sentenceType, String sentence) {
        String arg = sentenceType.getSentenceTypeValue() + "," + sentence;
        clientApi.getPrintWriter().println(
                String.format(GeoLocationCommand.GEO_NMEA.getValue(), arg));

        return CmdResponseReader.readResponse(clientApi.getBufferedReader(), true);
    }
}
