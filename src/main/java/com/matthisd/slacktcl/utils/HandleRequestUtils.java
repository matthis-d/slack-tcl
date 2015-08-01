package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.BusStation;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleRequestUtils {


    /**
     * Converts a raw text sent by Slack to a Map.
     * @param body The body sent by Slack.
     * @return The map of pair-values sent.
     */
    public static Map<String,String> convertStringBody(String body) {

        Map<String, String> response = new HashMap<>();

        if (body == null || body.isEmpty()) {
            return response;
        }

        String[] keyValuePairs = body.split("\n");

        for (String keyValuePair : keyValuePairs) {

            String[] entry = keyValuePair.split("=");
            response.put(entry[0].trim(), entry[1].trim());

        }

        return response;
    }


    public static BusStation getBusStationFromText(String text) {

        String trimmedText = text.trim();

        // Test with full pattern: bus station name and bus number
        Pattern fullPattern = Pattern.compile(SlackTclConstants.FULL_STATION_PATTERN);
        Matcher fullMatcher = fullPattern.matcher(trimmedText);

        if (fullMatcher.find()) {
            String stationName = fullMatcher.group(1);
            Integer busNumber = Integer.valueOf(fullMatcher.group(2));

            return new BusStation(stationName, busNumber);
        }

        // Test with only the bus number
        Pattern numberPattern = Pattern.compile(SlackTclConstants.BUS_NUMBER_PATTERN);
        Matcher numberMatcher = numberPattern.matcher(trimmedText);

        if (numberMatcher.find()) {
            Integer busNumber = Integer.valueOf(numberMatcher.group(1));

            return new BusStation(busNumber);
        }

        // Test with the bus station name
        Pattern stationPattern = Pattern.compile(SlackTclConstants.STATION_NAME_PATTERN);
        Matcher stationMatcher = stationPattern.matcher(trimmedText);

        if (stationMatcher.find()) {
            String stationName = stationMatcher.group(1);

            return new BusStation(stationName);
        }

        // None of above worked: return a default station
        return new BusStation();

    }

}
