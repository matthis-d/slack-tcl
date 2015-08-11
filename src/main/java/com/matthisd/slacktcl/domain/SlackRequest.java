package com.matthisd.slacktcl.domain;

import com.matthisd.slacktcl.enums.RequestTypeEnum;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class SlackRequest {

    @Id
    private String id;

    private String stationName;

    private String busNumber;

    private String direction;

    private RequestTypeEnum type;

    private String listName;

    private Date createdAt;

    private Date updatedAt;

    public SlackRequest() {
        super();
        this.type = RequestTypeEnum.TIME_STATION_BUS;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public SlackRequest(String stationName) {
        this();
        this.stationName = stationName;
    }

    public SlackRequest(String stationName, String busNumber) {
        this();
        this.stationName = stationName;
        this.busNumber = busNumber;
    }

    public SlackRequest(String stationName, String busNumber, String direction) {
        this();
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.direction = direction;
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
        this.updateMade();
        this.busNumber = busNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.updateMade();
        this.stationName = stationName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.updateMade();
        this.direction = direction;
    }

    public RequestTypeEnum getType() {
        return type;
    }

    public void setType(RequestTypeEnum type) {
        this.updateMade();
        this.type = type;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.updateMade();
        this.listName = listName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    public void updateMade() {
        this.setUpdatedAt(new Date());
    }
}
