package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.impl.RequestServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


public class ApproveRequestCommand implements Command {

    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String approved = req.getParameter("approved");
        Requests request = Requests.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .age(Integer.parseInt(req.getParameter("age")))
                .nationality(req.getParameter("nationality"))
                .gender(Gender.valueOf(req.getParameter("gender").toUpperCase(Locale.ROOT)))
                .details(req.getParameter("details"))
                .reward(Integer.parseInt(req.getParameter("reward")))
                .status(Status.valueOf(req.getParameter("status").toUpperCase(Locale.ROOT)))
                .approved(Boolean.parseBoolean(approved))
                .users((User) req.getSession().getAttribute("sessionUser"))
                .build();
        if (Boolean.parseBoolean(approved)) {
            requestService.update(request);
        } else {
            requestService.delete(request);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(PageUtil.SHOW_ALL_DISAPPROVED_REQUEST), CommandResult.FORWARD);
    }
}
