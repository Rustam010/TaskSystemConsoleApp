package com.rustam.consoleApp.client.impl;

import com.rustam.consoleApp.client.RestClient;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RestClientImpl implements RestClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders sessionHeaders = new HttpHeaders();
    private static final String BASE_URL = "http://localhost:4142/task-system";

    @Override
    public String getCsrfToken() {
        String csrfUrl = BASE_URL + "/csrf";
        try {
            ResponseEntity<Map> response = restTemplate.exchange(csrfUrl, HttpMethod.GET, null, Map.class);
            Map<String, String> body = response.getBody();
            if (body != null && body.containsKey("token")) {
                String csrfToken = body.get("token");
                System.out.println("CSRF Token: " + csrfToken);
                return csrfToken;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при получении CSRF токена: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean login(String username, String password, String csrfToken) {
        String loginUrl = BASE_URL + "/login?remember-me=false";
        Map<String, Object> loginRequest = new HashMap<>();
        loginRequest.put("userName", username);
        loginRequest.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-CSRF-TOKEN", csrfToken);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(loginRequest, headers);

        try {
            ResponseEntity<LoginResponse> response = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, LoginResponse.class);
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                sessionHeaders.set("X-CSRF-TOKEN", csrfToken);
                System.out.println("User successfully logged in.");
                return true;
            }
        } catch (Exception e) {
            System.out.println("Ошибка при логине: " + e.getMessage());
        }
        return false;
    }

    @Override
    public String getOrganisationProfile(UUID organisationId) {
        String url = BASE_URL + "/api/v1/organisations/" + organisationId;
        HttpEntity<String> entity = new HttpEntity<>(sessionHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            System.out.println("Ошибка при получении профиля организации: " + e.getMessage());
            return null;
        }
    }

    private static class LoginResponse {
        private UUID userId;

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }
    }
}
