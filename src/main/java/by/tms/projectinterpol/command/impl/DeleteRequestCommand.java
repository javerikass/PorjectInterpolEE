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

import static by.tms.projectinterpol.util.PageUtil.SHOW_WANTED_APPROVED_REQUEST;


public class DeleteRequestCommand implements Command {


    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String age = req.getParameter("age");
        String nationality = req.getParameter("nationality");
        String gender = req.getParameter("gender");
        String details = req.getParameter("details");
        String reward = req.getParameter("reward");
        String status = req.getParameter("status");
        String approved = req.getParameter("approved");
        User sessionUser = (User) req.getSession().getAttribute("sessionUser");
        requestService.delete(Requests.builder()
                .id(Long.parseLong(id))
                .firstName(firstName)
                .lastName(lastName)
                .age(Integer.parseInt(age))
                .nationality(nationality)
                .gender(Gender.valueOf(gender))
                .details(details)
                .reward(Integer.parseInt(reward))
                .status(Status.valueOf(status))
                .approved(Boolean.parseBoolean(approved))
                .users(sessionUser)
                .build());
        if(status.equals(Status.WANTED.getStatusName())){
            return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(SHOW_WANTED_APPROVED_REQUEST), CommandResult.FORWARD);
        } else {
            return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(PageUtil.SHOW_MISSING_APPROVED_REQUEST), CommandResult.FORWARD);
        }

    }
}
