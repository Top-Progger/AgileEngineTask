package com.agileengine.interview.service;

import com.agileengine.interview.model.Image;
import com.agileengine.interview.model.ImagesPage;

public interface AgileEngineImagesService {
    String getToken(String apiKey);

    ImagesPage getImages(Integer page);

    Image getImageInfoById(String imageId);
}
