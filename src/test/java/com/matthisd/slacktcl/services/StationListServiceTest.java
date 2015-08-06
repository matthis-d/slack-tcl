package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.SlackTclApplication;
import com.matthisd.slacktcl.domain.StationList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
