package com.agileengine.interview.service.impl;

import com.agileengine.interview.model.Image;
import com.agileengine.interview.model.ImagesPage;
import com.agileengine.interview.service.AgileEngineImagesService;
import com.agileengine.interview.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CacheServiceImpl implements CacheService {
    private List<Image> imagesCache;
    private final AgileEngineImagesService agileEngineImagesService;

    @Override
    public List<Image> search(String searchTerm) {
        List<Image> foundImages = new ArrayList<>();
        imagesCache.forEach(image -> {
            if (isImageContainsSearchTerm(image, searchTerm)) {
                foundImages.add(image);
            }
        });

        return foundImages;
    }

    private boolean isImageContainsSearchTerm(Image image, String searchTerm) {
        List<Field> fields = Arrays.asList(image.getClass().getDeclaredFields());

        List<String> values = new ArrayList<>();
        fields.forEach(field -> {
            try {
                field.setAccessible(true);
                values.add((String) field.get(image));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return values.contains(searchTerm);
    }

    public List<Image> getCache() {
        return imagesCache;
    }

    @PostConstruct
    public void init() {
        Integer imagePageCounter = 1;
        List<Image> pictures = new ArrayList<>();

        ImagesPage firstPage = agileEngineImagesService.getImages(imagePageCounter);
        firstPage.getPictures().forEach(image -> {
            pictures.add(agileEngineImagesService.getImageInfoById(image.getId()));
        });

        boolean hasMore = firstPage.isHasMore();

        ImagesPage page;
        while (hasMore) {
            imagePageCounter++;
            page = agileEngineImagesService.getImages(imagePageCounter);
            page.getPictures().forEach(image -> {
                pictures.add(agileEngineImagesService.getImageInfoById(image.getId()));
            });
            hasMore = page.isHasMore();
        }

        imagesCache = pictures;
    }
}
