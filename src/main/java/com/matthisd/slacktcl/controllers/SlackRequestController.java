package com.matthisd.slacktcl.controllers;

import com.matthisd.slacktcl.utils.HandleRequestUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller("slackRequestController")
@RequestMapping("/request")
public class SlackRequestController {

    public final static Logger LOGGER = Logger.getLogger(SlackRequestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String sayHello() {

        LOGGER.debug("GET request is made");

        return "Hello!";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String handleRequest(@RequestBody String slackRequestBody) {

        LOGGER.debug("Request is made");

        Map<String, String> slackRequest = HandleRequestUtils.convertStringBody(slackRequestBody);

        // TODO: do the request

        return slackRequest.get("command");

    }


}
