package com.matthisd.slacktcl.services.impl;

import com.matthisd.slacktcl.domain.SlackRequest;
import com.matthisd.slacktcl.repositories.SlackRequestRepository;
import com.matthisd.slacktcl.services.SlackRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("slackRequestService")
public class SlackRequestServiceImpl implements SlackRequestService {

    @Autowired
    private SlackRequestRepository slackRequestRepository;

    @Override
    public List<SlackRequest> findAll() {
        return this.slackRequestRepository.findAll();
    }

    @Override
    public SlackRequest save(SlackRequest slackRequest) {
        return this.slackRequestRepository.save(slackRequest);
    }

    @Override
    public void remove(SlackRequest slackRequest) {
        this.slackRequestRepository.delete(slackRequest);
    }
}
