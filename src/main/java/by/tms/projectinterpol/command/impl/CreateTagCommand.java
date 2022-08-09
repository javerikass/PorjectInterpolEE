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
import java.util.Objects;

public class CreateTagCommand implements Command {

    private final TagServiceImpl tagService = TagServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tag = req.getParameter("tag");
        long save = tagService.save(Tag.builder().tag(tag).build());
        if (Objects.nonNull(save)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(PageUtil.SHOW_ALL_TAGS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
