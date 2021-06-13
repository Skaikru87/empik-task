package com.mkm.empiktask.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GithubUser {
    Long id;
    String login;
    String name;
    String type;
    Long followers;
    @JsonAlias("avatar_url") String avatarUrl;
    @JsonAlias("created_at") Instant createdAt;
    @JsonAlias("public_repos") Long publicRepos;
}
