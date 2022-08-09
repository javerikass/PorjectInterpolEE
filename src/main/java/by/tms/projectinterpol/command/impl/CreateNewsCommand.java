package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.service.impl.NewsServiceImpl;
import by.tms.projectinterpol.service.impl.TagServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static by.tms.projectinterpol.util.PageUtil.SHOW_ALL_TAGS_PAGE;


public class CreateNewsCommand implements Command {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();
    private final TagServiceImpl tagService = TagServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        String headline = req.getParameter("headline");
        List<Tag> tags = tagMapper(Arrays.stream(req.getParameterValues("tag")).toList());
        long save = newsService.save(News.builder().text(text).headline(headline).tag(tags).publicationDate(LocalDate.now()).build());
        if (Objects.nonNull(save)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(SHOW_ALL_TAGS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }

    public List<Tag> tagMapper(List<String> tags) {
        List<Tag> tagList = new ArrayList<>();
        for (String tag : tags) {
            Optional<Tag> tagByName = tagService.findTagByName(tag);
            if (tagByName.isPresent()) {
                tagList.add(tagByName.get());
            }
        }
        return tagList;
    }
}
