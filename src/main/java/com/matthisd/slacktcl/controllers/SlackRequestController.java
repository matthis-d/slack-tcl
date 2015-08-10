package com.matthisd.slacktcl.controllers;

import com.matthisd.slacktcl.constants.SlackTclConstants;
import com.matthisd.slacktcl.domain.*;
import com.matthisd.slacktcl.enums.RequestTypeEnum;
import com.matthisd.slacktcl.services.SlackRequestService;
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

import java.io.IOException;
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

    @Autowired
    private SlackRequestService slackRequestService;

    @RequestMapping(method = RequestMethod.GET)
    public String sayHello() {

        LOGGER.debug("GET request is made");

        return "Hello!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(@RequestBody String slackRequestBody) throws IOException {

        LOGGER.debug("Request is made");

        Map<String, String> slackRequestMap = HandleRequestUtils.convertStringBody(slackRequestBody);

        if (!StringUtils.equals(SlackTclConstants.TCL_COMMAND, slackRequestMap.get("command"))) {
            return "Mauvaise commande entrée";
        }

        // Convert to Slack request
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(slackRequestMap.get("text"));

        // Save the request in the database
        this.slackRequestService.save(slackRequest);

        String output;

        switch (slackRequest.getType()) {
            case TIME_STATION_BUS:
                output = this.getNextStationTimes(slackRequest);
                break;

            case LIST:
                output = this.getStationsList(slackRequest);
                break;

            default:
                output = "Commande non reconnue";
                break;
        }

        return output;
    }

    /**
     * Get next departure time for a bus number to a station from a station.
     * @param slackRequest The request made by the user
     * @return the next departures as a string.
     */
    public String getNextStationTimes(SlackRequest slackRequest) {

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


    /**
     * Get the list of valid stations from a request to get a list
     * @param slackRequest the request sent by the user.
     * @return the response that will be displayed on Slack with all stations found.
     * @throws IOException if the resource file cannot be read or if an other error occurs.
     */
    public String getStationsList(SlackRequest slackRequest) throws IOException {

        String stationName = slackRequest.getListName();

        List<String> stationsFound = this.stationListService.getStationNamesFromName(stationName);

        // If there is no station found, just indicate it to the user
        if (stationsFound.isEmpty()) {
            return "Aucune station avec ce nom trouvée";
        }

        StringBuilder resultBuilder = new StringBuilder("Stations trouvées : \n");
        stationsFound.forEach(
                stationFound -> resultBuilder.append(stationFound).append("\n")
        );

        return resultBuilder.toString();

    }


}
