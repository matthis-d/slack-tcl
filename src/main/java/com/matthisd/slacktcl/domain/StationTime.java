package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationTime {

    private String id;

    private String direction;

    @JsonProperty("delaipassage")
    private String delaiPassage;

    private String ligne;

    public StationTime() {
    }

    public StationTime(String id, String direction, String delaiPassage, String ligne) {
        this.id = id;
        this.direction = direction;
        this.delaiPassage = delaiPassage;
        this.ligne = ligne;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDelaiPassage() {
        return delaiPassage;
    }

    public void setDelaiPassage(String delaiPassage) {
        this.delaiPassage = delaiPassage;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("StationTime{");
        sb.append("id='").append(id).append('\'');
        sb.append(", direction='").append(direction).append('\'');
        sb.append(", delaiPassage='").append(delaiPassage).append('\'');
        sb.append(", ligne='").append(ligne).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * Determines if the bus is going to the given direction.
     * @param direction the direction to check.
     * @return true if it is serving it or if the direction is empty, false otherwise.
     */
    public Boolean isGoingTo(String direction) {

        if (StringUtils.isEmpty(direction)) {
            return Boolean.TRUE;
        }

        return StringUtils.containsIgnoreCase(this.getDirection(), direction.trim());
    }

    /**
     * Determines if the bus is stopping at this station.
     * @param busNumber the bus number to check.
     * @return true if the bus is stopping here or if it is empty, false otherwise.
     */
    public Boolean isBusStopping(String busNumber) {

        if (StringUtils.isEmpty(busNumber)) {
            return Boolean.TRUE;
        }

        return StringUtils.containsIgnoreCase(this.getLigne(), busNumber.trim());
    }

    /**
     * Determines if the bus is stopping here and goes to the given direction.
     * @param busNumber the bus number to check.
     * @param direction the direction to check.
     * @return the combination of {@link #isBusStopping(String)} and {@link #isGoingTo(String)}.
     */
    public Boolean isBusStoppingAndGoingTo(String busNumber, String direction) {
        return this.isGoingTo(direction) && this.isBusStopping(busNumber);
    }
}
