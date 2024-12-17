package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        
        UserService userService = new UserServiceImpl();
        
        userService.createUsersTable();
        userService.saveUser("Nikolai", "Stolypin", (byte)35);
        userService.saveUser("Evgeni", "Monakhov", (byte)36);
        userService.saveUser("Ivan", "Shumeiko", (byte)37);
        userService.saveUser("Andrei", "Saveliev", (byte)38);
        List<User> users = userService.getAllUsers();
        users.stream().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}