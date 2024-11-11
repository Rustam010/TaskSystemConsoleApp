package com.rustam.consoleApp.client;

import java.util.UUID;

public interface RestClient {
    String getCsrfToken();

    boolean login(String username, String password, String csrfToken);

    String getOrganisationProfile(UUID organisationId);
}
