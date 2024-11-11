package com.rustam.consoleApp.service;

import java.util.Scanner;
import java.util.UUID;

public interface UserService {
    void createUser();

    String getUserProfile(UUID organisationId);

    void updateUser(UUID organisationId);

    void deleteUser(UUID organisationId);

    void handleUserRequests(Scanner scanner);
}
