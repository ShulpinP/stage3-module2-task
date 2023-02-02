package com.mjc.school;

import com.mjc.school.controller.commands.Factory.CommandConstants;
import com.mjc.school.controller.commands.Factory.CommandFactory;
import com.mjc.school.controller.implementation.NewsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@Component
public class Menu {
    private final CommandFactory commandFactory;
    @Autowired
    public Menu(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
    }
    private void printMenu() {
        MenuStates[] menuItems = MenuStates.values();
        for (MenuStates i : menuItems) {
            System.out.println(i.getId() + " - " + i.getName());
        }
    }

    public void runMenu(Scanner scanner) {
        String input = "not defined";
        while (true) {
            printMenu();
            System.out.print("Enter the number of operation \n>>");
            input = scanner.nextLine();
            switch (input) {
                case "0" -> exit(0);
                case "1" -> createNews(scanner);
                case "2" -> getAllNews();
                case "3" -> getNewsById(scanner);
                case "4" -> updateNews(scanner);
                case "5" -> deleteNews(scanner);
                case "6" -> createAuthor(scanner);
                case "7" -> getAllAuthors();
                case "8" -> getAuthorById(scanner);
                case "9" -> updateAuthor(scanner);
                case "10" -> deleteAuthor(scanner);
                default -> System.out.println("Command not found");
            }
        }
    }

    private void createNews(Scanner scanner) {
        try {
            String title, content;
            long authorId;
            System.out.println("Operation: " + MenuStates.CREATE_NEWS.getName());
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.CREATE_NEWS, title, content, authorId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllNews() {
        try {
            System.out.println("Operation: " + MenuStates.GET_ALL_NEWS.getName());

            List<Object> newsList = Collections.unmodifiableList((List<Object>) commandFactory
                    .create(CommandConstants.GET_ALL_NEWS)
                    .execute());
            newsList.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void getNewsById(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuStates.GET_NEWS_BY_ID.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.GET_NEWS_BY_ID, newsId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateNews(Scanner scanner) {
        try {
            String title, content;
            long authorId, newsId;
            System.out.println("Operation: " + MenuStates.UPDATE_NEWS.getName());
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);
            System.out.print("Enter news title:\n>>");
            title = scanner.nextLine();
            System.out.print("Enter news content:\n>>");
            content = scanner.nextLine();
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.UPDATE_NEWS, newsId, title, content, authorId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteNews(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuStates.DELETE_NEWS.getName());
            long newsId;
            System.out.print("Enter news id:\n>>");
            newsId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.DELETE_NEWS, newsId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void createAuthor(Scanner scanner) {
        try {
            String name;
            System.out.println("Operation: " + MenuStates.CREATE_AUTHOR.getName());
            System.out.print("Enter author name:\n>>");
            name = scanner.nextLine();

            System.out.println(commandFactory
                    .create(CommandConstants.CREATE_AUTHOR, name)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllAuthors() {
        try {
            System.out.println("Operation: " + MenuStates.GET_ALL_AUTHORS.getName());

            List<Object> authorList = Collections.unmodifiableList((List<Object>) commandFactory
                    .create(CommandConstants.GET_ALL_AUTHORS)
                    .execute());
            authorList.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAuthorById(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuStates.GET_AUTHOR_BY_ID.getName());
            long authorId;
            System.out.print("Enter news id:\n>>");
            authorId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.GET_AUTHOR_BY_ID, authorId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateAuthor(Scanner scanner) {
        try {
            String authorName;
            long authorId;
            System.out.println("Operation: " + MenuStates.UPDATE_AUTHOR.getName());
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);
            System.out.print("Enter author name:\n>>");
            authorName = scanner.nextLine();

            System.out.println(commandFactory
                    .create(CommandConstants.UPDATE_AUTHOR, authorId, authorName)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAuthor(Scanner scanner) {
        try {
            System.out.println("Operation: " + MenuStates.DELETE_AUTHOR.getName());
            long authorId;
            System.out.print("Enter author id:\n>>");
            authorId = readId(scanner);

            System.out.println(commandFactory
                    .create(CommandConstants.DELETE_AUTHOR, authorId)
                    .execute());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private long readId(Scanner scanner) {
        if (!scanner.hasNextLong()) {
            scanner.nextLine();
            throw new IllegalArgumentException("Id must be an integer");
        }
        long id = scanner.nextLong();
        scanner.nextLine();
        return id;
    }
}