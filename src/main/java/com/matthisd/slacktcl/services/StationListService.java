package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.StationList;

import java.io.IOException;
import java.util.List;

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

    /**
     * Get a list of "valid" station names from a given name.
     * @param stationName the station name to look for.
     * @return The list of stations that may suit for the given entry.
     * @throws IOException Exception while reading the resource file.
     */
    List<String> getStationNamesFromName(String stationName) throws IOException;

}
