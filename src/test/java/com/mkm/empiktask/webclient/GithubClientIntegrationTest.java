package com.mkm.empiktask.webclient;

import com.mkm.empiktask.exception.ResourceNotFoundException;
import com.mkm.empiktask.model.GithubUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
public class GithubClientIntegrationTest {

    @Autowired
    GithubClient githubClient;

    @Test
    public void shouldReturnUser_whenUserExists(){
        //valid user login
        String validUserLogin ="octocat";
        //getting user data from Api
        GithubUser user = githubClient.getGithubUserByLogin(validUserLogin);
        //Logins should be the same
        Assertions.assertEquals(validUserLogin, user.getLogin());
    }

    @Test
    public void shouldThrowResourceNotFound_whenUserNotExists(){
        String notValidUser = "notfounduser";
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> githubClient.getGithubUserByLogin(notValidUser) );
    }
}
