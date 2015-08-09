package com.matthisd.slacktcl.services;

import com.matthisd.slacktcl.domain.SlackRequest;

import java.util.List;

public interface SlackRequestService {

    List<SlackRequest> findAll();

    SlackRequest save(SlackRequest slackRequest);

    void remove(SlackRequest slackRequest);

}
