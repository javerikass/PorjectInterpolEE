package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.Gender;
import by.tms.projectinterpol.entity.Requests;
import by.tms.projectinterpol.entity.Status;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.impl.RequestServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static by.tms.projectinterpol.util.PageUtil.CREATE_REQUEST_PAGE;


public class CreateRequestCommand implements Command {

    private final RequestServiceImpl requestService = RequestServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
        long save = requestService.save(Requests.builder()
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
        if (Objects.nonNull(save)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(CREATE_REQUEST_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
