package com.rustam.consoleApp;

import com.rustam.consoleApp.client.RestClient;
import com.rustam.consoleApp.client.impl.RestClientImpl;
import com.rustam.consoleApp.service.OrganisationService;
import com.rustam.consoleApp.service.ProjectService;
import com.rustam.consoleApp.service.UserService;
import com.rustam.consoleApp.service.impl.OrganisationServiceImpl;
import com.rustam.consoleApp.service.impl.ProjectServiceImpl;
import com.rustam.consoleApp.service.impl.UserServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RestClient restClient = new RestClientImpl();
        OrganisationService organisationService = new OrganisationServiceImpl(restClient);
        ProjectService projectService = new ProjectServiceImpl(restClient);
        UserService userService = new UserServiceImpl(restClient);

        Scanner scanner = new Scanner(System.in);

        // Процесс получения csrfToken
        String csrfToken = restClient.getCsrfToken();
        if (csrfToken == null) {
            System.out.println("Не удалось получить CSRF токен.");
            return;
        }

        // Процесс логина
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        boolean loginSuccess = restClient.login(username, password, csrfToken);
        if (!loginSuccess) {
            System.out.println("Не удалось выполнить вход в систему.");
            return;
        }

        while (true) {
            System.out.println("\nВыберите тип запроса:");
            System.out.println("1. Запросы организации");
            System.out.println("2. Запросы проекта");
            System.out.println("3. Запросы пользователей");
            System.out.println("0. Выход");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    organisationService.handleOrganisationRequests(scanner);
                    break;
                case "2":
                    projectService.handleProjectRequests(scanner);
                    break;
                case "3":
                    userService.handleUserRequests(scanner);
                    break;
                case "0":
                    System.out.println("Выход из программы.");
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
