package com.mkm.empiktask.controller;

import com.mkm.empiktask.model.User;
import com.mkm.empiktask.service.UserRequestCountService;
import com.mkm.empiktask.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    final UserService userService;
    final UserRequestCountService userRequestCountService;

    public UserController(UserService userService, UserRequestCountService userRequestCountService) {
        this.userService = userService;
        this.userRequestCountService = userRequestCountService;
    }

    @Operation(summary = "Get a user by his github login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)})
    @GetMapping("/{login}")
    public User getUserByLogin(@PathVariable("login") String login) {
//        try {
            User user = userService.getUserByLogin(login);
            userRequestCountService.saveOrIncrementUserRequestCountByLogin(login);
            return user;
//        } catch (HttpClientErrorException.NotFound e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Login not found", e);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Exception occurred", e);
//        }
    }
}
