package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.StationTimeList;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("stationTimesListService")
public class StationTimesListService {

    @Value("${tcl.api.username}")
    private String username;

    @Value("${tcl.api.password}")
    private String password;

    @Value("${tcl.api.timestops.url}")
    private String timeStopsUrl;

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

    /**
     * Generate credentials for a basic HTTP authentication with username and password.
     * Based on http://springinpractice.com/2013/10/02/quick-tip-basic-authentication-with-spring-resttemplate
     * @return the generated credential.
     */
    public String generateCredentials() {

        StringBuilder plainCredsBuilder = new StringBuilder(this.username);
        plainCredsBuilder.append(":");
        plainCredsBuilder.append(this.password);

        byte[] plainCredsBytes = plainCredsBuilder.toString().getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);

    }

    /**
     * Get a list of stations and tfrom a station name.
     * @param stationId the station id for which to find times.
     * @return the list of stations and times found.
     */
    public StationTimeList getStationTimeForStationId(String stationId) {

        String url = this.getTimeStopsUrlForStationId(stationId);

        RestTemplate restTemplate = new RestTemplate();

        // See http://springinpractice.com/2013/10/02/quick-tip-basic-authentication-with-spring-resttemplate
        final String base64Creds = this.generateCredentials();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        final HttpEntity<String> request = new HttpEntity<>(headers);

        final ResponseEntity<StationTimeList> response = restTemplate.exchange(url, HttpMethod.GET,
                request, StationTimeList.class);
        return response.getBody();
    }

}
