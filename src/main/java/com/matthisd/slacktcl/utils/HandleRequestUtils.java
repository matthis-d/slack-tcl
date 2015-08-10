package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.SlackRequest;
import com.matthisd.slacktcl.enums.RequestTypeEnum;
import org.apache.commons.codec.CharEncoding;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleRequestUtils {


    /**
     * Converts a raw text sent by Slack to a Map.
     * @param body The body sent by Slack.
     * @return The map of pair-values sent.
     * @throws UnsupportedEncodingException if the URLDecode uses a bad encoding.
     */
    public static Map<String, String> convertStringBody(String body)
            throws UnsupportedEncodingException {

        Map<String, String> response = new HashMap<>();

        if (body == null || body.isEmpty()) {
            return response;
        }

        String[] keyValuePairs = body.split("&");

        for (String keyValuePair : keyValuePairs) {

            String[] entry = keyValuePair.split("=");

            String value = entry.length > 1 ? entry[1] : "";

            response.put(URLDecoder.decode(entry[0].trim(), CharEncoding.UTF_8),
                    URLDecoder.decode(value.trim(), CharEncoding.UTF_8));

        }

        return response;
    }


    public static SlackRequest getSlackRequestFromText(String text) {

        String trimmedText = text.trim();

        // Test with pattern to get a list: "list stationName"
        Pattern listPattern = Pattern.compile(SlackTclConstants.LIST_PATTERN);
        Matcher listMatcher = listPattern.matcher(trimmedText);

        if (listMatcher.find()) {
            SlackRequest listRequest = new SlackRequest();
            listRequest.setType(RequestTypeEnum.LIST);
            listRequest.setListName(listMatcher.group(1).trim());

            return listRequest;
        }


        // Test with full pattern: bus station name, bus number and direction
        Pattern fullPattern = Pattern.compile(SlackTclConstants.FULL_STATION_PATTERN);
        Matcher fullMatcher = fullPattern.matcher(trimmedText);

        if (fullMatcher.find()) {
            String stationName = fullMatcher.group(1).trim();
            String busNumber = fullMatcher.group(2).trim();
            String direction = fullMatcher.group(3).trim();

            return new SlackRequest(stationName, busNumber, direction);
        }

        // Test with bus station and bus number in all directions
        Pattern stationBusPattern = Pattern.compile(SlackTclConstants.STATION_BUS_PATTERN);
        Matcher stationBusMatcher = stationBusPattern.matcher(trimmedText);

        if (stationBusMatcher.find()) {
            String stationName = stationBusMatcher.group(1).trim();
            String busNumber = stationBusMatcher.group(2).trim();

            return new SlackRequest(stationName, busNumber);
        }


        // Test with station in all directions and all bus numbers
        Pattern stationPattern = Pattern.compile(SlackTclConstants.STATION_PATTERN);
        Matcher stationMatcher = stationPattern.matcher(trimmedText);

        if (stationMatcher.find()) {
            String stationName = stationMatcher.group(1);

            return new SlackRequest(stationName);
        }

        return new SlackRequest();

    }

}
