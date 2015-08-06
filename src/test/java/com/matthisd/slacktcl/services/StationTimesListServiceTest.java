package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.SlackTclApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SlackTclApplication.class)
public class StationTimesListServiceTest {

    @Autowired
    private StationTimesListService stationTimesListService;

    @Test
    public void testGenerateCredentials() {

        String cred = stationTimesListService.generateCredentials();

        Assert.assertNotNull(cred);
    }

}
