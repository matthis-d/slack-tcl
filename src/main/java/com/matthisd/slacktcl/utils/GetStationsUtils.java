package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.StationList;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStationsUtils {

    /**
     * Verify the URL given as parameter is not empty.
     * If so, return the default URL to query. If not, return the URL as given.
     * @param url the URL to verify.
     * @return the verified URL.
     */
    public static String verifyUrl(String url) {

        if (url == null || url.isEmpty()) {
            return SlackTclConstants.DEFAULT_QUERY_URL;
        }

        return url;
    }

    /**
     * Get the stations list from the URL given as parameter
     * @param url URL to query
     * @return a Station List object
     */
    public static StationList getStationList(String url) {

        url = verifyUrl(url);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, StationList.class);

    }

    /**
     * Get the stations list from the default URL for TCL
     * @return a Station List object
     */
    public static StationList getStationList() {
        return getStationList(SlackTclConstants.DEFAULT_QUERY_URL);
    }

    /**
     * Get the stations list from the URL given as parameter.
     * It converts the default answers to a list of map.
     * @param url the URL to fetch.
     * @return a list a map where each map represents a station.
     */
    public static List<Map<String, String>> getStations(String url) {

        url = verifyUrl(url);

        // Get the default list at first
        StationList stationList = getStationList(url);

        // Prepare the result
        List<Map<String, String>> stations = new ArrayList<>();

        // Get field that will be keys of the maps
        List<String> fields = stationList.getFields();

        // Iterate over all values in answer to assemble them with fields
        for (List<String> stationValues : stationList.getValues()) {

            Map<String, String> valuesMap = new HashMap<>();

            // Do the mapping fields/values
            for (int i = 0; i < stationValues.size(); i++) {
                valuesMap.put(fields.get(i), stationValues.get(i));
            }

            stations.add(valuesMap);
        }

        return stations;
    }

    /**
     * Get the stations list from the URL given as parameter.
     * It converts the default answers to a list of map.
     * It uses the default URL as webservice to query.
     * @return a list a map where each map represents a station.
     */
    public static List<Map<String, String>> getStations() {
        return getStations(SlackTclConstants.DEFAULT_QUERY_URL);
    }

}
