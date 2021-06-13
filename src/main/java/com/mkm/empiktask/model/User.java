package com.mkm.empiktask.model;

import lombok.*;
import java.time.Instant;


@Builder
@Getter
@ToString
@EqualsAndHashCode
public class User {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private Instant createdAt;
    private Double calculations;

}
