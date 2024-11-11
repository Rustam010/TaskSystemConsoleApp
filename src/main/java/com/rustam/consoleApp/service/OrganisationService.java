package com.rustam.consoleApp.service;

import java.util.Scanner;
import java.util.UUID;

public interface OrganisationService {
    void createOrganisation();

    String getOrganisationProfile(UUID organisationId);

    void updateOrganisation(UUID organisationId);

    void deleteOrganisation(UUID organisationId);

    void handleOrganisationRequests(Scanner scanner);
}
