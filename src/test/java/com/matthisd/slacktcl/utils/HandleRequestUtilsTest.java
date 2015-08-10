package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.domain.SlackRequest;
import com.matthisd.slacktcl.enums.RequestTypeEnum;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class HandleRequestUtilsTest {

    @Test
    public void testConvertStringBody() throws UnsupportedEncodingException {

        String body = "agent=webapp&command=%2Ftcl&text=&channel=C02NXAH5H&token=xoxs-2779357159-2779357167-4209109658-d0e00b20c5&set_active=true&_attempts=1";

        Map<String, String> response = HandleRequestUtils.convertStringBody(body);

        Assert.assertFalse("Response shouldn't be empty", response.isEmpty());
        Assert.assertEquals("The command should be /tcl", "/tcl", response.get("command"));
        Assert.assertEquals("The text should be empty", "", response.get("text"));
    }

    @Test
    public void testGetBusStationFromFullText() {

        String text = "Technoparc:6:Gare de Vaise";
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(text);

        Assert.assertEquals("Station should be \"Technoparc\"", "Technoparc", slackRequest.getStationName());
        Assert.assertEquals("Bus number should be 6", "6", slackRequest.getBusNumber());
        Assert.assertEquals("Destinatoin should be \"Gare de Vaise\"", "Gare de Vaise", slackRequest.getDirection());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

        text = "A.Thomas - INSERM:C16:Charpennes";
        slackRequest = HandleRequestUtils.getSlackRequestFromText(text);
        Assert.assertEquals("Station should be \"A.Thomas - INSERM\"", "A.Thomas - INSERM", slackRequest.getStationName());
        Assert.assertEquals("Bus number should be C16", "C16", slackRequest.getBusNumber());
        Assert.assertEquals("Destinatoin should be \"Charpennes\"", "Charpennes", slackRequest.getDirection());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

    }

    @Test
    public void testGetBusStationFromTextStationAndBus() {

        String text = "Technoparc:6";
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(text);

        Assert.assertEquals("Station should be \"Technoparc\"", "Technoparc", slackRequest.getStationName());
        Assert.assertEquals("Bus number should be 6", "6", slackRequest.getBusNumber());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

        text = "A.Thomas - INSERM:C16";
        slackRequest = HandleRequestUtils.getSlackRequestFromText(text);
        Assert.assertEquals("Station should be \"A.Thomas - INSERM\"", "A.Thomas - INSERM", slackRequest.getStationName());
        Assert.assertEquals("Bus number should be C16", "C16", slackRequest.getBusNumber());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

    }

    @Test
    public void testGetBusStationFromTextStation() {

        String text = "Technoparc";
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(text);

        Assert.assertEquals("Station should be \"Technoparc\"", "Technoparc", slackRequest.getStationName());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

        text = "A.Thomas - INSERM";
        slackRequest = HandleRequestUtils.getSlackRequestFromText(text);
        Assert.assertEquals("Station should be \"A.Thomas - INSERM\"", "A.Thomas - INSERM", slackRequest.getStationName());
        Assert.assertEquals("The request type should be TIME_STATION_BUS",
                RequestTypeEnum.TIME_STATION_BUS, slackRequest.getType());

    }

    @Test
    public void testGetList() {

        String text = "list Techno";
        SlackRequest slackRequest = HandleRequestUtils.getSlackRequestFromText(text);

        Assert.assertEquals("The request type should be LIST", RequestTypeEnum.LIST, slackRequest.getType());
        Assert.assertEquals("The station looked for should contains Techno", "Techno", slackRequest.getListName());
    }



}
