package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.SlackTclApplication;
import com.matthisd.slacktcl.domain.StationList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SlackTclApplication.class)
public class StationListServiceTest {

    @Autowired
    private StationListService stationListService;

    @Test
    public void testGetStationListFromStationName() {

        StationList stationList = stationListService.getStationListFromStationName("Technoparc");

        Assert.assertNotNull("Station list shouldn't be null", stationList);

    }

    @Test
    public void testGetStationNamesFromName() throws IOException {

        List<String> stationsFound = stationListService.getStationNamesFromName("techno");

        Assert.assertFalse("List shouldn't be empty", stationsFound.isEmpty());
        Assert.assertTrue("List should contains Technoparc", stationsFound.contains("Technoparc"));
        Assert.assertEquals("List should contains 2 values", 2, stationsFound.size());

    }

}
