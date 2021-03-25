package app.controller;

import app.model.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateClient {
    private String URL = "http://91.241.64.178:7081/api/users";
    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public ResponseEntity<List<User>> getAllUsers() {
        return restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
    }

    public ResponseEntity<String> updateUser(User user, String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
    }

    public ResponseEntity<String> addUser(User user, String sessionId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> deleteUser(User user, String sessionId) {
        String URL_FOR_DELETE = String.format("%s/%d", URL, user.getId());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);

        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        return restTemplate.exchange(URL_FOR_DELETE, HttpMethod.DELETE, entity, String.class);
    }
}
