package com.rustam.consoleApp.service.impl;

import com.rustam.consoleApp.client.RestClient;
import com.rustam.consoleApp.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Scanner;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final RestClient restClient;

    public UserServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void createUser() {

    }

    @Override
    public String getUserProfile(UUID userId) {
        return "";
    }

    @Override
    public void updateUser(UUID userId) {

    }

    @Override
    public void deleteUser(UUID userId) {

    }

    @Override
    public void handleUserRequests(Scanner scanner) {
        System.out.println("\nВыберите действие для проекта:");
        System.out.println("1. Создать пользователя");
        System.out.println("2. Просмотреть профиль пользователя");
        System.out.println("3. Обновить пользователя");
        System.out.println("4. Удалить пользователя");
        System.out.print("Ваш выбор: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                createUser();
                break;
            case "2":
                System.out.print("Введите UUID пользователя: ");
                String userIdStr = scanner.nextLine();
                try {
                    UUID userId = UUID.fromString(userIdStr); // локальная переменная
                    System.out.println(getUserProfile(userId));
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "3":
                System.out.print("Введите UUID пользователя для обновления: ");
                try {
                    UUID userId = UUID.fromString(scanner.nextLine()); // снова локальная переменная
                    updateUser(userId);
                } catch (IllegalArgumentException e) {
                    System.out.println("Неверный формат UUID.");
                }
                break;
            case "4":
                System.out.print("Введите UUID пользователя для удаления: ");
                try {
                    UUID userId = UUID.fromString(scanner.nextLine()); // опять локальная переменная
                    deleteUser(userId);
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
