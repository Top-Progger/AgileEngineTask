package com.agileengine.interview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String author;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String camera;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tags;

    @JsonProperty("cropped_picture")
    private String croppedPicture;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("full_picture")
    private String fullPicture;
}
