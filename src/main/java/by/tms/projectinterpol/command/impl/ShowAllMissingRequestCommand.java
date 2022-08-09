package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.service.impl.RequestServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static by.tms.projectinterpol.util.PageUtil.MISSING_PAGE;


public class ShowAllMissingRequestCommand implements Command {


    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String offset = req.getParameter("offset");
        req.setAttribute("pages",(int) Math.ceil(requestService.findAmountRequestByStatusAndApproval(Status.MISSING, true) / 5.0));
        List<Requests> missingRequests= requestService.findRequestsByStatusAndApprovalWithLimitAndOffset(Status.MISSING, true, "5", offset);
        req.setAttribute("missingRequests", missingRequests);
        if (Objects.nonNull(missingRequests)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(MISSING_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
