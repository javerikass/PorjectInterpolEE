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
import java.util.Optional;

import static by.tms.projectinterpol.util.PageUtil.SHOW_ALL_USERS_PAGE;

public class DeleteUserCommand implements Command {

    private final UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Optional<User> userByUsername = userService.findUserByUsername(username);
        if (userByUsername.isPresent()) {
            userService.delete(userByUsername.get());
            return new CommandResult(req.getContextPath() + JSPUtil.getControllerCommandPath(SHOW_ALL_USERS_PAGE), CommandResult.FORWARD);
        }
        return new CommandResult(req.getContextPath() + JSPUtil.ERROR_PAGE, CommandResult.REDIRECT);
    }
}
