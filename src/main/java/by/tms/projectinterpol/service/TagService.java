package by.tms.projectinterpol.service;

import by.tms.projectinterpol.entity.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    long save(Tag tag);

    void update(Tag tag);

    void delete(Tag tag);

    List<Tag> findAll();

    Optional<Tag> findTagByName(String tagName);
}
