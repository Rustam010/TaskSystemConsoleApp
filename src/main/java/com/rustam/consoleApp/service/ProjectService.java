package com.rustam.consoleApp.service;

import java.util.Scanner;
import java.util.UUID;

public interface ProjectService {
    void createProject();

    String getProjectProfile(UUID organisationId);

    void updateProject(UUID organisationId);

    void deleteProject(UUID organisationId);

    void handleProjectRequests(Scanner scanner);
}
