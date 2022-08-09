package by.tms.projectinterpol.service;

import by.tms.projectinterpol.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    long save(User user);

    void update(User user);

    void delete(User user);

    List<User> findAllUsers();

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByLoginAndPassword(String username, String password);
}
