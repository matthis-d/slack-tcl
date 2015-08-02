package com.matthisd.slacktcl.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StationList {

    private List<String> fields;

    @JsonProperty("nb_results")
    private Integer nbResults;

    private List<List<String>> values;

    public StationList() {
        this.fields = new ArrayList<>();
        this.values = new ArrayList<>();
    }

    public StationList(List<String> fields, Integer nbResults, List<List<String>> values) {
        this.fields = fields;
        this.nbResults = nbResults;
        this.values = values;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Integer getNbResults() {
        return nbResults;
    }

    public void setNbResults(Integer nbResults) {
        this.nbResults = nbResults;
    }

    public List<List<String>> getValues() {
        return values;
    }

    public void setValues(List<List<String>> values) {
        this.values = values;
    }


}