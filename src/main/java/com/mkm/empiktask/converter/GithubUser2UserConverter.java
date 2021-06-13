package com.mkm.empiktask.converter;

import com.mkm.empiktask.model.GithubUser;
import com.mkm.empiktask.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class GithubUser2UserConverter implements Converter<GithubUser, User> {

    @Override
    public User convert(GithubUser githubUser) {
        return User.builder()
                .id(githubUser.getId())
                .login(githubUser.getLogin())
                .name(githubUser.getName())
                .type(githubUser.getType())
                .createdAt(githubUser.getCreatedAt())
                .avatarUrl(githubUser.getAvatarUrl())
                .calculations(calculateFollowersAndPublicRepos(githubUser.getFollowers(), githubUser.getPublicRepos(),
                        (followers, publicRepos)-> (6.0 / followers * (2.0 + publicRepos))
                ))
                .build();
    }

    private Double calculateFollowersAndPublicRepos(Long followers, Long publicRepos, GithubUserCalculator calculator){
        return followers !=0 ? calculator.doCalculations(followers, publicRepos) : 0.0;
    }
}

@FunctionalInterface
 interface GithubUserCalculator{
    public Double doCalculations(Long followers, Long publicRepos);
}