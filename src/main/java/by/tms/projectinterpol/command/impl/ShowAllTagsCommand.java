package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Tag;
import by.tms.projectinterpol.service.impl.TagServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ShowAllTagsCommand implements Command {

    private final TagServiceImpl tagService = TagServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tag> allTags = tagService.findAll();
        req.setAttribute("allTags", allTags);
        if (Objects.nonNull(allTags)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.CREATE_NEWS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
