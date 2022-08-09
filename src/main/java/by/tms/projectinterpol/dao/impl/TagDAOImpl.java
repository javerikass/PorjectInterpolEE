package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.TagDAO;
import by.tms.projectinterpol.entity.Tag;
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
import java.util.Optional;

public class TagDAOImpl implements TagDAO {

    private static final TagDAOImpl INSTANCE = new TagDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(TagDAOImpl.class);

    private TagDAOImpl() {
    }

    public static TagDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(Tag entity) {
        long tagId = 0;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement("INSERT INTO interpolee_storage.tag(tag) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            saveUserStatement.setString(1, entity.getTag());
            saveUserStatement.executeUpdate();
            ResultSet generatedKeys = saveUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tagId = generatedKeys.getLong("id");
                entity.setId(tagId);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return tagId;
    }

    @Override
    public void update(Tag entity) {

    }

    @Override
    public void delete(Tag entity) {

    }

    @Override
    public List<Tag> findAll() {
        List<Tag> tagList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement tagStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.tag")) {
            ResultSet resultSet = tagStatement.executeQuery();
            while (resultSet.next()) {
                tagList.add(buildTag(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return tagList;
    }

    private Tag buildTag(ResultSet resultSet) throws SQLException {
        return Tag.builder()
                .id(resultSet.getLong("id"))
                .tag(resultSet.getString("tag"))
                .build();
    }

    @Override
    public Optional<Tag> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        Tag tag = new Tag();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.tag WHERE tag.tag=?")) {
            saveUserStatement.setString(1, tagName);
            ResultSet resultSet = saveUserStatement.executeQuery();
            if (resultSet.next()) {
                tag = buildTag(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return Optional.ofNullable(tag);
    }
}
