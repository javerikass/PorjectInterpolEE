package by.tms.projectinterpol.command.impl;

import by.tms.projectinterpol.command.Command;
import by.tms.projectinterpol.command.CommandResult;
import by.tms.projectinterpol.entity.User;
import by.tms.projectinterpol.util.JSPUtil;
import by.tms.projectinterpol.util.PageUtil;
import by.tms.projectinterpol.validate.Validator;
import by.tms.projectinterpol.validate.impl.UserLoginValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class LoginCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Validator<User> userValidator = new UserLoginValidator();
        Optional<User> userOptional = userValidator.validate(req);
        if (userOptional.isPresent()) {
            req.getSession().setAttribute(PageUtil.USER_ATTRIBUTE, userOptional.get());
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.INTERPOL_PAGE), CommandResult.REDIRECT);
        } else {
            req.setAttribute("errorLogin","Incorrect password");
            return new CommandResult(req.getContextPath() + JSPUtil.getJSPPath(PageUtil.LOGIN_PAGE), CommandResult.FORWARD);
        }
    }
}
