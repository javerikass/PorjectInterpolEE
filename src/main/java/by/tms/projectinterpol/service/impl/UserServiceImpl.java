package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.UserDAO;
import by.tms.projectinterpol.dao.impl.UserDAOImpl;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;
    private final UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDAOImpl.getInstance();
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public long save(User user) {
        return userDAO.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String username, String password) {
        return userDAO.findUserByLoginAndPassword(username, password);
    }
}
