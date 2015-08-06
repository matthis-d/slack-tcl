package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.SlackRequest;
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
     *
     * @param body The body sent by Slack.
     * @return The map of pair-values sent.
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

        // Test with full pattern: bus station name and bus number
        Pattern fullPattern = Pattern.compile(SlackTclConstants.FULL_STATION_PATTERN);
        Matcher fullMatcher = fullPattern.matcher(trimmedText);

        if (fullMatcher.find()) {
            String stationName = fullMatcher.group(1);
            String busNumber = fullMatcher.group(2);
            String direction = fullMatcher.group(3);

            return new SlackRequest(stationName, busNumber, direction);
        }

        return new SlackRequest();

    }

}
