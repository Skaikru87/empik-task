package com.mkm.empiktask.service;

import com.mkm.empiktask.converter.GithubUser2UserConverter;
import com.mkm.empiktask.model.User;
import com.mkm.empiktask.webclient.GithubClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService {

    final GithubClient githubClient;
    final GithubUser2UserConverter githubUser2UserConverter;

    public UserService(GithubUser2UserConverter githubUser2UserConverter, GithubClient githubClient) {
        this.githubUser2UserConverter = githubUser2UserConverter;
        this.githubClient = githubClient;
    }

    public User getUserByLogin(String login) {
        return githubUser2UserConverter.convert(githubClient.getGithubUserByLogin(login));
    }
}
