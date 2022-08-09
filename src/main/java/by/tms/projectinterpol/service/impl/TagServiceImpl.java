package by.tms.projectinterpol.service.impl;


import by.tms.projectinterpol.dao.TagDAO;
import by.tms.projectinterpol.dao.impl.TagDAOImpl;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.service.TagService;

import java.util.List;
import java.util.Optional;

public class TagServiceImpl implements TagService {

    private static TagServiceImpl instance;
    private final TagDAO tagDAO;

    private TagServiceImpl() {
        tagDAO = TagDAOImpl.getInstance();
    }

    public static TagServiceImpl getInstance() {
        if (instance == null) {
            instance = new TagServiceImpl();
        }
        return instance;
    }

    @Override
    public long save(Tag tag) {
        return tagDAO.save(tag);
    }

    @Override
    public void update(Tag tag) {
        tagDAO.update(tag);
    }

    @Override
    public void delete(Tag tag) {
        tagDAO.delete(tag);
    }

    @Override
    public List<Tag> findAll() {
        return tagDAO.findAll();
    }

    @Override
    public Optional<Tag> findTagByName(String tagName) {
        return tagDAO.findTagByName(tagName);
    }
}
