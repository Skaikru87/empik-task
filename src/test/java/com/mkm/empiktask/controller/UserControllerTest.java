package com.mkm.empiktask.controller;

import com.mkm.empiktask.exception.InternalException;
import com.mkm.empiktask.exception.ResourceNotFoundException;
import com.mkm.empiktask.model.User;
import com.mkm.empiktask.service.UserRequestCountService;
import com.mkm.empiktask.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRequestCountService userRequestCountService;

    @Test
    public void shouldReturn200_whenUseExists() throws Exception {
        String validUserLogin = "octocat";
        mockMvc.perform((get("/users/" + validUserLogin)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404_whenUserNotExists() throws Exception {
        String inValidUserLogin = "usernotfound";
        when(userService.getUserByLogin(inValidUserLogin)).thenThrow(new ResourceNotFoundException("Not found"));
        mockMvc.perform((get("/users/" + inValidUserLogin)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturn500_whenExternalApiThrowUnmatchedException() throws Exception {
        String inValidUserLogin = "usernotfound";
        when(userService.getUserByLogin(inValidUserLogin)).thenThrow(new InternalException("internal exception"));
        mockMvc.perform((get("/users/" + inValidUserLogin)))
                .andExpect(status().isInternalServerError());
    }

}
