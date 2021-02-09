package com.agileengine.interview.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private boolean auth;
    private String token;
}
