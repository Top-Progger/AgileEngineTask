package com.agileengine.interview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImagesPage {
    private List<Image> pictures;
    private int page;
    private int pageCount;
    private boolean hasMore;
}
