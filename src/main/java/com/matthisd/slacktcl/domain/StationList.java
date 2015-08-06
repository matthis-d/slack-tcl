package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Domain class to represent the API response for a list of stations
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StationList {

    /**
     * List of all stations
     */
    private List<BusStation> values;

    /**
     * Default constructor
     */
    public StationList() {
        this.values = new ArrayList<>();
    }

    /**
     * Default constructor with values to set.
     * @param values List of stations to initialize
     */
    public StationList(List<BusStation> values) {
        this.values = values;
    }

    public List<BusStation> getValues() {
        return values;
    }

    public void setValues(List<BusStation> values) {
        this.values = values;
    }

    /**
     * Add a bus station to the list of values.
     * @param busStation the station to add.
     */
    public void addValue(BusStation busStation) {
        this.values.add(busStation);
    }

    /**
     * Remove a bus station from values.
     * @param busStation the station to remove from the list.
     */
    public void removeValue(BusStation busStation) {
        this.values.remove(busStation);
    }

}