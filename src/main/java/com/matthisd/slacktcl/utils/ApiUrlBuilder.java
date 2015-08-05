package com.matthisd.slacktcl.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

public class ApiUrlBuilder {

    @Value("#{tcl.api.stops.url}")
    private String stopsUrl;

    @Value("#{tcl.api.timestops.url}")
    private String timeStopsUrl;

    public ApiUrlBuilder() {
    }

    /**
     * Get the URL to call to get station from a name.
     * @param stopName The stop name.
     * @return the URL to use.
     */
    public String getStopsUrlForStopName(String stopName) {

        StringBuilder urlBuilder = new StringBuilder(this.stopsUrl);

        urlBuilder.append("?compact=false");

        if (!StringUtils.isEmpty(stopName)) {

            urlBuilder.append("&field=name");
            urlBuilder.append("&value=");
            urlBuilder.append(stopName);
        }

        return urlBuilder.toString();

    }

    /**
     * Get the URL to call to get next departures from a station id.
     * @param id The station ID.
     * @return the URL to use.
     */
    public String getTimeStopsUrlForStationId(String id) {

        StringBuilder urlBuilder = new StringBuilder(this.timeStopsUrl);

        urlBuilder.append("?compact=false");

        if (!StringUtils.isEmpty(id)) {

            urlBuilder.append("&field=id");
            urlBuilder.append("&value=");
            urlBuilder.append(id);
        }

        return urlBuilder.toString();
    }
}
