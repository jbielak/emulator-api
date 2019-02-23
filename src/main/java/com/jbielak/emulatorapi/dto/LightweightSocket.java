package com.jbielak.emulatorapi.dto;

public class LightweightSocket {

    private String address;
    private Integer port;

    public LightweightSocket() {
    }

    public LightweightSocket(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
