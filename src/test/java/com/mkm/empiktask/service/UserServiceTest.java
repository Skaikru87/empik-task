package com.mkm.empiktask.service;

import com.mkm.empiktask.converter.GithubUser2UserConverter;
import com.mkm.empiktask.exception.ResourceNotFoundException;
import com.mkm.empiktask.webclient.GithubClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    GithubClient githubClient;

    @Test
    public void shouldThrowException_whenUserNotExists(){
        String notfoundlogin = "notfoundlogin";
        when(githubClient.getGithubUserByLogin(notfoundlogin)).thenThrow(new ResourceNotFoundException("not found"));
        Assertions.assertThrows(ResourceNotFoundException.class, ()-> userService.getUserByLogin(notfoundlogin));
    }
}
