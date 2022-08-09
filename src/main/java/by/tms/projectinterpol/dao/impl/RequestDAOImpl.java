package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.RequestDAO;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
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

public class RequestDAOImpl implements RequestDAO {

    private static final RequestDAOImpl INSTANCE = new RequestDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(RequestDAOImpl.class);

    private RequestDAOImpl() {
    }

    public static RequestDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(Requests entity) {
        long requestId = 0;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("INSERT INTO interpolee_storage.requests" +
                     "(age, approved, details, firstname, gender, lastname, nationality, reward, status, user_id) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            requestStatement.setInt(1, entity.getAge());
            requestStatement.setBoolean(2, entity.isApproved());
            requestStatement.setString(3, entity.getDetails());
            requestStatement.setString(4, entity.getFirstName());
            requestStatement.setString(5, entity.getGender().getGenderName());
            requestStatement.setString(6, entity.getLastName());
            requestStatement.setString(7, entity.getNationality());
            requestStatement.setInt(8, entity.getReward());
            requestStatement.setString(9, entity.getStatus().getStatusName());
            requestStatement.setLong(10, entity.getUsers().getId());
            requestStatement.executeUpdate();
            ResultSet generatedKeys = requestStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                requestId = generatedKeys.getLong("id");
                entity.setId(requestId);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return requestId;
    }

    @Override
    public void update(Requests entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("UPDATE interpolee_storage.requests SET age = ?, approved = ?, details = ?, firstname = ?, gender = ?, lastname = ?, nationality = ?, reward = ?, status = ?, user_id = ? WHERE id=?")) {
            requestStatement.setInt(1, entity.getAge());
            requestStatement.setBoolean(2, entity.isApproved());
            requestStatement.setString(3, entity.getDetails());
            requestStatement.setString(4, entity.getFirstName());
            requestStatement.setString(5, entity.getGender().getGenderName());
            requestStatement.setString(6, entity.getLastName());
            requestStatement.setString(7, entity.getNationality());
            requestStatement.setInt(8, entity.getReward());
            requestStatement.setString(9, entity.getStatus().getStatusName());
            requestStatement.setLong(10, entity.getUsers().getId());
            requestStatement.setLong(11, entity.getId());
            requestStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void delete(Requests entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("DELETE FROM interpolee_storage.requests WHERE id=?")) {
            requestStatement.setLong(1, entity.getId());
            requestStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public List<Requests> findAll() {
        List<Requests> requestList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.requests")) {
            ResultSet resultSet = requestStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(buildRequest(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return requestList;
    }

    private Requests buildRequest(ResultSet resultSet) throws SQLException {
        return Requests.builder()
                .id(resultSet.getLong("id"))
                .firstName(resultSet.getString("firstName"))
                .age(resultSet.getInt("age"))
                .nationality(resultSet.getString("nationality"))
                .details(resultSet.getString("details"))
                .status(Status.valueOf(resultSet.getString("status").toUpperCase(Locale.ROOT)))
                .approved(resultSet.getBoolean("approved"))
                .gender(Gender.valueOf(resultSet.getString("gender").toUpperCase(Locale.ROOT)))
                .reward(resultSet.getInt("reward"))
                .build();
    }

    @Override
    public Optional<Requests> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Requests> findRequestByAge(Integer age) {
        return null;
    }

    @Override
    public List<Requests> findRequestsByGender(Gender gender) {
        return null;
    }

    @Override
    public List<Requests> findRequestsByApproval(boolean approved) {
        List<Requests> requestList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.requests WHERE approved=?")) {
            requestStatement.setBoolean(1, approved);
            ResultSet resultSet = requestStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(buildRequest(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return requestList;
    }

    @Override
    public List<Requests> findRequestByNationality(String nationality) {
        return null;
    }

    @Override
    public List<Requests> findRequestByName(String firstName, String lastName) {
        return null;
    }

    @Override
    public long findAmountRequestByStatusAndApproval(Status status, boolean approved) {
        long countRequests = 0;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("SELECT count(*) FROM interpolee_storage.requests WHERE approved=? and status=?")) {
            requestStatement.setBoolean(1, approved);
            requestStatement.setString(2, status.getStatusName());
            ResultSet resultSet = requestStatement.executeQuery();
            while (resultSet.next()) {
                countRequests = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return countRequests;
    }

    @Override
    public List<Requests> findRequestsByStatusAndApprovalWithLimitAndOffset(Status status, boolean approved, String limit, String offset) {
        List<Requests> requestList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement requestStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.requests WHERE approved=? and status=? LIMIT ? OFFSET ?")) {
            requestStatement.setBoolean(1, approved);
            requestStatement.setString(2, status.getStatusName());
            requestStatement.setInt(3, Integer.parseInt(limit));
            requestStatement.setInt(4, Integer.parseInt(offset));
            ResultSet resultSet = requestStatement.executeQuery();
            while (resultSet.next()) {
                requestList.add(buildRequest(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return requestList;
    }
}
