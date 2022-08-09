package by.tms.projectinterpol.command.impl;


import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.service.impl.RequestServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ShowAllDisapprovedRequestCommand implements Command {

    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Requests> allDisapprovedRequests = requestService.findRequestsByApproval(false);
        req.setAttribute("allDisapprovedRequests", allDisapprovedRequests);
        if (Objects.nonNull(allDisapprovedRequests)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.REQUESTS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
