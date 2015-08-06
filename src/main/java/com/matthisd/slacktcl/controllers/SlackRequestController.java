package com.matthisd.slacktcl.controllers;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.*;
import com.matthisd.slacktcl.services.StationListService;
import com.matthisd.slacktcl.services.StationTimesListService;
import com.matthisd.slacktcl.utils.HandleRequestUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("slackRequestController")
@RequestMapping("/request")
public class SlackRequestController {

    public final static Logger LOGGER = Logger.getLogger(SlackRequestController.class);

    @Autowired
    private StationListService stationListService;

    @Autowired
    private StationTimesListService stationTimesListService;

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {

        LOGGER.debug("GET request is made");

        return "Hello!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(@RequestBody String slackRequestBody) throws UnsupportedEncodingException {

        LOGGER.debug("Request is made");

        Map<String, String> slackRequestMap = HandleRequestUtils.convertStringBody(slackRequestBody);

        if (!StringUtils.equals(SlackTclConstants.TCL_COMMAND, slackRequestMap.get("command"))) {
            return "You entered wrong command";
        }

        // Convert to Slack request
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(slackRequestMap.get("text"));
        String busNumber = slackRequest.getBusNumber();
        String direction = slackRequest.getDirection();

        // Do the request to get stations
        StationList stationList = this.stationListService.getStationListFromStationName(slackRequest.getStationName());


        // Filter stations
        List<BusStation> possibleBusStations = new ArrayList<>();

        stationList.getValues().forEach(
                busStation -> {
                    if (busStation.isServing(busNumber)) {
                        possibleBusStations.add(busStation);
                    }
                }
        );

        // Get stations time for each station id
        List<StationTime> stationTimes = new ArrayList<>();

        possibleBusStations.forEach(
                possibleBusStation -> {

                    StationTimeList stationTimeList = stationTimesListService.getStationTimeForStationId(
                            possibleBusStation.getId());

                    stationTimes.addAll(stationTimeList.getValues());
                }
        );

        // Filter with station and direction
        StringBuilder result = new StringBuilder();

        stationTimes.forEach(
                stationTime -> {
                    if (stationTime.isBusStoppingAndGoingTo(busNumber, direction)) {
                        result.append(busNumber);
                        result.append(" passe dans ");
                        result.append(stationTime.getDelaiPassage());
                        result.append("\n");
                    }
                }
        );

        if (result.toString().isEmpty()) {
            result.append("Aucun prochain passage trouvé");
        }

        return result.toString();
    }


}
