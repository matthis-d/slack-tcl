package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.SlackRequest;

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


    public static SlackRequest getSlackRequestFromText(String text) {

        String trimmedText = text.trim();

        // Test with full pattern: bus station name and bus number
        Pattern fullPattern = Pattern.compile(SlackTclConstants.FULL_STATION_PATTERN);
        Matcher fullMatcher = fullPattern.matcher(trimmedText);

        if (fullMatcher.find()) {
            String stationName = fullMatcher.group(1);
            System.out.println(stationName);
            String busNumber = fullMatcher.group(2);
            System.out.println(busNumber);
            String direction = fullMatcher.group(3);
            System.out.println(direction);

            return new SlackRequest(stationName, busNumber, direction);
        }

        return new SlackRequest();

    }

}
