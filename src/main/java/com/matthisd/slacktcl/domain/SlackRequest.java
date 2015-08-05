package com.matthisd.slacktcl.domain;

public class SlackRequest {

    private String stationName;

    private String busNumber;

    private String direction;

    public SlackRequest() {
    }

    public SlackRequest(String stationName, String busNumber, String direction) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.direction = direction;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlackRequest{");
        sb.append("stationName='").append(stationName).append('\'');
        sb.append(", busNumber='").append(busNumber).append('\'');
        sb.append(", direction='").append(direction).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
