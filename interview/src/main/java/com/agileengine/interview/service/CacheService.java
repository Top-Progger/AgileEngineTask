package com.agileengine.interview.service;

import com.agileengine.interview.model.Image;

import java.util.List;

public interface CacheService {
    List<Image> search(String searchTerm);
}
