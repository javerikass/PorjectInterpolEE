package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.service.impl.RequestServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class ShowAllWantedRequestCommand implements Command {

    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String offset = req.getParameter("offset");
        req.setAttribute("pages",(int) Math.ceil(requestService.findAmountRequestByStatusAndApproval(Status.WANTED, true) / 5.0));
        List<Requests> wantedRequests = requestService.findRequestsByStatusAndApprovalWithLimitAndOffset(Status.WANTED, true, "5", offset);
        req.setAttribute("wantedRequests", wantedRequests);
        if (Objects.nonNull(wantedRequests)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.WANTED_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
