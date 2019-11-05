package com.geekbrains.model;

import com.geekbrains.domain.User;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Data
public class ClientService {
    private static final String URL_RESTAPI_GETPASS = "http://localhost:9000/users/getPass/";
    private static final String URL_RESTAPI_CREATEUSR = "http://localhost:9000/users/createUser";
    private static final String URL_RESTAPI_EDITUSR = "http://localhost:9000/users/edit/";
    private static final String URL_RESTAPI_DELETEUSR = "http://localhost:9000/users/delete/";

    private RestTemplate restTemplate = new RestTemplate();

    public ClientService() {
    }

    public User getUser(String nickname) {
        return restTemplate.getForObject(URL_RESTAPI_GETPASS + nickname, User.class);
    }

    public int createUser(String firstName, String lastName, String nickname, String password) {
        User user = new User(firstName, lastName, nickname, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);

        User newUsr = restTemplate.postForObject(URL_RESTAPI_CREATEUSR, requestBody, User.class);
        if (newUsr != null && newUsr.getNickname() != null) {
            System.out.println("User created: " + newUsr.getNickname());
            return 201;
        } else {
            System.out.println("Something error!");
            return 410;
        }
    }

    public int editUser(String firstName, String lastName, String nickname, String password, User user) {
        User editUser = new User(firstName, lastName, nickname, password);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> requestBody = new HttpEntity<>(editUser, headers);

        restTemplate.put(URL_RESTAPI_EDITUSR + user.getNickname(), requestBody);

        /*return Optional.ofNullable(restTemplate.getForObject(URL_RESTAPI_GETPASS + nickname, User.class))
                .map(it -> 200)
                .orElse(410);*/
        editUser = restTemplate.getForObject(URL_RESTAPI_GETPASS + nickname, User.class);
        if (editUser != null) {
            return 200;
        } else {
            return 410;
        }
    }

    public int deleteUsr(String nickname) {
        restTemplate.delete(URL_RESTAPI_DELETEUSR + nickname);

        User delUser = restTemplate.getForObject(URL_RESTAPI_GETPASS + nickname, User.class);

        if (delUser != null) {
            return 410;
        } else {
            return 200;
        }
    }
}
