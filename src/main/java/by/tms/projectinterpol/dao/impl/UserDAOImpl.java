package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.UserDAO;
import by.tms.projectinterpol.entity.Role;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.jdbc.ConnectionPool;
import by.tms.projectinterpol.jdbc.ProxyConnection;
import by.tms.projectinterpol.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final UserDAOImpl INSTANCE = new UserDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);

    private UserDAOImpl() {
    }

    public static UserDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(User user) {
        long userId = 0;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement("INSERT INTO interpolee_storage.users(username,password, role) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            saveUserStatement.setString(1, user.getUsername());
            saveUserStatement.setString(2, user.getPassword());
            saveUserStatement.setString(3, user.getRole().getRoleName());
            saveUserStatement.executeUpdate();
            ResultSet generatedKeys = saveUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong("id");
                LOGGER.debug("User was saved with {} id", userId);
                user.setId(userId);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return userId;
    }

    @Override
    public void update(User entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("UPDATE interpolee_storage.users SET username = ?, password = ?, role = ? WHERE username=?", Statement.RETURN_GENERATED_KEYS)) {
            userStatement.setString(1, entity.getUsername());
            userStatement.setString(2, entity.getPassword());
            userStatement.setString(3, entity.getRole().getRoleName());
            userStatement.setString(4, entity.getUsername());
            userStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void delete(User entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("DELETE FROM interpolee_storage.users WHERE username=?")) {
            userStatement.setString(1, entity.getUsername());
            userStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.users")) {
            ResultSet resultSet = userStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(buildUser(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return userList;
    }

    private User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role").toUpperCase(Locale.ROOT)))
                .build();
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        User user = null;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.users WHERE username=?")) {
            userStatement.setString(1, username);
            ResultSet resultSet = userStatement.executeQuery();
            while (resultSet.next()) {
                user = buildUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String username, String password) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement findUserByLoginAndPassword = connection.prepareStatement("SELECT * FROM interpolee_storage.users WHERE username=? and password=?")) {
            findUserByLoginAndPassword.setString(1, username);
            findUserByLoginAndPassword.setString(2, password);
            ResultSet resultSet = findUserByLoginAndPassword.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return Optional.empty();
    }

}
