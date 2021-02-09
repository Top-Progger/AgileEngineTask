package com.agileengine.interview.service.impl;

import com.agileengine.interview.model.ApiKey;
import com.agileengine.interview.model.AuthResponse;
import com.agileengine.interview.model.Image;
import com.agileengine.interview.model.ImagesPage;
import com.agileengine.interview.service.AgileEngineImagesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@AllArgsConstructor
@Slf4j
public class AgileEngineImagesServiceImpl implements AgileEngineImagesService {
    private static final String AGILE_ENGINE_URL = "http://interview.agileengine.com";
    private static final String AUTH = AGILE_ENGINE_URL + "/auth";
    private static final String IMAGES = AGILE_ENGINE_URL + "/images";
    private static final String DEFAULT_API_KEY = "23567b218376f79d9415";

    @Override
    public String getToken(String apiKey) {
        RestTemplate restTemplate = restTemplate();
        HttpEntity<ApiKey> request = new HttpEntity<>(new ApiKey(apiKey == null ? DEFAULT_API_KEY : apiKey));
        ResponseEntity<AuthResponse> authResponse = restTemplate.postForEntity(AUTH, request, AuthResponse.class);
        return authResponse.getBody().getToken();
    }

    @Override
    public ImagesPage getImages(Integer page) {
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getToken(DEFAULT_API_KEY));

        HttpEntity<?> request = new HttpEntity<>(headers);
        if (page == null) {
            page = 1;
        }

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(IMAGES)
                .queryParam("page", page);

        ResponseEntity<ImagesPage> images = restTemplate
                .exchange(uriBuilder.toUriString(), HttpMethod.GET, request, ImagesPage.class);
        return images.getBody();
    }

    @Override
    public Image getImageInfoById(String imageId) {
        RestTemplate restTemplate = restTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getToken(DEFAULT_API_KEY));
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<Image> image = restTemplate
                .exchange(IMAGES + "/" + imageId, HttpMethod.GET, request, Image.class);
        return image.getBody();
    }

    private RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
