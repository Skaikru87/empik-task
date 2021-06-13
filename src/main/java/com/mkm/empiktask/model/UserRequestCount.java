package com.mkm.empiktask.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
public class UserRequestCount {

    @Id
    @Column(name = "LOGIN", nullable = false)
    private String login;

    @Column(name = "REQUEST_COUNT", nullable = false)
    private Long requestCount;

    public static UserRequestCount createFirst(String login){
        return new UserRequestCount(login, 1L);
    }
}
