package com.matthisd.slacktcl.repositories;

import com.matthisd.slacktcl.domain.SlackRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "slackRequests", path = "slackRequests")
public interface SlackRequestRepository  extends MongoRepository<SlackRequest, String> {

}
