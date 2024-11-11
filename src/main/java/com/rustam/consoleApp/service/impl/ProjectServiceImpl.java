package com.rustam.consoleApp.service.impl;

import com.rustam.consoleApp.client.RestClient;
import com.rustam.consoleApp.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final RestClient restClient;

    public ProjectServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void createProject() {

    }

    @Override
    public String getProjectProfile(UUID projectId) {
        return "";
    }

    @Override
    public void updateProject(UUID projectId) {

    }

    @Override
    public void deleteProject(UUID projectId) {

    }

    @Override
    public void handleProjectRequests(Scanner scanner) {
        System.out.println("\nВыберите действие для проекта:");
        System.out.println("1. Создать проект");
        System.out.println("2. Просмотреть профиль проекта");
        System.out.println("3. Обновить проект");
        System.out.println("4. Удалить проект");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                createProject();
                break;
            case "2":
                System.out.print("Введите UUID проекта: ");
                String projectIdStr = scanner.nextLine();
                try {
                    UUID projectId = UUID.fromString(projectIdStr); // локальная переменная
                    System.out.println(getProjectProfile(projectId));
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "3":
                System.out.print("Введите UUID проекта для обновления: ");
                try {
                    UUID projectId = UUID.fromString(scanner.nextLine()); // снова локальная переменная
                    updateProject(projectId);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "4":
                System.out.print("Введите UUID проекта для удаления: ");
                try {
                    UUID projectId = UUID.fromString(scanner.nextLine()); // опять локальная переменная
                    deleteProject(projectId);
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

