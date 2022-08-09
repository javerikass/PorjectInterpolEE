package by.tms.projectinterpol.dao;


import by.tms.projectinterpol.entity.User;

import java.util.Optional;

public interface UserDAO extends BaseDAO<User> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByLoginAndPassword(String username, String password);
}
