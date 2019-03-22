package com.jbielak.emulatorapi.service;

import com.jbielak.emulatorapi.cmd.option.SentenceType;

public interface GeoLocationService {

    String setGeoFixLongLat(double longitude, double latitude);

    String setGeoNmea(SentenceType sentenceType, String sentence);
}
