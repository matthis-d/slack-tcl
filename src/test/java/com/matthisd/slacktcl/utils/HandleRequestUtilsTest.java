package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.domain.BusStation;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class HandleRequestUtilsTest {

    @Test
    public void testConvertStringBody() {

        String body = "" +
                "token=gIkuvaNzQIHg97ATvDxqgjtO\n" +
                "team_id=T0001\n" +
                "team_domain=example\n" +
                "channel_id=C2147483705\n" +
                "channel_name=test\n" +
                "user_id=U2147483697\n" +
                "user_name=Steve\n" +
                "command=/weather\n" +
                "text=94070";

        Map<String, String> response = HandleRequestUtils.convertStringBody(body);

        Assert.assertFalse("Response shouldn't be empty", response.isEmpty());
        Assert.assertEquals("The command should be /weather", "/weather", response.get("command"));
        Assert.assertEquals("The text should be 94070", "94070", response.get("text"));
    }

    @Test
    public void testGetBusStationFromText() {

        String text = "gare de vaise 6";
        BusStation busStation = HandleRequestUtils.getBusStationFromText(text);

        Assert.assertEquals("Station should be \"gare de vaise\"", "gare de vaise", busStation.getStationName());
        Assert.assertEquals("Bus number should be 6", Integer.valueOf(6), busStation.getBusNumber());

        text = "technoparc 89";
        busStation = HandleRequestUtils.getBusStationFromText(text);

        Assert.assertEquals("Station should be \"technoparc\"", "technoparc", busStation.getStationName());
        Assert.assertEquals("Bus number should be 89", Integer.valueOf(89), busStation.getBusNumber());

        text = "sans souci";
        busStation = HandleRequestUtils.getBusStationFromText(text);

        Assert.assertEquals("Station should be \"sans souci\"", "sans souci", busStation.getStationName());
        Assert.assertEquals("Bus number should be 6", Integer.valueOf(6), busStation.getBusNumber());

        text = "89";
        busStation = HandleRequestUtils.getBusStationFromText(text);

        Assert.assertEquals("Station should be \"technoparc\"", "technoparc", busStation.getStationName());
        Assert.assertEquals("Bus number should be 89", Integer.valueOf(89), busStation.getBusNumber());

    }



}
