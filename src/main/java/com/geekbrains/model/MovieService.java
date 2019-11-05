package com.geekbrains.model;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MovieService {
    private static final String URL_RESTAPI_GETFAVORITE = "http://localhost:9000/choiceUser/getFavorite/";
    private static final String URL_RESTAPI_GETLOOK = "http://localhost:9000/choiceUser/getLook/";
    private static final String URL_RESTAPI_GETWATCHED = "http://localhost:9000/choiceUser/getWatched/";

    private RestTemplate restTemplate = new RestTemplateBuilder().build();

    public MovieService() {
    }

    public List<String> getFavorite(String nickname) {
        @SuppressWarnings("unchecked")
        List<String> favorites = restTemplate.getForObject(URL_RESTAPI_GETFAVORITE + nickname, List.class);
        return favorites;
    }

    public List<String> getLook(String nickname) {
        @SuppressWarnings("unchecked")
        List<String> looks = restTemplate.getForObject(URL_RESTAPI_GETLOOK + nickname, List.class);
        return looks;
    }

    public List<String> getWatched(String nickname) {
        @SuppressWarnings("unchecked")
        List<String> watches = restTemplate.getForObject(URL_RESTAPI_GETWATCHED + nickname, List.class);
        return watches;
    }
}
