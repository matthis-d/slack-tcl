package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.StationTimeList;

public interface StationTimesListService {

    /**
     * Get the URL to call to get next departures from a station id.
     * @param id The station ID.
     * @return the URL to use.
     */
    String getTimeStopsUrlForStationId(String id);

    /**
     * Generate credentials for a basic HTTP authentication with username and password.
     * Based on http://springinpractice.com/2013/10/02/quick-tip-basic-authentication-with-spring-resttemplate
     * @return the generated credential.
     */
    String generateCredentials();

    /**
     * Get a list of stations and tfrom a station name.
     * @param stationId the station id for which to find times.
     * @return the list of stations and times found.
     */
    StationTimeList getStationTimeForStationId(String stationId);

}
