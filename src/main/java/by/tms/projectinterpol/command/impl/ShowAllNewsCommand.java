package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.News;
import by.tms.projectinterpol.service.impl.NewsServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ShowAllNewsCommand implements Command {

    private final NewsServiceImpl newsService = NewsServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<News> allNews = newsService.findAll();
        req.setAttribute("allNews", allNews);
        if (Objects.nonNull(allNews)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.NEWS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
