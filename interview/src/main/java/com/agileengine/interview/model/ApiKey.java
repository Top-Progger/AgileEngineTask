package com.agileengine.interview.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiKey {
    private String apiKey;

    public ApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
