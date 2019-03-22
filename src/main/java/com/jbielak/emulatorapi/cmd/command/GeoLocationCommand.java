package com.jbielak.emulatorapi.cmd.command;

public enum GeoLocationCommand {

    GEO_FIX_LONG_LAT("geo fix %s %s"),
    GEO_NMEA("geo nmea %s");

    private final String geoLocationCommandValue;

    GeoLocationCommand(String geoLocationCommandValue) {
        this.geoLocationCommandValue = geoLocationCommandValue;
    }

    public String getValue() {
        return this.geoLocationCommandValue;
    }
}
