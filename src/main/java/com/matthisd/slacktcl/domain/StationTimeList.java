package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationTimeList {

    private List<StationTime> values;

    public StationTimeList() {
        this.values = new ArrayList<>();
    }

    public StationTimeList(List<StationTime> values) {
        this.values = values;
    }

    public List<StationTime> getValues() {
        return values;
    }

    public void setValues(List<StationTime> values) {
        this.values = values;
    }

    public void addValue(StationTime stationTime) {
        this.values.add(stationTime);
    }

    public void removeValue(StationTime stationTime) {
        this.values.remove(stationTime);
    }
}
