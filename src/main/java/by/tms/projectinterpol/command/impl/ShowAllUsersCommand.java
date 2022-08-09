package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.service.impl.UserServiceImpl;
import by.tms.projectinterpol.util.JSPUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static by.tms.projectinterpol.util.PageUtil.USERS_PAGE;

public class ShowAllUsersCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userService.findAllUsers();
        req.setAttribute("allUsers", allUsers);
        if (Objects.nonNull(allUsers)) {
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(USERS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
