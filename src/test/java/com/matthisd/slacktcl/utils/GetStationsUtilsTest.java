package com.matthisd.slacktcl.utils;

import com.matthisd.slacktcl.domain.StationList;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class GetStationsUtilsTest {

    @Test
    public void testGetStationList() {

        StationList stationList = GetStationsUtils.getStationList();

        Assert.assertNotNull("Station list shouldn't be null", stationList);
        Assert.assertEquals("There should be 1000 results", Integer.valueOf(1000), stationList.getNbResults());
        Assert.assertFalse("Fields shouldn't be empty", stationList.getFields().isEmpty());
        Assert.assertFalse("Values shouldn't be empty", stationList.getValues().isEmpty());

    }

    @Test
    public void testGetStationsListMap() {

        List<Map<String,String>> stations = GetStationsUtils.getStations();
        Assert.assertFalse("Stations shouldn't be empty", stations.isEmpty());
        Assert.assertEquals("Stations list size should be 1000", 1000, stations.size());

    }

}
