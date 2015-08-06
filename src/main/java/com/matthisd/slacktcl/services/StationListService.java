package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.StationList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Service("stationListService")
public class StationListService {

    @Value("${tcl.api.stops.url}")
    private String stopsUrl;


    /**
     * Get the URL to call to get station from a name.
     * @param stopName The stop name.
     * @return the URL to use.
     */
    public String getStopsUrlForStopName(String stopName) {

        StringBuilder urlBuilder = new StringBuilder(this.stopsUrl);

        urlBuilder.append("?compact=false");

        if (!StringUtils.isEmpty(stopName)) {

            urlBuilder.append("&field=nom");
            urlBuilder.append("&value=");
            urlBuilder.append(stopName);
        }

        return urlBuilder.toString();

    }


    /**
     * Get a list of stations from a station name.
     * @param stationName the station name to find.
     * @return the list of stations found.
     */
    public StationList getStationListFromStationName(String stationName) {

        String url = this.getStopsUrlForStopName(stationName);

        RestTemplate restTemplate = new RestTemplate();

        StationList stationList = restTemplate.getForObject(url, StationList.class);

        return stationList;
    }

}
