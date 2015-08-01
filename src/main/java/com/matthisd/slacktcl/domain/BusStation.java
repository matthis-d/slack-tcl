package com.matthisd.slacktcl.domain;

import com.matthisd.slacktcl.constants.SlackTclConstants;

public class BusStation {

    private String stationName;

    private Integer busNumber;

    public BusStation() {
        this.stationName = SlackTclConstants.DEFAULT_BUS_STOP;
        this.busNumber = SlackTclConstants.DEFAULT_BUS_NUMBER;
    }

    public BusStation(String stationName) {
        this();
        this.stationName = stationName;
    }

    public BusStation(Integer busNumber) {
        this();
        this.busNumber = busNumber;
    }

    public BusStation(String stationName, Integer busNumber) {
        this.stationName = stationName;
        this.busNumber = busNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Integer getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(Integer busNumber) {
        this.busNumber = busNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusStation{");
        sb.append("stationName='").append(stationName).append('\'');
        sb.append(", busNumber=").append(busNumber);
        sb.append('}');
        return sb.toString();
    }
}
