package com.rustam.consoleApp.service.impl;

import com.rustam.consoleApp.client.RestClient;
import com.rustam.consoleApp.service.OrganisationService;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.UUID;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    private final RestClient restClient;

    public OrganisationServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void createOrganisation() {

    }

    @Override
    public String getOrganisationProfile(UUID organisationId) {
        return "";
    }

    @Override
    public void updateOrganisation(UUID organisationId) {

    }

    @Override
    public void deleteOrganisation(UUID organisationId) {

    }

    @Override
    public void handleOrganisationRequests(Scanner scanner) {
        System.out.println("\nВыберите действие для организации:");
        System.out.println("1. Создать организацию");
        System.out.println("2. Просмотреть профиль организации");
        System.out.println("3. Обновить организацию");
        System.out.println("4. Удалить организацию");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                createOrganisation();
                break;
            case "2":
                System.out.print("Введите UUID организации: ");
                String organisationIdStr = scanner.nextLine();
                try {
                    UUID organisationId = UUID.fromString(organisationIdStr); // локальная переменная
                    System.out.println(getOrganisationProfile(organisationId));
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "3":
                System.out.print("Введите UUID организации для обновления: ");
                try {
                    UUID organisationId = UUID.fromString(scanner.nextLine()); // снова локальная переменная
                    updateOrganisation(organisationId);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "4":
                System.out.print("Введите UUID организации для удаления: ");
                try {
                    UUID organisationId = UUID.fromString(scanner.nextLine()); // опять локальная переменная
                    deleteOrganisation(organisationId);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            default:
                System.out.println("Неверный выбор.");
                break;
        }
    }
}
