package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.StationList;

public interface StationListService {

    /**
     * Get the URL to call to get station from a name.
     * @param stopName The stop name.
     * @return the URL to use.
     */
    String getStopsUrlForStopName(String stopName);

    /**
     * Get a list of stations from a station name.
     * @param stationName the station name to find.
     * @return the list of stations found.
     */
    StationList getStationListFromStationName(String stationName);

}
