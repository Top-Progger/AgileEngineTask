package com.agileengine.interview.controller;

import com.agileengine.interview.model.Image;
import com.agileengine.interview.model.ImagesPage;
import com.agileengine.interview.service.AgileEngineImagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("interview/api/v1")
public class ImageController {
    private final AgileEngineImagesService agileEngineImagesService;

    @GetMapping("/token/{apiKey}")
    public String getToken(@PathVariable(name = "apiKey", required = false) String apiKey) {
        return agileEngineImagesService.getToken(apiKey);
    }

    @GetMapping("/images")
    public ImagesPage getPictures(@RequestParam(name = "page", required = false) Integer page) {
        return agileEngineImagesService.getImages(page);
    }

    @GetMapping("/images/{id}")
    public Image getImageById(@PathVariable(name = "id") String id) {
        return agileEngineImagesService.getImageInfoById(id);
    }
}
