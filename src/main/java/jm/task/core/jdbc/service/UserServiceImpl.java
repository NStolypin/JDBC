package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDAO = new UserDaoJDBCImpl(); // UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userDAO.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDAO.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDAO.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.getAllUsers();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        userDAO.cleanUsersTable();
    }
}
