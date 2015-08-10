package com.matthisd.slacktcl.services.impl;

import com.google.gson.Gson;
import com.matthisd.slacktcl.domain.StationList;
import com.matthisd.slacktcl.services.StationListService;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getStationNamesFromName(String stationName) throws IOException {

        ClassPathResource stationsResource = new ClassPathResource("all-stations.json");

        InputStream in = stationsResource.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(in, CharEncoding.UTF_8);

        Gson gson = new Gson();
        List allStations = gson.fromJson(inputStreamReader, List.class);

        List<String> results = new ArrayList<>();

        // Do a filter on all stations in the list
        for (Object station : allStations) {

            String realStationName = (String) station;

            if (StringUtils.containsIgnoreCase(realStationName, stationName)) {
                results.add(realStationName);
            }
        }

        return results;
    }
}
