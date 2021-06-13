package com.mkm.empiktask.service;

import com.mkm.empiktask.model.UserRequestCount;
import com.mkm.empiktask.repository.UserRequestCountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserRequestCountServiceTest {

    @Mock
    private UserRequestCountRepository userRequestCountRepository;

    @InjectMocks
    UserRequestCountService userRequestCountService;

    @Test
    public void shouldSaveRequest_whenEntityNotExistsForCurrentLogin(){
        String login = "octocat";
        when(userRequestCountRepository.existsById(any())).thenReturn(false);
        UserRequestCount urc = UserRequestCount.createFirst(login);
        when(userRequestCountRepository.getById(any())).thenReturn(urc);

        userRequestCountService.saveOrIncrementUserRequestCountByLogin(login);
        verify(userRequestCountRepository, times(1)).save(any());
        verify(userRequestCountRepository, times(0)).incrementRequestCounterByOne(any());
        Assertions.assertEquals(1, userRequestCountRepository.getById(login).getRequestCount());
        Assertions.assertEquals(login, userRequestCountRepository.getById(login).getLogin());
    }
    @Test
    public void shouldIncrementCounterRequest_whenEntityExistsForCurrentLogin(){
        String login = "octocat";
        when(userRequestCountRepository.existsById(any())).thenReturn(true);
        UserRequestCount urc = UserRequestCount.createFirst(login);
        urc.setRequestCount(2L);
        when(userRequestCountRepository.getById(any())).thenReturn(urc);

        userRequestCountService.saveOrIncrementUserRequestCountByLogin(login);
        verify(userRequestCountRepository, times(0)).save(any());
        verify(userRequestCountRepository, times(1)).incrementRequestCounterByOne(any());
        Assertions.assertEquals(2, userRequestCountRepository.getById(login).getRequestCount());
    }

}
