package by.tms.projectinterpol.dao.impl;

import by.tms.projectinterpol.dao.NewsDAO;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.jdbc.ConnectionPool;
import by.tms.projectinterpol.jdbc.ProxyConnection;
import by.tms.projectinterpol.util.LoggerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static by.tms.projectinterpol.util.LoggerUtil.DAO_METHODS_EXCEPTION_MESSAGE;


public class NewsDAOImpl implements NewsDAO {

    private static final NewsDAOImpl INSTANCE = new NewsDAOImpl();
    private static final Logger LOGGER = LogManager.getLogger(NewsDAOImpl.class);

    private NewsDAOImpl() {
    }

    public static NewsDAOImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public long save(News entity) {
        long newsId = 0;
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement saveUserStatement = connection.prepareStatement("INSERT INTO interpolee_storage.news(news_text,headline, publication_date) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement saveNewsTagsStatement = connection.prepareStatement("INSERT INTO interpolee_storage.news_tags(news_id,tag_id) VALUES (?,?)")) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            saveUserStatement.setString(1, entity.getText());
            saveUserStatement.setString(2, entity.getHeadline());
            saveUserStatement.setObject(3, entity.getPublicationDate());
            saveUserStatement.executeUpdate();
            ResultSet generatedKeys = saveUserStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                newsId = generatedKeys.getLong("id");
                entity.setId(newsId);
            }
            for (Tag tag : entity.getTag()) {
                saveNewsTagsStatement.setLong(1, newsId);
                saveNewsTagsStatement.setLong(2, tag.getId());
                saveNewsTagsStatement.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
            rollbackConnection(connection);
        } finally {
            closeConnection(connection);
        }
        return newsId;
    }

    @Override
    public void update(News entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("UPDATE interpolee_storage.news SET news_text = ?, headline = ?, publication_date = ? WHERE id=?", Statement.RETURN_GENERATED_KEYS)) {
            userStatement.setString(1, entity.getText());
            userStatement.setString(2, entity.getHeadline());
            userStatement.setString(3, entity.getPublicationDate().toString());
            userStatement.setLong(4, entity.getId());
            userStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public void delete(News entity) {
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement userStatement = connection.prepareStatement("DELETE FROM interpolee_storage.users WHERE username=?")) {
            userStatement.setLong(1, entity.getId());
            userStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public List<News> findAll() {
        List<News> newsList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement newsStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.news", Statement.RETURN_GENERATED_KEYS)) {
            ResultSet resultSet = newsStatement.executeQuery();
            while (resultSet.next()) {
                News news = buildNews(resultSet);
                news.setTag(findNewsTags(news));
                System.out.println(news);
                newsList.add(news);
            }
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return newsList;
    }

    public List<Tag> findNewsTags(News news) {
        List<Tag> tagList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement tagStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.tag INNER JOIN interpolee_storage.news_tags nt on nt.news_id = ? and tag.id = nt.tag_id")) {
            tagStatement.setLong(1, news.getId());
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


    private News buildNews(ResultSet resultSet) throws SQLException {
        return News.builder()
                .id(resultSet.getLong("id"))
                .text(resultSet.getString("news_text"))
                .headline(resultSet.getString("headline"))
                .build();
    }

    @Override
    public Optional<News> findById(long id) {
        return Optional.empty();
    }

    @Override
    public long findNewsAmount() {
        long countNews = 0;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement saveUserStatement = connection.prepareStatement("SELECT count(*) FROM interpolee_storage.news")) {
            saveUserStatement.executeUpdate();
            ResultSet count = saveUserStatement.executeQuery();
            if (count.next()) {
                countNews = count.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return countNews;
    }

    @Override
    public List<News> findNewsByTag(String tag) {
        return null;
    }

    @Override
    public List<News> findAllNewsWithOffset(String offset) {
        List<News> newsList = new ArrayList<>();
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement newsStatement = connection.prepareStatement("SELECT * FROM interpolee_storage.news LIMIT 5 OFFSET ?")) {
            newsStatement.setInt(1, Integer.parseInt(offset));
            ResultSet resultSet = newsStatement.executeQuery();
            while (resultSet.next()) {
                newsList.add(buildNews(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
        }
        return newsList;
    }

    @Override
    public List<News> findNewsByPublicationDate(LocalDate localDate) {
        return null;
    }

    protected void closeConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
            }
        }
    }

    protected void rollbackConnection(Connection connection) {
        if (Objects.nonNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                LOGGER.error(DAO_METHODS_EXCEPTION_MESSAGE, e);
            }
        }
    }
}
