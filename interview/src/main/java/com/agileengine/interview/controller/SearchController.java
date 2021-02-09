package com.agileengine.interview.controller;

import com.agileengine.interview.model.Image;
import com.agileengine.interview.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("interview/api/v1/search")
@AllArgsConstructor
public class SearchController {
    private final CacheService cacheService;

    @GetMapping("/{searchTerm}")
    public ResponseEntity<?> searchImages(@PathVariable(name = "searchTerm") String searchTerm) {
        List<Image> images = cacheService.search(searchTerm);

        return CollectionUtils.isEmpty(images) ? ResponseEntity.notFound().build() : ResponseEntity.ok(images);
    }
}
