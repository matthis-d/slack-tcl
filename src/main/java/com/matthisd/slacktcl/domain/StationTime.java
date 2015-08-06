package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
}
