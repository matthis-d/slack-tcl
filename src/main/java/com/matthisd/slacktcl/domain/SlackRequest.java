package com.matthisd.slacktcl.domain;

import com.matthisd.slacktcl.enums.RequestTypeEnum;
import org.springframework.data.annotation.Id;

public class SlackRequest {

    @Id
    private String id;

    private String stationName;

    private String busNumber;

    private String direction;

    private RequestTypeEnum type;

    private String listName;

    public SlackRequest() {
    }

    public SlackRequest(String stationName) {
        this.stationName = stationName;
        this.type = RequestTypeEnum.TIME_STATION_BUS;
    }

    public SlackRequest(String stationName, String busNumber) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.type = RequestTypeEnum.TIME_STATION_BUS;
    }

    public SlackRequest(String stationName, String busNumber, String direction) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.direction = direction;
        this.type = RequestTypeEnum.TIME_STATION_BUS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public RequestTypeEnum getType() {
        return type;
    }

    public void setType(RequestTypeEnum type) {
        this.type = type;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SlackRequest{");
        sb.append("listName='").append(listName).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", busNumber='").append(busNumber).append('\'');
        sb.append(", direction='").append(direction).append('\'');
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
