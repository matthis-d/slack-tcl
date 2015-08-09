package com.matthisd.slacktcl.services.impl;

import com.matthisd.slacktcl.domain.StationTimeList;
import com.matthisd.slacktcl.services.StationTimesListService;
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
public class StationTimesListServiceImpl implements StationTimesListService {

    @Value("${tcl.api.username}")
    private String username;

    @Value("${tcl.api.password}")
    private String password;

    @Value("${tcl.api.timestops.url}")
    private String timeStopsUrl;

    @Override
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

    @Override
    public String generateCredentials() {

        StringBuilder plainCredsBuilder = new StringBuilder(this.username);
        plainCredsBuilder.append(":");
        plainCredsBuilder.append(this.password);

        byte[] plainCredsBytes = plainCredsBuilder.toString().getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);

    }

    @Override
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
