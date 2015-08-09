package com.matthisd.slacktcl.services.impl;

import com.matthisd.slacktcl.domain.StationList;
import com.matthisd.slacktcl.services.StationListService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("stationListService")
public class StationListServiceImpl implements StationListService {

    @Value("${tcl.api.stops.url}")
    private String stopsUrl;

    @Override
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

    @Override
    public StationList getStationListFromStationName(String stationName) {

        String url = this.getStopsUrlForStopName(stationName);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, StationList.class);

    }
}
