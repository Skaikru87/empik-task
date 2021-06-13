package com.mkm.empiktask.webclient;

import com.mkm.empiktask.exception.InternalException;
import com.mkm.empiktask.exception.ResourceNotFoundException;
import com.mkm.empiktask.model.GithubUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class GithubClient {

    private static final String GITHUB_BASE_URL = "https://api.github.com/";
    private static final String GITHUB_USERS_URL = "users/";

    public GithubUser getGithubUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(GITHUB_BASE_URL + GITHUB_USERS_URL + login, GithubUser.class);
        } catch (HttpClientErrorException.NotFound notFoundExp) {
            log.error("Exception while getting Github user:: {}", notFoundExp.getMessage());
            throw new ResourceNotFoundException(notFoundExp.getMessage());
        } catch (Exception e) {
            log.error("Exception while getting Github user:: {}", e.getMessage());
            throw new InternalException(e.getMessage());
        }
    }

}
