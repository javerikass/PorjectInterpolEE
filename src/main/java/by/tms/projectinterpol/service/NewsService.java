package by.tms.projectinterpol.service;

import by.tms.projectinterpol.entity.News;

import java.time.LocalDate;
import java.util.List;

public interface NewsService {

    long save(News news);

    void update(News news);

    void delete(News news);

    long findNewsAmount();

    List<News> findAll();

    List<News> findNewsByTag(String tag);

    List<News> findAllNewsWithOffset(String offset);

    List<News> findNewsByPublicationDate(LocalDate localDate);
}
