package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.matthisd.slacktcl.constants.SlackTclConstants;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStation {

    @JsonProperty(SlackTclConstants.JSON_STATION_ID)
    private String id;

    @JsonProperty(SlackTclConstants.JSON_STATION_NAME)
    private String stationName;

    @JsonProperty(SlackTclConstants.JSON_STATION_SERVICE)
    private String service;

    public BusStation() {
    }

    public BusStation(String id, String stationName, String service) {
        this.id = id;
        this.stationName = stationName;
        this.service = service;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    /**
     * Indicates if the bus number does a stop at this station.
     * @param busNumber The bus number to test.
     * @return true if the bus is stopping here, false otherwise.
     */
    public Boolean isServing(String busNumber) {

        return StringUtils.containsIgnoreCase(this.service, busNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BusStation{");
        sb.append("id='").append(id).append('\'');
        sb.append(", stationName='").append(stationName).append('\'');
        sb.append(", service='").append(service).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
