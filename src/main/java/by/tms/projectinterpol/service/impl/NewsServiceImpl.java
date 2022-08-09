package by.tms.projectinterpol.service.impl;

import by.tms.projectinterpol.dao.NewsDAO;
import by.tms.projectinterpol.dao.impl.NewsDAOImpl;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.service.NewsService;

import java.time.LocalDate;
import java.util.List;

public class NewsServiceImpl implements NewsService {

    private static NewsServiceImpl instance;
    private final NewsDAO newsDAO;

    private NewsServiceImpl() {
        newsDAO = NewsDAOImpl.getInstance();
    }

    public static NewsServiceImpl getInstance() {
        if (instance == null) {
            instance = new NewsServiceImpl();
        }
        return instance;
    }

    @Override
    public long findNewsAmount() {
        return newsDAO.findNewsAmount();
    }

    @Override
    public long save(News news) {
        return newsDAO.save(news);
    }

    @Override
    public void update(News news) {
        newsDAO.update(news);
    }

    @Override
    public void delete(News news) {
        newsDAO.delete(news);
    }

    @Override
    public List<News> findAll() {
        return newsDAO.findAll();
    }

    @Override
    public List<News> findNewsByTag(String tag) {
        return newsDAO.findNewsByTag(tag);
    }

    @Override
    public List<News> findAllNewsWithOffset(String offset) {
        return findAllNewsWithOffset(offset);
    }

    @Override
    public List<News> findNewsByPublicationDate(LocalDate localDate) {
        return findNewsByPublicationDate(localDate);
    }
}
